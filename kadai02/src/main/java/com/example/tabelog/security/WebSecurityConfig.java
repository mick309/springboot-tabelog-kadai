package com.example.tabelog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
						.requestMatchers("/company/view").hasAnyRole("GENERAL", "ADMIN") // 一般ユーザーと管理者がアクセス可能
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

	/**
	 * パスワードをハッシュ化して保存・検証するためのエンコーダー
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
=======
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
						.requestMatchers("/company/view").hasAnyRole("GENERAL", "ADMIN") // 一般ユーザーと管理者がアクセス可能
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

	/**
	 * パスワードを平文で扱うエンコーダー
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				// パスワードをそのまま返す（平文比較用）
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// 平文同士を比較
				return rawPassword.toString().equals(encodedPassword);
			}
		};
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
	}
}
