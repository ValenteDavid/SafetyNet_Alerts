package com.safetynet.safetynetalerts.controller.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	
	private static final Logger log = LoggerFactory.getLogger(NotFoundException.class);

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
		log.error(message);
	}

}
