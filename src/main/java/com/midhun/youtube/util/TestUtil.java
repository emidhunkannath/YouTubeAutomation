package com.midhun.youtube.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.github.bonigarcia.wdm.WebDriverManager;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static io.github.bonigarcia.wdm.DriverManagerType.EDGE;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;

public class TestUtil {

	private TreeMap<String, String> testData = new TreeMap<String, String>();
	private TreeMap<String, String> testInfo = new TreeMap<String, String>();

	public Properties getProperties() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/config/config.properties"));
		return prop;
	}

	public WebDriver launchApplication(WebDriver driver, EventFiringWebDriver e_driver, WebEventListener eventListener, String browserName, String url) throws InterruptedException, IOException {

		switch (browserName) {

		case "Chrome":
			WebDriverManager.getInstance(CHROME).setup();
			driver = new ChromeDriver();
			break;
		case "IE":
			WebDriverManager.getInstance(IEXPLORER).setup();
			driver = new InternetExplorerDriver();
			break;
		case "Firefox":
			WebDriverManager.getInstance(FIREFOX).setup();
			driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.getInstance(EDGE).setup();
			driver = new EdgeDriver();
		default:
			return null;
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(10000);
		return driver;
	}

	public void setTestData(String testDataFilePath, String sheetName, String testCaseId) throws FilloException {

		Fillo fillo = new Fillo();
		Recordset recordset = null;
		String key, value;
		testData.clear();
		String query = String.format("SELECT * FROM %s WHERE TestCaseId = '%s'", sheetName, testCaseId);
		Connection conn = fillo.getConnection(testDataFilePath);
		recordset = conn.executeQuery(query);
		while (recordset.next()) {

			ArrayList<String> f = new ArrayList<String>(recordset.getFieldNames());
			key = null;
			value = null;
			for (int i = 0; i < f.size(); i++) {

				if (i < 4) {

					key = f.get(i);
					value = recordset.getField(f.get(i));
				}
				else if (f.get(i).startsWith("Key_")) {

					key = recordset.getField(f.get(i));
					value = recordset.getField(f.get(i + 1));
				}
				else
					continue;
				if (!key.equals(""))
					testData.put(key, value);
			}
		}
		conn.close();
	}

	public String getTestData(String key) {

		return testData.get(key);
	}

	public void setTestInfo(String testCaseId, String scenario, String feature) {

		testInfo.clear();
		testInfo.put("TestCaseId", testCaseId);
		testInfo.put("Scenario", scenario);
		testInfo.put("Feature", feature);
	}

	public String getTestInfo(String key) {

		return testInfo.get(key);
	}
}