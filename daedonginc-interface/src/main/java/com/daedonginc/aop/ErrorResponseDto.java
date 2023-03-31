package com.daedonginc.aop;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/03/30
 */
public record ErrorResponseDto(
		String errorSimpleName,
		String errorMessage,
		LocalDateTime timestamp
) {
	public static ErrorResponseDto toErrorResponseDto(Exception e, String errorMessage) {
		return new ErrorResponseDto(
				e.getClass().getSimpleName(),
				errorMessage,
				LocalDateTime.now()
		);
	}
}
