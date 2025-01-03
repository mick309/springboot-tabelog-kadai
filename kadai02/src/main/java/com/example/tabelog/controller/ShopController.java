package com.example.tabelog.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.ShopService;

@Controller
@RequestMapping("/shops")
public class ShopController {

	private final ShopRepository shopRepository;
	private final ShopService shopService;

	public ShopController(ShopRepository shopRepository, ShopService shopService) {
		this.shopRepository = shopRepository;
		this.shopService = shopService;
	}

	@GetMapping
	public String index(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String area,
			@RequestParam(required = false) Integer priceLower,
			@RequestParam(required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {

		List<Shop> newShops = shopRepository.findTop10ByOrderByCreatedAtDesc();
		model.addAttribute("newshops", newShops);

		Page<Shop> shopPage;

		if (keyword != null && !keyword.isEmpty()) {
			shopPage = shopRepository.findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc(
					"%" + keyword + "%", "%" + keyword + "%", pageable);
		} else if (area != null && !area.isEmpty()) {
			shopPage = shopRepository.findByAddressLikeOrderByCreatedAtDesc(
					"%" + area + "%", pageable);
		} else if (priceLower != null) {
			shopPage = shopRepository.findByPriceLowerGreaterThanEqualOrderByCreatedAtDesc(priceLower, pageable);
		} else {
			shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);
		}

		model.addAttribute("shopPage", shopPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("area", area);
		model.addAttribute("priceLower", priceLower);
		model.addAttribute("order", order);

		return "shops/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {

		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid shop ID: " + id));

		model.addAttribute("shop", shop);
		model.addAttribute("reservationInputForm", new ReservationInputForm());

		return "shops/show";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name = "keyword", required = false) String keyword,
			@PageableDefault(page = 0, size = 10) Pageable pageable,
			Model model) {
		Page<Shop> shopPage = shopService.searchShopsByKeyword(keyword, pageable);
		model.addAttribute("shopPage", shopPage);
		model.addAttribute("keyword", keyword);

		return "shops/search";
	}
}
