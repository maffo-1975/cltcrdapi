package com.marciocazevedo.cltcrdapi.services.exceptions;

public class NonexistentIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NonexistentIdException(String message) {
		super(message);
	}

}
