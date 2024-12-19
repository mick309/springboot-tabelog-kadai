package com.example.tabelog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf
						.ignoringRequestMatchers("/api/**")) // CSRF無効化（APIエンドポイント用）
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/register", "/register-success", "/css/**", "/images/**", "/js/**", "/storage/**",
								"/", "/signup/**", "/shops", "/shops/{id}", "/stripe/webhook", "/shops/{id}/reviews",
								"/favicon.ico")
						.permitAll() // 認証不要のリクエスト
						.requestMatchers("/admin/**").hasRole("ADMIN") // 管理者のみアクセス可能
						.anyRequest().authenticated()) // その他のリクエストは認証必須
				.formLogin(form -> form
						.loginPage("/login") // カスタムログインページ
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/?loggedIn", true)
						.failureUrl("/login?error")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/?loggedOut")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString(); // パスワードをそのまま返す（平文比較）
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.toString().equals(encodedPassword); // 平文比較
			}
		};
	}
}
