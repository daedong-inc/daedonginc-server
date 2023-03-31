package com.daedonginc.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daedonginc.service.companyhistory.exception.NotFoundCompanyHistoryException;

/**
 * @author domo
 * Created on 2023/03/30
 */
@RestControllerAdvice
public class ApiRestControllerAdvice {
	/**
	 * 400
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ErrorResponseDto handleException(MethodArgumentNotValidException e) {
		String filedName = e.getBindingResult().getFieldError().getField();
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return ErrorResponseDto.toErrorResponseDto(e, filedName + " : " + message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundCompanyHistoryException.class)
	ErrorResponseDto handleException(NotFoundCompanyHistoryException e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}

	/**
	 * 500
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	ErrorResponseDto handleException(Exception e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}
}
