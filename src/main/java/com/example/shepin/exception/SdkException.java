package com.example.shepin.exception;

/**
 * @author opec
 */
public class SdkException extends RuntimeException {

	private static final long serialVersionUID = -1108392076700488161L;

	public SdkException(String message) {
		super(message);
	}

	public SdkException(String message, Throwable cause) {
		super(message, cause);
	}
}
