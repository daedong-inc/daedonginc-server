package com.daedonginc.service.news.exception;

/**
 * @author domo
 * Created on 2023/03/27
 */
public class NotFoundNewsException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found news [newsId: %s]";

	public NotFoundNewsException(final Long newsId) {
		super(String.format(MESSAGE_FORMAT, newsId));
	}
}
