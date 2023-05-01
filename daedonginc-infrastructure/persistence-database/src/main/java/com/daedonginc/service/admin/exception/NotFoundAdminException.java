package com.daedonginc.service.admin.exception;

/**
 * @author domo
 * Created on 2023/03/28
 */
public class NotFoundAdminException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found admin [name: %s]";

	public NotFoundAdminException(final String name) {
		super(String.format(MESSAGE_FORMAT, name));
	}
}
