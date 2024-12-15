package com.example.tabelog.config;

import com.stripe.Stripe;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

public class StripeSetup {
	public static void main(String[] args) throws Exception {
		// Stripe APIキーを設定
		Stripe.apiKey = "sk_test_51PfM1lK56XZ4pCuCQuUqt8SnOwz1spZjNwpfCn6ym9C8VvmVZHzcLdu6DGKVfjA6S77HLNz14GnYKCWgCWZEOkBH00gu5HpkqB";

		// 商品を作成
		ProductCreateParams productParams = ProductCreateParams.builder()
				.setName("Monthly Subscription")
				.build();
		Product product = Product.create(productParams);

		// 定期課金の価格を作成
		PriceCreateParams priceParams = PriceCreateParams.builder()
				.setProduct(product.getId())
				.setUnitAmount(50000L) // 500円 (Stripeは最小単位で指定、JPYの場合は100倍)
				.setCurrency("jpy")
				.setRecurring(PriceCreateParams.Recurring.builder()
						.setInterval(PriceCreateParams.Recurring.Interval.MONTH) // Enum 値を使用 // 毎月課金
						.build())
				.build();
		Price price = Price.create(priceParams);

		System.out.println("Price ID: " + price.getId());
	}
}
