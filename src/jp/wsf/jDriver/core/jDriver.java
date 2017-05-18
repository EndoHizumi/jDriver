package jp.wsf.jDriver.core;

import static jp.wsf.jDriver.asserts.AssertResult.assertResult;
import static jp.wsf.jDriver.result.ResultCount.PrintTestResult;
import static jp.wsf.jDriver.result.ResultCount.addCountEx;
import static jp.wsf.jDriver.result.ResultCount.addCountInvoke;
import static jp.wsf.jDriver.result.ResultCount.printError;
import static jp.wsf.jDriver.result.ResultCount.printResult;
import static jp.wsf.jDriver.result.ResultCount.setResultverbose;
import static jp.wsf.jDriver.result.ResultCount.setVerbose;
import static jp.wsf.jDriver.result.ResultCount.printFatal;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import jp.wsf.jDriver.exception.CompareFailureException;
import jp.wsf.jDriver.exception.TestExecuteException;
import jp.wsf.jDriver.exception.TestFailureException;
import jp.wsf.jDriver.method.Invoker;
import jp.wsf.jDriver.method.MethodInfoDTO;
import jp.wsf.jDriver.method.TargetMethodCollecter;
import jp.wsf.jDriver.result.ResultCount;
import jp.wsf.jDriver.utils.Utilties;

/**
 * @author winvis
 * 
 */
public final class jDriver {

	private String targetClassName = "";

	public jDriver() {
		Utilties util = new Utilties();
		targetClassName = util.getTargetClassName();
	}

	public jDriver(boolean verbose) {
		setVerbose(verbose);
	}

	public jDriver(boolean verbose, boolean resultverbose) {
		setVerbose(verbose);
		setResultverbose(resultverbose);
	}
	
	public static void main(String args[]) {
		if (args.length == 0) {
			printResult("Please set the class name");
			return;
		} else {
			jDriver jdriver = new jDriver();
			jdriver.execute(args[0]);
		}
	}

	public void execute() {
		execute("");
	}

	public void execute(Object targetClass) {
		execute(targetClass.getClass().getName());
	}

	public void execute(Class targetClass) {
		execute(targetClass.getName());
	}

	public void execute(String ClassName) {

		if (ClassName.length() == 0) {
			if (targetClassName.length() != 0) {
				ClassName = targetClassName;
			} else {
				printError("Please set the class name");
				return;
			}
		}

		Invoker invoke = null;
		
		try {
			// テスト対象のメソッドの実行
			MethodInfoDTO methodInfoDto = TargetMethodCollecter.getTestTargetMethods(ClassName);
			Invoker invoker = new Invoker(methodInfoDto);
			invoker.execute();

		}catch (InvocationTargetException ex){
			// TODO:Dirty.
			try {
				if(ex.getCause() == null){
					addCountEx();
					printFatal(ex.getMessage(),ex.getClass().getSimpleName());
					ex.printStackTrace();
				}else{
					throw (Exception)ex.getCause();
				}
			}catch (CompareFailureException ce) {
				ResultCount.addFailCount();
				printFatal(ce.getMessage(),ce.getClass().getSimpleName());
				ce.printStackTrace();
			}catch (TestFailureException tfe){
				ResultCount.addFailCount();
				printFatal(tfe.getMessage(),tfe.getClass().getSimpleName());
				tfe.printStackTrace();
			}catch (TestExecuteException te){
				ResultCount.addFailCount();
				printFatal(te.getMessage(),te.getClass().getSimpleName());
				te.printStackTrace();
			}catch (Exception e) {
				// テストして成功したのか判断する
				if(invoke != null){
					Class[] exceptions = invoke.getInvokeMethod().getExpectExceptions();
					if (exceptions != null && Arrays.asList(exceptions).contains(e.getClass())) {
						// 期待する例外の場合
						assertResult(true);
						addCountInvoke();
					} else {
						// 期待していない例外の場合
						addCountEx();
						printFatal(e.getMessage(),e.getClass().getSimpleName());
						e.printStackTrace();
					}
				}else{
					addCountEx();
					printFatal(e.getMessage(),e.getClass().getSimpleName());
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			addCountEx();
			printFatal(e.getMessage(),e.getClass().getSimpleName());
			e.printStackTrace();
	}finally {
		// テスト結果の表示
		PrintTestResult();
	}
}
}
