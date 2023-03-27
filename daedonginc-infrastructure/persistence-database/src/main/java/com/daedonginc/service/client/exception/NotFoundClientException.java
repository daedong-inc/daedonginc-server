package com.daedonginc.service.client.exception;

/**
 * @author domo
 * Created on 2023/03/27
 */
public class NotFoundClientException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found client [clientId: %s]";

	public NotFoundClientException(final Long clientId) {
		super(String.format(MESSAGE_FORMAT, clientId));
	}
}
