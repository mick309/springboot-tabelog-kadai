package com.example.tabelog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tabelog.entity.Category; // ここを確認
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.service.CategoryService;

@Controller
@RequestMapping("/shops")
public class ShopController {

	private final ShopRepository shopRepository;
	private CategoryService categoryService;

	public ShopController(ShopRepository shopRepository, CategoryService categoryService) {
		this.shopRepository = shopRepository;
		this.categoryService = categoryService;
	}

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "area", required = false) String area,
			@RequestParam(name = "price_upper", required = false) Integer priceUpper,
			@RequestParam(name = "price_lower", required = false) Integer priceLower,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		// Top10の新着ショップを取得し、モデルに追加
		List<Shop> newShops = shopRepository.findTop10ByOrderByCreatedAtDesc();
		model.addAttribute("newshops", newShops);

		Page<Shop> shopPage;

		if (keyword != null && !keyword.isEmpty()) {
			if (order != null && order.equals("priceAsc")) {
				shopPage = shopRepository.findByShopNameLikeOrAddressLikeOrderByPriceUpperAsc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			} else {
				shopPage = shopRepository.findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			}
		} else if (area != null && !area.isEmpty()) {
			if (order != null && order.equals("priceAsc")) {
				shopPage = shopRepository.findByAddressLikeOrderByCreatedAtAsc("%" + area + "%", pageable);
			} else {
				shopPage = shopRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);

			}
		} else if (priceUpper != null) {
			if (order != null && order.equals("priceAsc")) {
				shopPage = shopRepository.findByPriceLowerGreaterThanEqualOrderByPriceUpperAsc(priceUpper, pageable);
			} else {
				shopPage = shopRepository.findByPriceUpperGreaterThanEqualOrderByCreatedAtDesc(priceUpper, pageable);

			}
		} else if (priceLower != null) {
			if (order != null && order.equals("priceAsc")) {
				shopPage = shopRepository.findByPriceLowerGreaterThanEqualOrderByPriceUpperAsc(priceLower, pageable);
			} else {
				shopPage = shopRepository.findByPriceLowerGreaterThanEqualOrderByCreatedAtDesc(priceLower, pageable);

			}
		} else {
			if (order != null && order.equals("priceAsc")) {
				shopPage = shopRepository.findAllByOrderByPriceUpperAsc(priceUpper, pageable);
			} else {
				shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);
			}
		}

		model.addAttribute("shopPage", shopPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("area", area);
		model.addAttribute("priceUpper", priceUpper);
		model.addAttribute("priceLower", priceLower);
		model.addAttribute("order", order);

		return "shops/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Optional<Shop> optionalShop = shopRepository.findById(id);
		if (optionalShop.isPresent()) {
			model.addAttribute("shop", optionalShop.get());
		} else {
			model.addAttribute("errorMessage", "指定された店舗は存在しません。");
			return "shops/error"; // エラーページを用意
		}
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		return "shops/show";

	}

	@GetMapping("/admin/shops/register")
	public String showRegisterForm(Model model) {
		List<Category> categories = categoryService.getAllCategories(); // 例: Categoryサービスから取得
		model.addAttribute("categories", categories);
		model.addAttribute("shopRegisterForm", new ShopRegisterForm());
		return "register";
	}

}
