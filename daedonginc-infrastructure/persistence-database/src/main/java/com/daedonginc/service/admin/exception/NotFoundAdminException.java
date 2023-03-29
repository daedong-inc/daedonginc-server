package com.daedonginc.service.admin.exception;

/**
 * @author domo
 * Created on 2023/03/28
 */
public class NotFoundAdminException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found admin [name: %s, password: %s]";

	public NotFoundAdminException(final String name, final String password) {
		super(String.format(MESSAGE_FORMAT, name, password));
	}
}
