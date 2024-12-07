package com.example.tabelog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.tabelog.converter.StringToWeekDayConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final StringToWeekDayConverter stringToWeekDayConverter;

	public WebConfig(StringToWeekDayConverter stringToWeekDayConverter) {
		this.stringToWeekDayConverter = stringToWeekDayConverter;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(stringToWeekDayConverter);
	}

	// Spring Securityの設定
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN") // 管理者専用
						.requestMatchers("/premium/**").hasRole("PREMIUM_USER") // 課金ユーザー専用
						.requestMatchers("/user/**").hasRole("GENERAL") // 一般ユーザー専用
						.requestMatchers("/**").permitAll() // それ以外は全員アクセス可
				)
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/") // ログイン成功時のリダイレクト先
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.permitAll())
				.csrf().disable(); // 必要に応じて有効化（開発時のみ無効化推奨）

		return http.build();
	}
}
