package com.yash.ticketbooking.exception;

/**
 * @author yash.ghawghawe
 *
 */
public class TrainNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	String message;
	
	/**
	 * @param message
	 */
	public TrainNotAvailableException(String message){
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
