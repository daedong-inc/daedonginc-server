package com.daedonginc.popup.excepion;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/04/02
 */
public class StartDateLaterThanEndDateException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "start date is later than end date [startDate: %s, endDate: %s]";

	public StartDateLaterThanEndDateException(final LocalDateTime startDate, final LocalDateTime endDate) {
		super(String.format(MESSAGE_FORMAT, startDate, endDate));
	}
}
