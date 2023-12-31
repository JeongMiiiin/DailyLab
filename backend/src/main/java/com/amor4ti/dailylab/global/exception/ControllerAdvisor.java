package com.amor4ti.dailylab.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amor4ti.dailylab.global.response.CommonResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResponse customExceptionHandler(CustomException e){
		CommonResponse response = new CommonResponse();
		response.setCode(e.getExceptionStatus().getCode());
		response.setMessage(e.getExceptionStatus().getMessage());
		return response;
	}

	/* 어디에서도 잡지 못한 예외 핸들링 */
	@ExceptionHandler(Exception.class)
	public CommonResponse exceptionHandler(Exception e) {
		CommonResponse response = new CommonResponse();
		response.setCode(ExceptionStatus.EXCEPTION.getCode());
		response.setMessage(ExceptionStatus.EXCEPTION.getMessage());
		return response;
	}
}
