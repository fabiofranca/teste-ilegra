package com.ilegra.environment;

public class EnvironmentException extends RuntimeException {

	private static final long serialVersionUID = 7781617448251360936L;
	public EnvironmentException(String msg) { super(msg); }
	public EnvironmentException(Throwable e) { super(e); }
	public EnvironmentException(String message, Throwable cause) { super(message, cause); }

}
