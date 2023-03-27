package com.daedonginc.service.category.exception;

/**
 * @author domo
 * Created on 2023/03/27
 */
public class NotFoundCategoryException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found category [categoryId: %s]";

	public NotFoundCategoryException(final Long categoryId) {
		super(String.format(MESSAGE_FORMAT, categoryId));
	}
}
