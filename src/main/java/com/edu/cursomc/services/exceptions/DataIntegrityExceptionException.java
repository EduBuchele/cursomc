package com.edu.cursomc.services.exceptions;

public class DataIntegrityExceptionException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExceptionException(String msg) {
		super(msg);
	}

	public DataIntegrityExceptionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
