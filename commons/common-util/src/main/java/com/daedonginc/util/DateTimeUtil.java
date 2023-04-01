package com.daedonginc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/04/02
 */
public class DateTimeUtil {
	public static LocalDateTime getTodayFirstTime(LocalDate date) {
		return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0);
	}

	public static LocalDateTime getTodayLastTime(LocalDate date) {
		return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59, 59);
	}
}
