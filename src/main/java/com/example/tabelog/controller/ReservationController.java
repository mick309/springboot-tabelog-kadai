package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Reservation;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.repository.ReservationRepository;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.ReservationService;

import jakarta.validation.Valid;

@SessionAttributes("reservationInputForm")
@Controller
public class ReservationController {

	private final ReservationRepository reservationRepository;
	private final ShopRepository shopRepository;
	private final ReservationService reservationService;

	public ReservationController(ReservationRepository reservationRepository, ShopRepository shopRepository,
			ReservationService reservationService) {
		this.reservationRepository = reservationRepository;
		this.shopRepository = shopRepository;
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);

		model.addAttribute("reservationPage", reservationPage);

		return "reservations/index";

	}

	@PostMapping("/reservations")
	public String createReservation(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@Valid @ModelAttribute ReservationInputForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		// ユーザー情報の取得
		User user = userDetailsImpl.getUser();
		if (user == null) {
			// 必要に応じてエラーハンドリング
			bindingResult.rejectValue("user", "error.user", "ユーザー情報を取得できませんでした。");
			return "reservation/form";
		}

		// 店舗情報の取得
		Shop shop = shopRepository.findById(form.getShopId()).orElse(null);
		if (shop == null) {
			bindingResult.rejectValue("shopId", "error.shop", "店舗情報を取得できませんでした。");
			return "reservation/form";
		}

		// 予約日と予約時間のバリデーション
		if (!reservationService.isReservationDateValid(form.getReservationsDate())) {
			bindingResult.rejectValue("reservationDate", "error.reservationDate", "予約日は本日以降の日付を入力してください。");
		}

		if (!reservationService.isReservationTimeValid(form.getReservationTime())) {
			bindingResult.rejectValue("reservationTime", "error.reservationTime", "予約時間は開店時間後、閉店時間の1時間前までに設定してください。");
		}

		if (bindingResult.hasErrors()) {
			return "reservation/form"; // フォームにエラーがある場合、再表示
		}

		Reservation reservation = new Reservation();
		reservation.setReservationsDate(form.getReservationsDate());
		reservation.setReservationTime(form.getReservationTime());
		reservation.setUser(user);
		reservation.setShop(shop);
		reservation.setNumberOfPeople(form.getNumberOfPeople());
		reservationRepository.save(reservation);

		redirectAttributes.addFlashAttribute("message", "予約が正常に作成されました。");

		return "redirect:/reservations";
	}

	@GetMapping("/shops/{id}/reservations/confirm")
	public String confirm(@PathVariable(name = "id") Integer id, Model model) {
		Shop shop = shopRepository.findById(id).orElse(null);

		if (shop == null) {
			return "error/404"; // 店舗が見つからない場合の処理
		}

		model.addAttribute("shop", shop);
		model.addAttribute("reservationInputForm", new ReservationInputForm());

		return "reservations/confirm"; // 正しいテンプレートパスを指定
	}
	
	
	@PostMapping("/shops/{id}/reservations/input")
	public String input(@PathVariable(name = "id") Integer id,
			@ModelAttribute ReservationInputForm reservationInputForm,
			Model model) {
		Shop shop = shopRepository.findById(id).orElse(null);

		if (shop == null) {
			return "error/404";
		}

		// セッションスコープにデータを保存
		model.addAttribute("shop", shop);
		model.addAttribute("reservationInputForm", reservationInputForm);

		return String.format("redirect:/shops/%d/reservations/confirm", id);
	}

	@PostMapping("/shops/{id}/reservations/create")
	public String create(@PathVariable(name = "id") Integer shopId, @Valid @ModelAttribute ReservationInputForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		if (bindingResult.hasErrors()) {
			return "reservations/confirm"; // エラーメッセージを表示するためのページ
		}

		// 既存の予約作成処理コード
		String errorMessage = createReservation(userDetailsImpl, form, bindingResult, redirectAttributes);
		if (errorMessage != null) {
			return errorMessage;
		}

		return "redirect:/reservations";
	}

	@ModelAttribute("reservationInputForm")
	public ReservationInputForm form() {
		return new ReservationInputForm();
	}
}
