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

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReservationInputForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ReviewRepository;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.CategoryService;
import com.example.tabelog.service.ReviewService;

@Controller
@RequestMapping("/shops")
public class ShopController {

	private final ShopRepository shopRepository;
	private final CategoryService categoryService;
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;

	// コンストラクタインジェクション
	public ShopController(ShopRepository shopRepository, CategoryService categoryService,
			ReviewRepository reviewRepository, ReviewService reviewService) {
		this.shopRepository = shopRepository;
		this.categoryService = categoryService;
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
	}

	// ショップ一覧ページ
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "area", required = false) String area,
			@RequestParam(name = "price_upper", required = false) Integer priceUpper,
			@RequestParam(name = "price_lower", required = false) Integer priceLower,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {

		// 新着ショップ（トップ10）を取得
		List<Shop> newShops = shopRepository.findTop10ByOrderByCreatedAtDesc();
		model.addAttribute("newshops", newShops);

		Page<Shop> shopPage;

		// 検索条件に基づいてショップ情報を取得
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
				shopPage = shopRepository.findAllByOrderByPriceUpperAsc(pageable);
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

	// ショップ詳細ページ
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id,
			Model model,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		// Shopの取得（findByIdを使って安全に取得）
		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid shop ID: " + id));

		// カテゴリー情報の取得
		Category category = shop.getCategory();
		if (category == null) {
			throw new IllegalStateException("Category not found for Shop ID: " + id);
		}

		// ユーザー情報の取得
		User user = userDetailsImpl != null ? userDetailsImpl.getUser() : null;

		// ユーザーがすでにレビューを投稿しているかどうか
		boolean hasUserAlreadyReviewed = false;
		if (userDetailsImpl != null) {
			hasUserAlreadyReviewed = reviewService.isReviewDone(user, shop);
		}

		// 新着レビューを取得
		List<Review> newReviews = reviewRepository.findTop6ByShopOrderByCreatedAtDesc(shop);
		long totalReviewCount = reviewRepository.countByShop(shop);

		model.addAttribute("shop", shop);
		model.addAttribute("category", category); // カテゴリー情報をモデルに追加
		model.addAttribute("user", user);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("newReviews", newReviews);
		model.addAttribute("hasUserAlreadyReviewed", hasUserAlreadyReviewed);
		model.addAttribute("totalReviewCount", totalReviewCount);

		return "/shops/show";
	}

	// ショップ登録フォーム
	@GetMapping("/admin/shops/register")
	public String showRegisterForm(Model model) {
		List<Category> categories = categoryService.findAll(); // Categoryサービスから取得
		model.addAttribute("categories", categories);
		model.addAttribute("shopRegisterForm", new ShopRegisterForm());
		return "register";
	}

	// メインページ
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}
}
