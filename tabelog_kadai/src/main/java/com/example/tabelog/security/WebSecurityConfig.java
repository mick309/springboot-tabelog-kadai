package com.example.tabelog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						// 静的リソースや一般ユーザーに公開するURLは許可
						.requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**", "/", "/signup/**",
								"/shops", "/shops/{id}", "/shops/{id}/reviews", "/stripe/webhook")
						.permitAll()
						// 管理者用ページはADMINロールを持つユーザーのみアクセス可能
						.requestMatchers("/admin/**").hasRole("ADMIN")
						// その他のリクエストは認証が必要
						.anyRequest().authenticated())
				.formLogin((form) -> form
						// ログインページの設定
						.loginPage("/login") // ログインページのURL
						.loginProcessingUrl("/login") // ログインフォームの送信先URL
						.defaultSuccessUrl("/?loggedIn", true) // ログイン成功時のリダイレクト先URL
						.failureUrl("/login?error") // ログイン失敗時のリダイレクト先URL
						.permitAll() // ログインページへのアクセスはすべてのユーザーに許可
				)
				.logout((logout) -> logout
						// ログアウト設定
						.logoutUrl("/logout") // ログアウトURL
						.logoutSuccessUrl("/?loggedOut") // ログアウト成功時のリダイレクト先URL
						.permitAll() // ログアウトはすべてのユーザーに許可
				)
				// CSRFを特定のURLで無効化（Stripe Webhook用など）
				.csrf().ignoringRequestMatchers("/stripe/webhook");

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptを使用してパスワードをハッシュ化
		return new BCryptPasswordEncoder();
	}
}
