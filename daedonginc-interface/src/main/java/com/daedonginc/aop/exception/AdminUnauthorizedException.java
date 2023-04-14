package com.daedonginc.aop.exception;

/**
 * @author domo
 * Created on 2023/04/14
 */
public class AdminUnauthorizedException extends RuntimeException {
	private static final String MESSAGE_FORMAT = "admin unauthorized";

	public AdminUnauthorizedException() {
		super(String.format(MESSAGE_FORMAT));
	}
}
