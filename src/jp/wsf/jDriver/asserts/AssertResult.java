package jp.wsf.jDriver.asserts;

import java.util.List;

import jp.wsf.jDriver.exception.CompareFailureException;

import static jp.wsf.jDriver.result.ResultCount.*;

public class AssertResult {

	static public boolean assertResult(String expect, String actual) {
		boolean result = false;
		if (expect.equals(actual)) {
			result = true;
		} else {
			throw new CompareFailureException("Different from the expected value. expect:" + expect
					+ " actual:" + actual);
		}
		return result;
	}

	static public boolean assertResult(int expect, int actual) {
		boolean result = false;
		if (expect == actual) {
			result = true;
		} else {
			throw new CompareFailureException("Different from the expected value. expect:" + expect
					+ " actual:" + actual);
		}
		return result;
	}

	static public boolean assertResult(boolean actual) {
		boolean result = false;
		if (actual == true) {
			result = true;
		} else {
			throw new CompareFailureException("acutual is not true ");
		}
		return result;
	}

	static public boolean assertResult(String message, String expect,
			String actual) {
		boolean result = false;
		if (expect.equals(actual)) {
			if (message.length() > 0)
				print(message);
			result = true;
		} else {
			throw new CompareFailureException("Different from the expected value. expect:" + expect
					+ " actual:" + actual);
		}
		return result;
	}

	static public boolean assertResult(String message, int expect, int actual) {
		boolean result = false;
		if (expect == actual) {
			if (message.length() > 0)
				print(message);
			result = true;
		} else {
			throw new CompareFailureException("Different from the expected value. expect:" + expect
					+ " actual:" + actual);
		}
		return result;
	}

	/**
	 * if the expected value of in the Array is to exist,this returns true.
	 * 
	 * @param expect
	 *            String,actuals String[]
	 * @return result boolean
	 */
	static public boolean assertInArray(String expect, String[] actuals) {
		boolean result = false;
		boolean inArray = false;

		for (String actual : actuals) {
			if (actual.indexOf(expect) >= 0) {
				inArray = true;
			}
		}
		if (inArray) {
			result = true;
		} else {
			throw new CompareFailureException("Not Found expected value in Array. expect:" + expect);
		}
		return result;
	}

	/**
	 * if the expected value of in the List is to exist,this returns true.
	 * 
	 * @param expect
	 *            String,actuals List<String>
	 * @return result boolean
	 */
	static public boolean assertInList(String expect, List<String> actuals) {
		boolean result = false;
		boolean inArray = false;
		for (String actual : actuals) {
			if (actual.indexOf(expect) >= 0) {
				inArray = true;
			}
		}
		if (inArray) {
			result = true;
		} else {
			throw new CompareFailureException("Not Found expected value in Array. expect:" + expect);
		}
		return result;
	}

	
}
