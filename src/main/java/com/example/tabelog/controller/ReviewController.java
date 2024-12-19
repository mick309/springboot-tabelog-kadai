package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReviewEditForm;
import com.example.tabelog.form.ReviewRegisterForm;
import com.example.tabelog.repository.ReviewRepository;
import com.example.tabelog.repository.ShopRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.ReviewService;

@Controller
@RequestMapping("/shops/{shopId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository,
                            ShopRepository shopRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.shopRepository = shopRepository;
    }

    // レビュー一覧表示
    @GetMapping
    public String index(@PathVariable(name = "shopId") Long shopId,
                        @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
                        Model model) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id: " + shopId));
        Page<Review> reviewPage = reviewRepository.findByShopOrderByCreatedAtDesc(shop, pageable);

        model.addAttribute("shop", shop);
        model.addAttribute("reviewPage", reviewPage);

        return "review/index";
    }

    // レビュー登録画面表示
    @GetMapping("/register")
    public String register(@PathVariable(name = "shopId") Long shopId, Model model) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id:" + shopId));
        model.addAttribute("shop", shop);
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
        return "review/register";
    }

    // レビュー新規作成
    @PostMapping("/create")
    public String create(@PathVariable(name = "shopId") Long shopId,
                         @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                         Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Shop shop = shopRepository.findById(shopId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id:" + shopId));
            model.addAttribute("shop", shop);
            return "review/register";
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id:" + shopId));
        User user = userDetailsImpl.getUser();

        reviewService.create(reviewRegisterForm, shop, user);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");

        return "redirect:/shops/{shopId}";
    }

   
 // レビュー編集画面表示
    @GetMapping("/{reviewId}/edit")
    public String edit(@PathVariable(name = "shopId") Long shopId,
                       @PathVariable(name = "reviewId") Long reviewId, Model model) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id: " + shopId));
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Review Id: " + reviewId));

        ReviewEditForm reviewEditForm = new ReviewEditForm(
                review.getId(), 
                review.getEvaluation(), 
                review.getReviewComment());

        model.addAttribute("shop", shop);
        model.addAttribute("review", review);
        model.addAttribute("reviewEditForm", reviewEditForm);

        return "review/edit";
    }


    // レビュー更新処理
    @PostMapping("/{reviewId}/update")
    public String update(@PathVariable(name = "shopId") Long shopId,
                         @PathVariable(name = "reviewId") Long reviewId,
                         @ModelAttribute @Validated ReviewEditForm reviewEditForm,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            Shop shop = shopRepository.findById(shopId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Shop Id: " + shopId));
            Review review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Review Id: " + reviewId));

            model.addAttribute("shop", shop);
            model.addAttribute("review", review);
            return "review/edit";
        }

        reviewService.update(reviewEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");

        return "redirect:/shops/" + shopId;
    }
}