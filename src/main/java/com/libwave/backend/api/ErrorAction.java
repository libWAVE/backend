package com.libwave.backend.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.libwave.backend.api.model.ErrorResponse;

@ControllerAdvice
public class ErrorAction {

	private Log log = LogFactory.getLog(this.getClass());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleServiceException(Exception ex) {
		log.error("Error: " + ex.getMessage());
		return new ErrorResponse("SYSTEM ERROR");
	}

}
