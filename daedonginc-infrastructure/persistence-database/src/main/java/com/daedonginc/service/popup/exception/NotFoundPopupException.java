package com.daedonginc.service.popup.exception;

/**
 * @author domo
 * Created on 2023/03/27
 */
public class NotFoundPopupException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found popup [popupId: %s]";

	public NotFoundPopupException(final Long popupId) {
		super(String.format(MESSAGE_FORMAT, popupId));
	}
}
