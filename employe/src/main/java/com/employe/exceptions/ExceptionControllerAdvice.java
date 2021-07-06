package com.employe.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employe.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> customValidationErrorHandling(MethodArgumentNotValidException ex)
	{
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchEmployeException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(NoSuchEmployeException ex)
	{
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.OK);
	}
	
	  @ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<Object> handleConstraintViolationExceptions(
	      ConstraintViolationException ex) {
	    String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	  }

}
