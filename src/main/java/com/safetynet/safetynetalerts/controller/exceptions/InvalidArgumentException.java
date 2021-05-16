package com.safetynet.safetynetalerts.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException{
	
	public static enum typeArg{
		STATION_NUMBER("The station number cannot be less than or equal to 0 : ");
		
		private String message;

		typeArg(String message) {
			this.message=message;
		}

		public String getMessage() {
			return message;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(typeArg typeArg, int stationNumber) {
		super(typeArg.getMessage() + stationNumber);
	}
}


