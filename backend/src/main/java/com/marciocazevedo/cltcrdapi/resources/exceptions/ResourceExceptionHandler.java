package com.marciocazevedo.cltcrdapi.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marciocazevedo.cltcrdapi.services.exceptions.NonexistentIdException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NonexistentIdException.class)
	public ResponseEntity<StandardError> handleNonexistentIdException(NonexistentIdException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), "Data not found", e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
