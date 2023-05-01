package com.daedonginc.service.companyhistory.exception;

/**
 * @author domo
 * Created on 2023/03/23
 */
public class NotFoundCompanyHistoryException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found company history [companyHistoryId: %s]";

	public NotFoundCompanyHistoryException(final Long companyHistoryId) {
		super(String.format(MESSAGE_FORMAT, companyHistoryId));
	}
}
