package com.daedonginc.service.product.exception;

/**
 * @author domo
 * Created on 2023/03/25
 */
public class NotFoundProductException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "can not found product [productId: %s]";

	public NotFoundProductException(final Long productId) {
		super(String.format(MESSAGE_FORMAT, productId));
	}
}
