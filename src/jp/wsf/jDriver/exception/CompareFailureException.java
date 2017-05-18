package jp.wsf.jDriver.exception;

public class CompareFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompareFailureException(){
		super();
	}
	
	public CompareFailureException(String message){
		super(message);
	}

}
