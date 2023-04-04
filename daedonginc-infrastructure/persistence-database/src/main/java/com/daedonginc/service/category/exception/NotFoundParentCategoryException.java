package com.daedonginc.service.category.exception;

/**
 * @author domo
 * Created on 2023/03/27
 */
public class NotFoundParentCategoryException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found parent category [parentCategoryId: %s]";

	public NotFoundParentCategoryException(final Long parentCategoryId) {
		super(String.format(MESSAGE_FORMAT, parentCategoryId));
	}
}
