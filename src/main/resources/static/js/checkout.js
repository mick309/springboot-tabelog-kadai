// Stripe 公開可能キーを設定
const stripe = Stripe('pk_test_51PfM1lK56XZ4pCuCNrhhlmbG0uqByy8fghmPpxVtcRUGDRg75tXsRg4xKmWxmIzPTWtELzafgXcsVTpp6NhlQGEn00SRbWupfD'); // 公開可能キー

// チェックアウトボタンのクリックイベントリスナー
document.getElementById("checkout-button").addEventListener("click", () => {
	fetch("/api/stripe/create-checkout-session", {
		method: "POST",
		headers: {
			"Content-Type": "application/json", // 必要な場合にヘッダーを追加
		},
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error("Network response was not ok");
			}
			return response.json(); // サーバーからJSONレスポンスを受け取る
		})
		.then((data) => {
			if (data.id) {
				return stripe.redirectToCheckout({ sessionId: data.id }); // セッションIDを使用してリダイレクト
			} else {
				throw new Error("Invalid session ID received");
			}
		})
		.then((result) => {
			if (result.error) {
				console.error("Stripe error:", result.error.message);
				alert("Error: " + result.error.message); // Stripeエラーを表示
			}
		})
		.catch((error) => {
			console.error("Error:", error.message);
			alert("エラーが発生しました。もう一度お試しください。"); // ネットワークエラーなどのエラーハンドリング
		});
});
