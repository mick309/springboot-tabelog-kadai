package com.example.tabelog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						// 許可されたリソース
						.requestMatchers(
								"/css/**", "/images/**", "/js/**", "/storage/**", "/", "/signup/**", "/shops",
								"/shops/{id}", "/stripe/webhook", "/shops/{id}/reviews")
						.permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login") // ログインページのURL
						.loginProcessingUrl("/login") // ログインフォームの送信先URL
						.defaultSuccessUrl("/?loggedIn", true) // ログイン成功時のリダイレクト先URL
						.failureUrl("/login?error") // ログイン失敗時のリダイレクト先URL
						.permitAll())
				.logout((logout) -> logout
						.logoutUrl("/logout") // ログアウト用のURL
						.logoutSuccessUrl("/?loggedOut") // ログアウト成功時のリダイレクト先URL
						.invalidateHttpSession(true) // セッションを無効化
						.deleteCookies("JSESSIONID") // クッキーを削除
						.permitAll())
				.csrf().disable(); // 必要に応じてCSRFを無効化（本番環境では再検討が必要）

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString(); // パスワードをそのまま返す
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.toString().equals(encodedPassword); // 平文比較
			}
		};
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/favicon.ico"); // 無視リストに追加
	}

}
