package jp.wsf.jDriver.result;

import jp.wsf.jDriver.exception.CompareFailureException;
import jp.wsf.jDriver.exception.TestFailureException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ResultCount {
	private static int invokeCount = 0;

	private static int failureCount = 0;

	private static int exceptionCount = 0;

	private static boolean verbose = true;

	private static boolean resultverbose = true;
	
	private static Logger log= Logger.getLogger("jDriver"); 
	
	private static String propetiesURL = System.getProperty("user.dir") + "\\src\\log4j.properties";
	
	static{
		PropertyConfigurator.configure(propetiesURL);
	}
	/**
	 * Output Message for console.
	 * 
	 * @param Message
	 */
	static public void print(String Message) {
		if (verbose) {
			log.debug(Message);
		}
	}

	/**
	 * Output Message for console.
	 * 
	 * @param Message
	 * @param Caption
	 */
	static public void print(String Message, String Caption) {
			print(Caption + ":\t" + Message);
	}

	/**
	 * Output the Result Message Message to console.
	 * 
	 * @param Message
	 */
	static public void printResult(String Message) {
		if (resultverbose) {
			log.info(Message);
		}
	}

	/**
	 * Output the Result Message Message to console.
	 * 
	 * @param Message
	 * @param Caption
	 */
	static public void printResult(String Message, String Caption) {
			printResult(Caption + ":\t" + Message);
	}
	
	/**
	 * Output the Error Message Message to console.
	 * 
	 * @param Message
	 */
	static public void printError(String Message) {
		if (resultverbose) {
			log.error(Message);
		}
	}

	/**
	 * Output the Error Message Message to console.
	 * 
	 * @param Message
	 * @param Caption
	 */
	static public void printError(String Message, String Caption) {
		printError(Caption + ":\t" + Message);
	}
	
	/**
	 * Output the Warn Message Message to console.
	 * 
	 * @param Message
	 */
	static public void printWarn(String Message) {
		if (resultverbose) {
			log.warn(Message);
		}
	}

	/**
	 * Output the Warn Message Message to console.
	 * 
	 * @param Message
	 * @param Caption
	 */
	static public void printWarn(String Message, String Caption) {
		printWarn(Caption + ":\t" + Message);
	}
	
	/**
	 * Output the Fatal Message Message to console.
	 * 
	 * @param Message
	 */
	static public void printFatal(String Message) {
		if (resultverbose) {
			log.fatal(Message);
		}
	}

	/**
	 * Output the Fatal Message Message to console.
	 * 
	 * @param Message
	 * @param Caption
	 */
	static public void printFatal(String Message, String Caption) {
		printFatal(Caption + ":\t" + Message);
	}

	/**
	 * Increment failureCount
	 * @throws CompareFailureException 
	 */
	static public void fail()  {
		fail("");
	}

	/**
	 * Increment failureCount
	 * 
	 * @param Message
	 * @throws CompareFailureException 
	 */
	static public void fail(String Message) {
		addFailCount();
		throw new TestFailureException(Message);
	}
	
	static public void addFailCount(){
		failureCount += 1;
	}

	/**
	 * Increment exceptionCount
	 * @throws CompareFailureException 
	 */
	static public void addCountEx() throws CompareFailureException {
		exceptionCount += 1;
	}

	/**
	 * Increment exceptionCount
	 * 
	 * @param Message
	 * @throws CompareFailureException 
	 */
	static public void addCountEx(String Message) {
		addCountEx();
		print(Message);
	}
	
	public static int getFailureCount() {
		return failureCount;
	}

	public static int getexceptionCount() {
		return exceptionCount;
	}

	public static boolean isVerbose() {
		return verbose;
	}

	public static void setVerbose(boolean verbose) {
		ResultCount.verbose = verbose;
	}

	public static boolean isResultverbose() {
		return resultverbose;
	}

	public static void setResultverbose(boolean resultverbose) {
		ResultCount.resultverbose = resultverbose;
	}

	public static int getInvokeCount() {
		return invokeCount;
	}

	public static void setInvokeCount(int invokeCount) {
		ResultCount.invokeCount = invokeCount;
	}
	
	static public void addCountInvoke() {
		invokeCount += 1;
	}
	
	/**
	 * Output the test result to the console.
	 */
	public static void PrintTestResult() {
		print("");
		printResult(String.valueOf(invokeCount), "Invoke Method");
		printResult(String.valueOf(failureCount), "Failure case");
		printResult(String.valueOf(exceptionCount), "Exception case");
		if (failureCount >= 1 || exceptionCount >= 1) {
			printResult("Test failure.");
		} else {
			printResult("Test Success.");
		}
	}
}
