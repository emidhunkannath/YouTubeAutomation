package com.midhun.youtube.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.midhun.youtube.base.TestBase;

public class ExtentReporterNG extends TestBase {

	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static ExtentTest loginfo = null;

	public ExtentReporterNG() throws IOException {}

	public static ExtentReports setUp() {

		String reportLocation = prop.getProperty("extentreportpath");
		report = new ExtentHtmlReporter(reportLocation);
		report.loadXMLConfig(prop.getProperty("extentconfigpath"));
		report.start();
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", prop.getProperty("applicationname"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		return extent;
	}

	public void testStepHandle(String teststatus, Throwable throwable) throws IOException {

		switch (teststatus) {

		case "FAIL":
			loginfo.fail(MarkupHelper.createLabel("Test case failed", ExtentColor.RED));
			loginfo.error(throwable.fillInStackTrace());
			if (driver != null) {

				loginfo.addScreenCaptureFromPath(captureScreenshot(), "Screenshot");
				driver.quit();
			}
			break;
		case "PASS":
			loginfo.pass("Test case passed");
			break;
		case "SKIP":
			loginfo.skip(MarkupHelper.createLabel("Test case skipped", ExtentColor.BLUE));
			loginfo.info(throwable.fillInStackTrace());
		default:
			break;
		}
	}

	private String captureScreenshot() {

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File target = new File("test-output/Screenshots/" + getCurrentDateTime() + ".png");
		src.renameTo(target);
		return target.getAbsolutePath();
	}

	private String getCurrentDateTime() {

		String dateTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		dateTime = sdf.format(new Date());
		return dateTime;
	}
}