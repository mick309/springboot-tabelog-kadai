package com.example.tabelog.controller.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

	@Value("${stripe.apiKey}")
	private String stripeApiKey;

	@Value("${stripe.successUrl}")
	private String successUrl;

	@Value("${stripe.cancelUrl}")
	private String cancelUrl;

	@PostConstruct
	public void init() {
		Stripe.apiKey = stripeApiKey;
	}

	@PostMapping("/create-checkout-session")
	public String createCheckoutSession() {
		try {
			SessionCreateParams params = SessionCreateParams.builder()
					.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
					.setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}")
					.setCancelUrl(cancelUrl)
					.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
					.addLineItem(
							SessionCreateParams.LineItem.builder()
									.setQuantity(1L)
									.setPrice("price_1QWAFKK56XZ4pCuCKBaW4i2b") // Replace with your actual price ID
									.build())
					.build();

			Session session = Session.create(params);
			return session.getId();
		} catch (Exception e) {
			throw new RuntimeException("Stripe Checkout Session creation failed", e);
		}
	}
}
