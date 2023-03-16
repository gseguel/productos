package com.gseguel.productos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098756128826720553L;

	public EntityNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
