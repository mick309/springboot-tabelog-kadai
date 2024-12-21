package com.example.tabelog.controller.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Controller
@RequestMapping("/api/stripe")
public class CheckoutController {

	@Value("${stripe.public.key}")
	private String stripePublicKey;

	@Value("${stripe.apiKey}")
	private String stripeApiKey;

	@Value("${stripe.successUrl}")
	private String successUrl;

	@Value("${stripe.cancelUrl}")
	private String cancelUrl;

	// プレミアムチェックアウトページ
	@GetMapping("/premium-checkout")
	public String checkoutPage(Model model) {
		model.addAttribute("stripePublicKey", stripePublicKey);
		return "user/premium-checkout"; // プレミアムチェックアウトのHTMLページ
	}

	// Stripe セッションを作成
	@PostMapping("/create-checkout-session")
	@ResponseBody
	public String createCheckoutSession(@AuthenticationPrincipal UserDetails userDetails) throws StripeException {
		// ユーザー情報を取得（必要であれば使用）
		String userEmail = userDetails.getUsername();

		// Stripe セッション作成パラメータ
		SessionCreateParams params = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}")
				.setCancelUrl(cancelUrl)
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setQuantity(1L)
								.setPrice("price_123456789") // Stripe ダッシュボードで設定した価格ID
								.build())
				.build();

		// セッション作成
		Session session = Session.create(params);

		// 作成したセッションのIDを返す
		return session.getId();
	}

	// チェックアウト成功ページ
	@GetMapping("/premium-success")
	public String successPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		// セッションIDをURLパラメータから取得
		String sessionId = (String) model.asMap().get("session_id");

		// ユーザー情報をモデルに追加
		model.addAttribute("user", userDetails);
		model.addAttribute("sessionId", sessionId);

		// 成功ページに遷移
		return "user/premium-success"; // 成功ページのHTMLテンプレート
	}

	// チェックアウトキャンセルページ
	@GetMapping("/premium-cancel")
	public String cancelPage() {
		return "user/premium-cancel"; // キャンセルページのHTMLテンプレート
	}
}
