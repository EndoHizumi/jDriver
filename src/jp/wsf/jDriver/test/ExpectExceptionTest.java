package jp.wsf.jDriver.test;

public class ExpectExceptionTest {
	public void isPassed(int i) {
		if(i<0){
			 throw new IllegalArgumentException
             ("1�ȏ�̂̒l����͂��Ă�������");
		}else{
			throw new NullPointerException();
		}
	}
}
