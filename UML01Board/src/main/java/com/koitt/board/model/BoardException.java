package com.koitt.board.model;

public class BoardException extends RuntimeException {
	
	private Throwable throwable;
	
	public BoardException(String message) {
		super(message);
	}
	
	public BoardException(String message, Throwable throwable) {
		super(message);
		this.throwable = throwable;
	}
	
	@Override
	public synchronized Throwable getCause() {
		return throwable;
	}

}
