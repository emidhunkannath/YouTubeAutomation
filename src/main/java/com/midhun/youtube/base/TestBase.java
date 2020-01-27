package com.midhun.youtube.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.midhun.youtube.util.TestUtil;
import com.midhun.youtube.util.WebEventListener;

public class TestBase {

	public static TestUtil testUtil;
	public static Properties prop;
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger logger = LogManager.getLogger();

	public TestBase() throws FileNotFoundException, IOException {

		if (testUtil == null)
			testUtil = new TestUtil();
		if (prop == null)
			prop = testUtil.getProperties();
	}

	public void init() throws InterruptedException, IOException {

		driver = testUtil.launchApplication(driver, e_driver, eventListener, prop.getProperty("browser"), prop.getProperty("url"));
	}
}