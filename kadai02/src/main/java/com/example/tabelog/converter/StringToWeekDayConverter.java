package com.example.tabelog.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.tabelog.form.WeekDay;

@Component
public class StringToWeekDayConverter implements Converter<String, WeekDay> {

	private static final Map<String, WeekDay> map = new HashMap<>();

	static {
		map.put("月曜日", WeekDay.MONDAY);
		map.put("火曜日", WeekDay.TUESDAY);
		map.put("水曜日", WeekDay.WEDNESDAY);
		map.put("木曜日", WeekDay.THURSDAY);
		map.put("金曜日", WeekDay.FRIDAY);
		map.put("土曜日", WeekDay.SATURDAY);
		map.put("日曜日", WeekDay.SUNDAY);
	}

	@Override
	public WeekDay convert(String source) {
		WeekDay day = map.get(source);
		if (day == null) {
			throw new IllegalArgumentException("Invalid day: " + source);
		}
		return day;
	}
}

