package com.midhun.youtube.util;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebEventListener extends ExtentReporterNG implements WebDriverEventListener {

	public WebEventListener() throws IOException {}

	@Override
	public void beforeAlertAccept(WebDriver driver) {

		logger.info("Before alert accept");
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {

		logger.info("Alert accepted");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {

		logger.info("Alert dismissed");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {

		logger.info("Before alert dismiss");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

		logger.info("Before navigating to '" + url + "'");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {

		logger.info("Navigated to '" + url + "'");
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {

		logger.info("Navigating back to previous page");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {

		logger.info("Navigated back to previous page");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {

		logger.info("Navigating forward to next page");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {

		logger.info("Navigated forward to next page");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {

		logger.info("Refreshing the page");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {

		logger.info("Page refreshed");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {

		logger.info("Trying to find element By " + by.toString());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {

		logger.info("Found element " + element.toString() + " By " + by.toString());
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {

		logger.info("Trying to click on " + element.toString());
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {

		logger.info("Clicked on " + element.toString());
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

		logger.info("Value of the " + element.toString() + " before any changes made");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

		logger.info("Element value changed to " + element.toString());
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {

		logger.info("Before script " + script);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {

		logger.info("After script " + script);
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

		logger.info("Before switch to window " + windowName);
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {

		logger.info("After switch to window " + windowName);
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {

		logger.error("Exception occured", throwable);
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {

		logger.info("Taking screenshot");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

		logger.info("Screenshot taken");
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {

		logger.info("Before capture text of the element " + element.toString());
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {

		logger.info("Captured text of the element " + element.toString() + " - " + text);
	}
}