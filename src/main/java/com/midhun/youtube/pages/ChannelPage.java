package com.midhun.youtube.pages;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import com.midhun.youtube.util.ExtentReporterNG;

public class ChannelPage extends ExtentReporterNG {

	public ChannelPage() throws IOException {

		PageFactory.initElements(driver, this);
	}

	public String getChannelPageTitle() {

		return driver.getTitle();
	}
}