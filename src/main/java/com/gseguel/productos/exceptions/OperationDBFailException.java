package com.gseguel.productos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationDBFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605220503212446038L;

	public OperationDBFailException(String errorMessage) {
		super(errorMessage);
	}
}
