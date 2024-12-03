package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.service.CategoryService;
import com.example.tabelog.service.ShopService;

@Controller
@RequestMapping("/admin/shops")
public class AdminShopController {

	private final ShopRepository shopRepository;
	private final ShopService shopService;
	private final CategoryService categoryService;

	public AdminShopController(ShopRepository shopRepository, ShopService shopService,
			CategoryService categoryService) {
		this.shopRepository = shopRepository;
		this.shopService = shopService;
		this.categoryService = categoryService;
	}

	// 店舗一覧ページ
	@GetMapping
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Shop> shopPage;

		if (keyword != null && !keyword.isEmpty()) {
			shopPage = shopRepository.findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%",
					"%" + keyword + "%", pageable);
		} else {
			shopPage = shopRepository.findAll(pageable);
		}

		model.addAttribute("shopPage", shopPage);
		model.addAttribute("keyword", keyword);

		return "admin/shops/index";
	}

	// 店舗詳細ページ
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Shop shop = shopService.findById(id);
		model.addAttribute("shop", shop);
		return "admin/shops/show";
	}

	// 店舗登録画面
	@GetMapping("/register")
	public String register(@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
		ShopRegisterForm form = new ShopRegisterForm();
		model.addAttribute("shopRegisterForm", form);

		if (categoryId != null) {
			// サービスからカテゴリを取得してモデルに設定
			Category category = categoryService.getCategoryById(categoryId);
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("category", category);
		}

		model.addAttribute("categories", categoryService.findAll());
		return "admin/shops/register";
	}

	// 店舗登録処理
	@PostMapping("/register")
	public String registerShop(@ModelAttribute @Validated ShopRegisterForm shopRegisterForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "admin/shops/register";
		}

		shopService.create(shopRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");

		return "redirect:/admin/shops";
	}

	// 店舗編集ページ
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Shop shop = shopService.findById(id);
		ShopEditForm shopEditForm = new ShopEditForm(
				shop.getId(),
				shop.getCategory() != null ? shop.getCategory().getId() : null,
				shop.getShopName(),
				null,
				shop.getDescription(),
				shop.getPriceUpper(),
				shop.getPriceLower(),
				shop.getHoursOpen(),
				shop.getHoursClose(),
				shop.getClosedDay(),
				shop.getPostalCode(),
				shop.getAddress(),
				shop.getPhoneNumber());

		model.addAttribute("shopEditForm", shopEditForm);
		model.addAttribute("categories", categoryService.findAll());

		return "admin/shops/edit";
	}

	// 店舗情報更新処理
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated ShopEditForm shopEditForm, BindingResult bindingResult,
			@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/shops/edit";
		}

		shopService.update(shopEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");

		return "redirect:/admin/shops";
	}

	// 店舗削除処理
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		shopService.delete(id);
		return "redirect:/admin/shops";
	}
}
