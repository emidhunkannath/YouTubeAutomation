package com.midhun.youtube.testrunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/main/resources/features",
					glue = { "com.midhun.youtube.stepdef" },
					tags = { "" },
					plugin = { "pretty", "html:target/cucumber-report", "json:target/cucumber-report/cucumber.json" },
					monochrome = true,
					strict = true,
					dryRun = false)

public class Runner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {

		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "Cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) throws Throwable {

		testNGCucumberRunner.runScenario(pickleEvent.getPickle());
	}

	@DataProvider
	public Object[][] scenarios() {

		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {

		testNGCucumberRunner.finish();
	}
}