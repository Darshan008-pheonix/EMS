package com.ems.amz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExpectionHandler {

	
	@ExceptionHandler(InvalidLeaveApllicationExcpetion.class)
	ResponseEntity leaveApplicationEx() {
		return new ResponseEntity("Inavlid Application",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	ResponseEntity EmployeeNotFound() {
		return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
	}
}
