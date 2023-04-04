package com.daedonginc.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daedonginc.popup.excepion.StartDateLaterThanEndDateException;
import com.daedonginc.service.category.exception.DetailLevelIsCanNotHaveSubLevelException;
import com.daedonginc.service.category.exception.NotFoundCategoryException;
import com.daedonginc.service.category.exception.NotFoundParentCategoryException;
import com.daedonginc.service.companyhistory.exception.NotFoundCompanyHistoryException;
import com.daedonginc.service.popup.exception.NotFoundPopupException;

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundPopupException.class)
	ErrorResponseDto handleException(NotFoundPopupException e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StartDateLaterThanEndDateException.class)
	ErrorResponseDto handleException(StartDateLaterThanEndDateException e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundCategoryException.class)
	ErrorResponseDto handleException(NotFoundCategoryException e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundParentCategoryException.class)
	ErrorResponseDto handleException(NotFoundParentCategoryException e) {
		return ErrorResponseDto.toErrorResponseDto(e, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DetailLevelIsCanNotHaveSubLevelException.class)
	ErrorResponseDto handleException(DetailLevelIsCanNotHaveSubLevelException e) {
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
