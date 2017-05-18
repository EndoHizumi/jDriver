package jp.wsf.jDriver.test;

public class ExpectExceptionTest {
	public void isPassed(int i) {
		if(i<0){
			 throw new IllegalArgumentException
             ("1ˆÈã‚Ì‚Ì’l‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
		}else{
			throw new NullPointerException();
		}
	}
}
