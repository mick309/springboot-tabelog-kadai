package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Reservation;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.repository.ReservationRepository;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationController {

	private final ReservationRepository reservationRepository;
	private final ShopRepository shopRepository;
	private final UserRepository userRepository;

	public ReservationController(
			ReservationRepository reservationRepository,
			ShopRepository shopRepository,
			UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.shopRepository = shopRepository;
		this.userRepository = userRepository;
	}

	// 管理者用予約一覧ページ
	@GetMapping
	public String adminIndex(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {
		Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
		model.addAttribute("reservationPage", reservationPage);
		return "admin/reservations/index";
	}

	// 新規予約フォームページ
	@GetMapping("/form")
	public String showReservationForm(Model model) {
		// 全ユーザーと全店舗をリストで取得
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("shops", shopRepository.findAll());
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		return "admin/reservations/form";
	}

	// 新規予約作成処理
	@PostMapping("/create")
	public String createReservation(
			@Valid @ModelAttribute ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (bindingResult.hasErrors()) {
			// エラーがある場合はフォームに戻る
			model.addAttribute("users", userRepository.findAll());
			model.addAttribute("shops", shopRepository.findAll());
			return "admin/reservations/form";
		}

		// 店舗情報の取得
		Shop shop = shopRepository.findById(reservationInputForm.getShopId()).orElse(null);
		if (shop == null) {
			bindingResult.rejectValue("shopId", "error.shop", "無効な店舗IDが選択されました。");
			model.addAttribute("users", userRepository.findAll());
			model.addAttribute("shops", shopRepository.findAll());
			return "admin/reservations/form";
		}

		// ユーザー情報の取得
		User user = userRepository.findById(reservationInputForm.getUserId()).orElse(null);
		if (user == null) {
			bindingResult.rejectValue("userId", "error.user", "無効なユーザーIDが選択されました。");
			model.addAttribute("users", userRepository.findAll());
			model.addAttribute("shops", shopRepository.findAll());
			return "admin/reservations/form";
		}

		// 予約の作成と保存
		Reservation reservation = new Reservation();
		reservation.setReservationsDate(reservationInputForm.getReservationsDate());
		reservation.setReservationTime(reservationInputForm.getReservationTime());
		reservation.setNumberOfPeople(reservationInputForm.getNumberOfPeople());
		reservation.setUser(user);
		reservation.setShop(shop);

		reservationRepository.save(reservation);
		redirectAttributes.addFlashAttribute("successMessage", "予約が正常に作成されました。");
		return "redirect:/admin/reservations";
	}

	@GetMapping("/{id}/show")
	public String showReservationDetails(@PathVariable(name = "id") Integer id, Model model) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		if (reservation == null) {
			model.addAttribute("errorMessage", "指定された予約が見つかりません。");
			return "redirect:/admin/reservations";
		}
		model.addAttribute("reservation", reservation);
		return "admin/reservations/show"; // 適切なテンプレート名
	}
}
