package com.brillio.pocreactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(InsufficientDataException.class)
	public ResponseEntity<?> insufficientDataPushed(InsufficientDataException insufficientDataException){
		return new ResponseEntity<>(insufficientDataException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<?> insufficientDataPushed(EmployeeAlreadyExistsException employeeAlreadyExistsException){
		return new ResponseEntity<>(employeeAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> insufficientDataPushed(EmployeeNotFoundException employeeNotFoundException){
		return new ResponseEntity<>(employeeNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
