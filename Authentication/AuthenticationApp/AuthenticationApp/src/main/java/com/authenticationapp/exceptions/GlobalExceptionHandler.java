package com.authenticationapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(
	            MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return ResponseEntity.badRequest().body(errors);
	    }

	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorResponse> handleRuntimeException(
	            RuntimeException ex, 
	            WebRequest request) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(), 
	            ex.getMessage(), 
	            request.getDescription(false)
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGlobalException(
	            Exception ex, 
	            WebRequest request) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	            ex.getMessage(), 
	            request.getDescription(false)
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // Error Response DTO
	    public static class ErrorResponse {
	        private int status;
	        private String message;
	        private String path;

	        public ErrorResponse(int status, String message, String path) {
	            this.status = status;
	            this.message = message;
	            this.path = path;
	        }

	        // Getters and Setters
	        public int getStatus() { return status; }
	        public void setStatus(int status) { this.status = status; }
	        public String getMessage() { return message; }
	        public void setMessage(String message) { this.message = message; }
	        public String getPath() { return path; }
	        public void setPath(String path) { this.path = path; }
	    }
	
}
