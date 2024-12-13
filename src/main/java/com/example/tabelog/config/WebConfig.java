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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/favicon.ico").permitAll() // 追加: favicon.ico 許可
						.requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 追加: 静的リソース許可
						.requestMatchers("/error").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/premium/**").hasRole("PREMIUM_USER")
						.requestMatchers("/user/**").hasRole("GENERAL")
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/"))
				.csrf(csrf -> csrf
						.ignoringRequestMatchers("/api/**"));
		return http.build();
	}

	
}
