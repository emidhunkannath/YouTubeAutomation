package com.midhun.youtube.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.midhun.youtube.util.ExtentReporterNG;

public class HomePage extends ExtentReporterNG {

	@FindBy(how = How.ID, using = "search")
	private WebElement txtSearch;

	@FindBy(how = How.ID, using = "search-icon-legacy")
	private WebElement btnSearch;

	public HomePage() throws IOException {

		PageFactory.initElements(driver, this);
	}

	public String getHomePageTitle() {

		return driver.getTitle();
	}

	public ResultPage search(String channel) throws InterruptedException, IOException {

		txtSearch.sendKeys(channel);
		btnSearch.click();
		Thread.sleep(10000);
		return new ResultPage();
	}
}