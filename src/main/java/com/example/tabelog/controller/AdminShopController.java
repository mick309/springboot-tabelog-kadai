package com.example.tabelog.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.service.CategoryService;
import com.example.tabelog.service.ShopService;

@Controller
@RequestMapping("/admin/shops")
public class AdminShopController {

	private final ShopService shopService;
	private final CategoryService categoryService;

	public AdminShopController(ShopService shopService, CategoryService categoryService) {
		this.shopService = shopService;
		this.categoryService = categoryService;
	}

	// 店舗一覧ページ
	@GetMapping
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			String keyword) {
		Page<Shop> shopPage = (keyword != null && !keyword.isEmpty())
				? shopService.searchShopsByKeyword(keyword, pageable)
				: shopService.getAllShops(pageable);

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

	// 店舗登録フォーム表示
	@GetMapping("/register")
	public String showShopRegisterForm(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("shopRegisterForm", new ShopRegisterForm());
		return "admin/shops/register";
	}

	// 店舗登録処理
	@PostMapping("/register")
	public String registerShop(@ModelAttribute @Validated ShopRegisterForm shopRegisterForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "admin/shops/register";
		}

		Shop shop = new Shop();
		shop.setShopName(shopRegisterForm.getShopName());
		shop.setDescription(shopRegisterForm.getDescription());
		shop.setPriceUpper(shopRegisterForm.getPriceUpper());
		shop.setPriceLower(shopRegisterForm.getPriceLower());
		shop.setHoursOpen(shopRegisterForm.getHoursOpen());
		shop.setHoursClose(shopRegisterForm.getHoursClose());
		shop.setPostalCode(shopRegisterForm.getPostalCode());
		shop.setAddress(shopRegisterForm.getAddress());
		shop.setPhoneNumber(shopRegisterForm.getPhoneNumber());
		shop.setClosedDay(shopRegisterForm.getClosedDay());

		// カテゴリーの設定
		shop.setCategory(
				categoryService.findById(shopRegisterForm.getCategoryId())
						.orElseThrow(
								() -> new IllegalArgumentException("無効なカテゴリID: " + shopRegisterForm.getCategoryId())));

		shopService.save(shop);
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
	public String update(@ModelAttribute @Validated ShopEditForm shopEditForm,
			BindingResult bindingResult,
			@PathVariable(name = "id") Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
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
		redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");
		return "redirect:/admin/shops";
	}
}
