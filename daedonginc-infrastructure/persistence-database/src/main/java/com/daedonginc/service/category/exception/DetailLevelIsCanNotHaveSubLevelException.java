package com.daedonginc.service.category.exception;

/**
 * @author domo
 * Created on 2023/04/04
 */
public class DetailLevelIsCanNotHaveSubLevelException extends RuntimeException {
	public DetailLevelIsCanNotHaveSubLevelException(String message) {
		super(message);
	}
}
