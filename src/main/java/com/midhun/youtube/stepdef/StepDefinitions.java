package com.midhun.youtube.stepdef;

import java.io.IOException;
import org.testng.Assert;
import org.testng.SkipException;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.codoid.products.exception.FilloException;
import com.midhun.youtube.pages.ChannelPage;
import com.midhun.youtube.pages.HomePage;
import com.midhun.youtube.pages.ResultPage;
import com.midhun.youtube.util.ExtentReporterNG;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends ExtentReporterNG {

	private HomePage homePage;
	private ResultPage resultPage;
	private ChannelPage channelPage;
	String sce = null, feature = null;

	public StepDefinitions() throws IOException {}

	@When("^open browser and navigate to application$")
	public void open_browser_and_navigate_to_application() throws ClassNotFoundException, IOException, InterruptedException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("When"), "open browser and navigate to application");
		init();
		Assert.assertEquals((homePage = new HomePage()).getHomePageTitle(), testUtil.getTestData("HomePageTitle"), "Home page title mismatch");
		loginfo.pass("Opened browser and entered URL");
	}

	@Then("^search for a channel$")
	public void search_for_a_channel() throws ClassNotFoundException, InterruptedException, IOException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("Then"), "search for a channel");
		resultPage = homePage.search(testUtil.getTestData("Channel"));
		Assert.assertEquals(resultPage.getResultPageTitle(), testUtil.getTestData("RsltPageTitle"), "Result page title mismatch");
		loginfo.pass("Searched for a channel");
	}

	@When("^user select a channel$")
	public void user_select_a_channel() throws ClassNotFoundException, InterruptedException, IOException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("When"), "user select a channel");
		channelPage = resultPage.selectChannel();
		loginfo.pass("User selected a channel");
	}

	@Then("^validate channel title$")
	public void validate_channel_title() throws ClassNotFoundException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("Then"), "validate channel title");
		Assert.assertEquals(channelPage.getChannelPageTitle(), testUtil.getTestData("ChannelPageTitle"), "Channel title mismatch");
		loginfo.pass("Validated channel title");
	}

	@Given("^read test data from exel file for the \"([^\"]*)\"$")
	public void read_test_data_from_exel_file_for_the(String testCaseId) throws ClassNotFoundException, FilloException {

		loginfo = null;
		testUtil.setTestInfo(testCaseId, sce, feature);
		test = extent.createTest(Feature.class, testUtil.getTestInfo("Feature"));
		test = test.createNode(Scenario.class, testUtil.getTestInfo("Scenario"));
		loginfo = test.createNode(new GherkinKeyword("Given"), "read test data from exel file for the \"" + testUtil.getTestInfo("TestCaseId") + "\"");
		testUtil.setTestData(prop.getProperty("testdatafilepath"), prop.getProperty("testdatasheetname"), testUtil.getTestInfo("TestCaseId"));
		// testUtil.testData.forEach((k, v) -> System.out.println(k + " - " + v));
		if (testUtil.getTestData("Run").equals("No"))
			throw new SkipException("Test case skipped");
		loginfo.pass("Read test data from excel file");
	}

	@Then("^read test data for skill two$")
	public void read_test_data_for_skill_two() throws ClassNotFoundException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("Then"), "read test data for skill two");
		System.out.println(testUtil.getTestData("Tech3"));
		loginfo.pass("Read test data for skill two");
	}

	@Then("^read test data for skill three$")
	public void read_test_data_for_skill_three() throws ClassNotFoundException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("Then"), "read test data for skill three");
		System.out.println(testUtil.getTestData("Tech5"));
		loginfo.pass("Read test data for skill three");
	}

	@Then("^close the browser$")
	public void close_the_browser() throws ClassNotFoundException {

		loginfo = null;
		loginfo = test.createNode(new GherkinKeyword("Then"), "close the browser");
		if (driver != null)
			driver.quit();
		loginfo.pass("Browser closed");
	}

	@Before
	public void before(io.cucumber.java.Scenario scenario) {

		feature = scenario.getUri().toString().substring(scenario.getUri().toString().lastIndexOf("/") + 1, scenario.getUri().toString().indexOf(".feature")).replace('_', ' ');
		sce = scenario.getName();
	}

	@After
	public void after() {}
}