package jp.wsf.jDriver.test;

import static jp.wsf.jDriver.result.ResultCount.*;

public class testLog {
	public static void main(String[] args) {
		print("log");
		print("log","caption");
		printResult("log");
		printResult("log","caption");
		printError("log");
		printError("log","caption");
		printWarn("log");
		printWarn("log","caption");
		printFatal("log");
		printFatal("log","caption");
	}
}
