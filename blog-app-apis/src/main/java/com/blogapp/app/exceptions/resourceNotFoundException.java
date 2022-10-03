package com.blogapp.app.exceptions;

public class resourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldvalue;
	public resourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
}
