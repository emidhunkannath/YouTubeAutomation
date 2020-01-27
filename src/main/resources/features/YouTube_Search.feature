@UAT
Feature: YouTube Search

@SimpleCucumberScenario1 @Scenario_003
Scenario Outline: YouTube Search Validation
	Given read test data from exel file for the "<TestCaseId>"
	When open browser and navigate to application
	Then search for a channel
	Then close the browser
	Examples:
		| TestCaseId   |
		| TestCase_003 |
		
@SimpleCucumberScenario2 @Scenario_004
Scenario Outline: YouTube Channel Title Validation
	Given read test data from exel file for the "<TestCaseId>"
	When open browser and navigate to application
	Then search for a channel
	When user select a channel
	Then validate channel title
	Then close the browser
	Examples:
		| TestCaseId   |
		| TestCase_004 |
				
@ReadTestDataFromExcel1 @Scenario_001
Scenario Outline: Read Test Data From Excel File
	Given read test data from exel file for the "<TestCaseId>"
	Examples:
		| TestCaseId   |
		| TestCase_001 |
						
@ReadTestDataFromExcel2 @Scenario_002
Scenario Outline: Read Test Data From Excel File for specific skills
	Given read test data from exel file for the "<TestCaseId>"
	Then read test data for skill two
	Then read test data for skill three
	Examples:
		| TestCaseId   |
		| TestCase_002 |
								
@DataDrivenTesting @Scenario_005
Scenario Outline: YouTube Channel Title Validation For Multiple Search String
	Given read test data from exel file for the "<TestCaseId>"
	When open browser and navigate to application
	Then search for a channel
	When user select a channel
	Then validate channel title
	Then close the browser
	Examples:
		| TestCaseId   |
		| TestCase_005 |
		| TestCase_006 |