package com.midhun.youtube.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.midhun.youtube.util.ExtentReporterNG;

public class ResultPage extends ExtentReporterNG {

	@FindBy(how = How.XPATH, using = "//ytd-channel-name[@id='channel-title']/div/div/yt-formatted-string")
	private WebElement lblChannelTitle;

	public ResultPage() throws IOException {

		PageFactory.initElements(driver, this);
	}

	public String getResultPageTitle() {

		return driver.getTitle();
	}

	public ChannelPage selectChannel() throws InterruptedException, IOException {

		lblChannelTitle.click();
		Thread.sleep(1000);
		return new ChannelPage();
	}
}