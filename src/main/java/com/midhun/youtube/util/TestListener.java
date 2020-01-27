package com.midhun.youtube.util;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends ExtentReporterNG implements ITestListener {

	public TestListener() throws IOException {}

	@Override
	public void onTestStart(ITestResult result) {

		logger.info("Scenario started " + result.getMethod().getConstructorOrMethod().getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		logger.info("Scenario passed " + result.getMethod().getConstructorOrMethod().getName() + " " + testUtil.getTestInfo("Scenario"));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		logger.error("Scenario failed " + result.getMethod().getConstructorOrMethod().getName() + " " + testUtil.getTestInfo("Scenario"), result.getThrowable());
		try {

			testStepHandle("FAIL", result.getThrowable());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		logger.info("Scenario skipped " + result.getMethod().getConstructorOrMethod().getName() + " " + testUtil.getTestInfo("Scenario"));
		try {

			testStepHandle("SKIP", result.getThrowable());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		logger.info("Scenario failed but has been annotated with success percentage " + result.getMethod().getConstructorOrMethod().getName() + " " + testUtil.getTestInfo("Scenario"));
	}

	@Override
	public void onStart(ITestContext context) {

		logger.info("Test started " + context.getName());
		extent = setUp();
	}

	@Override
	public void onFinish(ITestContext context) {

		logger.info("Test finished " + context.getName());
		extent.flush();
	}
}