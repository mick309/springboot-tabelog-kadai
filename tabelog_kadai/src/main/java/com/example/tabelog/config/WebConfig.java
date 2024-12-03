package com.example.tabelog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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
}
