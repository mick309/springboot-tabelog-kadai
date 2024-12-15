package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PremiumSuccessController {
	@GetMapping("/premium-success")
	public String premiumSuccess(@RequestParam("session_id") String sessionId, Model model) {
		// Stripeセッションを使って、課金情報を取得 (オプション)
		// ユーザーのPREMIUMステータスを更新
		model.addAttribute("message", "PREMIUM会員登録が完了しました！");
		return "premium-success";

	}
}
