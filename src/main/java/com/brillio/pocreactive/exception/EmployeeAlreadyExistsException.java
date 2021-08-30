package com.brillio.pocreactive.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
	public EmployeeAlreadyExistsException(String message){
		super(message);
	}
}
