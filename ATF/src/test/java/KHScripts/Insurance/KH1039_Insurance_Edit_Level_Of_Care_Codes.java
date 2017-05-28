package KHScripts.Insurance;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

public class KH1039_Insurance_Edit_Level_Of_Care_Codes {

	/************************************************************************************
	'Class name                     : 	KH1039_Insurance_Edit_Level_Of_Care_Codes
	'JIRA ID						:	KH-1039
	'Description                    : 	Creates an insurance and edits the level of care codes on the insurance rates screen. 
	'Input Parameters           	: 	Insurance data
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	Verify that level of care codes are being saved for insurance rates
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		Test();
	}

	
	@Test
	public static void Test() throws Exception {
	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
		Report.generateReportsFile("html","KH1039_Insurance_Edit_Level_Of_Care_Codes");
		Report.SetTestName("KH1039_Insurance_Edit_Level_Of_Care_Codes","Verify level of care codes saved for insurance rates.");
		Report.assignCategory("TBD");
		Report.assignCategory("Not Ready");
		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "KHInsurance\\KH1039_Insurance_Edit_Level_Of_Care_Codes.xlsx";
		String dataSheetName = "CreateInsurance";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/
       
        //@Step - Create New Insurance
		Datatable.loadDataSheet(dataFileName, "CreateInsurance");
		KH.Menu.TopMenu.Select(driver, "File/New/Insurance");
		KH.Insurance.AddNewInsurance.FillAddNewInsurance(driver);
		KH.Insurance.AddNewInsurance.btn_NI_AddInsurance(driver).click();
		Waits.ForGlobalAjaxLoader(driver);

		//@Step - Edit Insurance Rates
		Datatable.loadDataSheet(dataFileName, "LevelOfCareRates");
        Thread.sleep(Waits.getSleepLevelTwo());
		KH.Menu.TopMenu.Select(driver, "Edit/Rates");
 	   	Waits.ForGlobalAjaxLoader(driver);
 	   	KH.Insurance.InsuranceRates.div_LOC_Heading(driver).click();
		KH.Insurance.InsuranceRates.FillLevelOfCareRates(driver);
		KH.Insurance.InsuranceRates.btn_Update(driver).click();
 	   	Waits.ForGlobalAjaxLoader(driver);
		
		//@Step - Verify level of care codes saved
		KH.Menu.TopMenu.Select(driver, "Edit/Detail");
 	   	Waits.ForGlobalAjaxLoader(driver);
		Report.attachScreenShotToReport(driver);
		KH.Menu.TopMenu.Select(driver, "Edit/Rates");
 	   	Waits.ForGlobalAjaxLoader(driver);
 	   	KH.Insurance.InsuranceRates.div_LOC_Heading(driver).click();
		Report.attachScreenShotToReport(driver);
	}

	@AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
