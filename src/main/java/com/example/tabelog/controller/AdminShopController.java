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

import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
<<<<<<< HEAD
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

	@GetMapping
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Shop> shopPage;

		if (keyword != null && !keyword.isEmpty()) {
			shopPage = shopService.searchByKeyword(keyword, pageable);
		} else {
			shopPage = shopService.findAll(pageable);
		}

		model.addAttribute("shopPage", shopPage);
		model.addAttribute("keyword", keyword);

		return "admin/shops/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, Model model) {
		Shop shop = shopService.findById(id);
		model.addAttribute("shop", shop);
		return "admin/shops/show";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("shopRegisterForm", new ShopRegisterForm());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/shops/register";
	}

	@PostMapping("/register")
	public String registerShop(@ModelAttribute @Validated ShopRegisterForm shopRegisterForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("errorMessage", "入力内容にエラーがあります。");
			return "admin/shops/register";
		}

		shopService.create(shopRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");

		return "redirect:/admin/shops";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {
		Shop shop = shopService.findById(id);
		model.addAttribute("shopEditForm", new ShopEditForm(shop));
		model.addAttribute("categories", categoryService.findAll());
		return "admin/shops/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated ShopEditForm shopEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/shops/edit";
		}

		shopService.update(shopEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");

		return "redirect:/admin/shops";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		shopService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");
=======
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.service.ShopService;

@Controller
@RequestMapping("/admin/shops")
public class AdminShopController {
	private final ShopRepository shopRepository;
	private final ShopService shopService;

	public AdminShopController(ShopRepository shopRepository, ShopService shopService) {
		this.shopRepository = shopRepository;
		this.shopService = shopService;
	}

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

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Shop shopName = shopRepository.getReferenceById(id);

		model.addAttribute("shop", shopName);

		return "admin/shops/show";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("shopRegisterForm", new ShopRegisterForm());
		return "admin/shops/register";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute @Validated ShopRegisterForm shopRegisterForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/shops/register";
		}

		shopService.create(shopRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");

		return "redirect:/admin/shops";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Shop shop = shopRepository.getReferenceById(id);
		String imageName = shop.getImageName();
		ShopEditForm shopEditForm = new ShopEditForm(shop.getId(), shop.getCategoryId(), shop.getShopName(), null,
				shop.getDescription(),
				shop.getPriceUpper(), shop.getPriceLower(), shop.getHoursOpen(), shop.getHoursClose(),
				shop.getClosedDay(), shop.getPostalCode(), shop.getAddress(),
				shop.getPhoneNumber());

		model.addAttribute("imageName", imageName);
		model.addAttribute("shopEditForm", shopEditForm);

		return "admin/shops/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated ShopEditForm shopEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/shops/edit";
		}

		shopService.update(shopEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");

		return "redirect:/admin/shops";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		shopRepository.deleteById(id);

		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を削除しました。");

>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
		return "redirect:/admin/shops";
	}
}
