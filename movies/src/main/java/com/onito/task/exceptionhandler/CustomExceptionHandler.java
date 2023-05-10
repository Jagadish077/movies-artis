package com.onito.task.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onito.task.model.ErrorResponse;
import com.onito.task.model.GenericResponse;
import com.onito.task.model.GenericResponseModel;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(InvalidRequestException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> invalidDate(InvalidRequestException ex) {
		return new GenericResponse<GenericResponseModel<Object,ErrorResponse>>(new GenericResponseModel<Object, ErrorResponse>(null, new ErrorResponse(ex.getMessage())), HttpStatus.BAD_REQUEST);
	}
	
}
