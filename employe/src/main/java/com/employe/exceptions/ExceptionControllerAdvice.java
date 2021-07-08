package com.employe.exceptions;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employe.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {
// Handle invalid @Valid objects
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
	
//	handle @validated contraints fail
	  @ExceptionHandler(ConstraintViolationException.class)
	  public  ResponseEntity<ErrorMessage> handleConstraintViolationExceptions(
	      ConstraintViolationException ex) {
		  
		  ErrorMessage error = new ErrorMessage();
		  error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		  error.setMessage(ex.getConstraintViolations().stream().map(x->ex.getMessage()).collect(Collectors.joining(",")));
		 String eMess = (ex.getConstraintViolations().stream().map(x->ex.getMessage()).collect(Collectors.joining(",")));
		  error.setMessage(eMess);
		 return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	  }

}
