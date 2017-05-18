package jp.wsf.jDriver.exception;

public class TestFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestFailureException(){
		super();
	}
	
	public TestFailureException(String message){
		super(message);
	}

}
