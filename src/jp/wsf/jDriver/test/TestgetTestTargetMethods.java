package jp.wsf.jDriver.test;

import static jp.wsf.jDriver.asserts.AssertResult.assertResult;
import jp.wsf.jDriver.annotations.Ignore;
import jp.wsf.jDriver.annotations.ParameterSource;
import jp.wsf.jDriver.annotations.Test;
import jp.wsf.jDriver.core.jDriver;
import jp.wsf.jDriver.method.MethodInfoDTO;
import jp.wsf.jDriver.method.TargetMethodCollecter;
import jp.wsf.jDriver.result.ResultCount;

public class TestgetTestTargetMethods {
	public static void main(String[] args) {
		jDriver jd = new jDriver();
		jd.execute();
	}
	
	@Test
	public void getMethodDtoTestCase1() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		TestCase1 testcase = new TestCase1();
		MethodInfoDTO actualOne=  TargetMethodCollecter.getTestTargetMethods(testcase);
		String[] actualTwo = {"testOne","testTwo"};
		assertResult(actualOne.getTargetClassSimpleName(),"TestCase1");
		ResultCount.print(actualOne.getTargetClassSimpleName(),"MethodInfoDTO.targetClass");
		for(int i=0;i<actualTwo.length;i++){
			assertResult(actualOne.getMethodInfoList()[i].getMethodName(),actualTwo[i]);
			ResultCount.print(actualOne.getMethodInfoList()[i].getMethodName(),"MethodInfoDTO.MethodInfo" + i);
		}
	}
	
	@Test
	public void getMethodDtoTestCase2() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		TestCase2 testcase = new TestCase2();
		MethodInfoDTO actualOne=  TargetMethodCollecter.getTestTargetMethods(testcase);
		String[] actualTwo = {"testOne"};
		assertResult("TestCase2",actualOne.getTargetClassSimpleName());
		ResultCount.print(actualOne.getTargetClassSimpleName(),"MethodInfoDTO.targetClass");
		for(int i=0;i<actualTwo.length;i++){
			assertResult(actualOne.getMethodInfoList()[i].getMethodName(),actualTwo[i]);
			ResultCount.print(actualOne.getMethodInfoList()[i].getMethodName(),"MethodInfoDTO.MethodInfo" + i);
		}
	}
	
	@Test
	public void getMethodDtoTestCase3() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		TestCase3 testcase = new TestCase3();
		MethodInfoDTO actualOne=  TargetMethodCollecter.getTestTargetMethods(testcase);
		String[] actualTwo = {"testOne","testTwo"};
		String[] actualThree = {"","testParams"};
		assertResult("TestCase3",actualOne.getTargetClassSimpleName());
		ResultCount.print(actualOne.getTargetClassSimpleName(),"MethodInfoDTO.targetClass");
		for(int i=0;i<actualTwo.length;i++){
			assertResult(actualOne.getMethodInfoList()[i].getMethodName(),actualTwo[i]);
			ResultCount.print(actualOne.getMethodInfoList()[i].getMethodName(),"MethodInfoDTO.MethodInfo" + i);
			assertResult(actualThree[i],actualOne.getMethodInfoList()[i].getParamerterSourceName());
			ResultCount.print(actualOne.getMethodInfoList()[i].getParamerterSourceName(),"MethodInfoDTO.ParamerterSourceName");
		}

	}
	
	
	class TestCase1{

		@Test
		public void testOne(){
			
		}
		
		@Test
		public void testTwo(){
			
		}
	}

	class TestCase2{
		@Test
		public void testOne(){
			
		}
		
		@Ignore
		public void testTwo(){
			
		}
	}

	class TestCase3{
		
		@ParameterSource(name="testTwo-params")
		public Object testParams(){
			Object[][] params = {
					{"hello"," new world"},{"114","514"},{"‰ä‚ª¢‚Ì","t‚ª—ˆ‚½‚Ÿ‚Ÿ‚ ‚ŸII"},
			};
			return params;
		}
		
		@Test
		public void testOne(){
			
		}
		
		@Test(ParameterSource="testTwo-params")
		public String testTwo(String a,String b){
			return a+b;
		}
	}
}

