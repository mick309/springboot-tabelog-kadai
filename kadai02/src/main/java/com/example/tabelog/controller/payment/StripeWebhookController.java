package com.example.tabelog.controller.payment;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tabelog.service.UserService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;

@RestController
@RequestMapping("/api/stripe/webhook")
public class StripeWebhookController {

	@Value("${stripe.webhook-secret}")
	private String endpointSecret;

	private final UserService userService;

	private static final Logger LOGGER = Logger.getLogger(StripeWebhookController.class.getName());

	public StripeWebhookController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<String> handleWebhook(
			@RequestBody String payload,
			@RequestHeader("Stripe-Signature") String sigHeader) {

		try {
			// Webhook の署名を検証
			Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

			// イベントタイプごとに処理を分岐
			switch (event.getType()) {
			case "checkout.session.completed":
				handleCheckoutSessionCompleted(event);
				break;
			default:
				LOGGER.info("Unhandled event type: " + event.getType());
			}
			return ResponseEntity.ok("Webhook processed successfully");
		} catch (SignatureVerificationException e) {
			LOGGER.log(Level.WARNING, "Signature verification failed: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error handling webhook: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook handling failed");
		}
	}

	private void handleCheckoutSessionCompleted(Event event) {
		EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
		if (deserializer.getObject().isPresent()) {
			Session session = (Session) deserializer.getObject().get();

			// セッションからカスタマー情報を取得
			String customerId = session.getCustomer();

<<<<<<< HEAD
			
=======
			// Stripe Customer ID を使ってユーザーを「課金済み」に更新
			userService.markAsPaid(customerId);

>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
			LOGGER.info("Successfully processed checkout.session.completed for customer: " + customerId);
		} else {
			LOGGER.warning("Failed to deserialize session object for event: " + event.getId());
		}
	}
}
