package KHScripts;

import KH.ReportsAdmin.ViewQualityDashboard;
import DataSource.Datatable;
import components.Report;
import components.Waits;
import components.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KH_Dashboard_QAPI_KH1067 {
	
	/****************************************************************
	 *Class name		: KH_Dashboard_QAPI_KH1067
	 *User Story		: KH803
	 *Author			: arun.prasath@kinnser.com
	 *Description		: Test to verify Add New QAPI Goal is working as expected. 
	 *Input Parameters	: None
	 *Output Parameters	: None
	 *Assumptions		: Test Data is present in the Global Sheet.
	 *Use				: N/A
	 *Tags				: KH:SmokeTest
	******************************************************************/

public static void main(String[] args) throws Exception {
	Test();
}
	
@Test
public static void Test() throws Exception {
	
	/******************************************************************
	 * Mandate to call below lines at every test case start up
	 ******************************************************************/	
		//@Reports Configuration
		Report.generateReportsFile("html", "KH_Dashboard_QAPI");
		Report.SetTestName("KH_Dashboard_QAPI","KH_Dashboard_QAPI");
		Report.assignCategory("TBD");
		Report.assignCategory("Not Ready");
		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "KHClinical\\KHDashboard\\KH_QAPIDashboard.xlsx";
		String dataSheetName = "ADDGoal";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
	/****************************************************************/
		
		//@ Navigate the menu and goto the page Reports/Admin
		KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
		ViewQualityDashboard.getQualityDashboardElement(driver).click();
		Waits.ForElementToBeClickable(driver, ViewQualityDashboard.getAddNewQAPIGoalElement(driver));
		ViewQualityDashboard.AddHISGoal(driver);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ViewQualityDashboard.getSuccessAlert(driver).getText(), "New goal added successfully.");
		Waits.ForElementToBeClickable(driver, ViewQualityDashboard.getAddNewQAPIGoalElement(driver));
		ViewQualityDashboard.AddESASGoal(driver);
		sa.assertEquals(ViewQualityDashboard.getSuccessAlert(driver).getText(), "New goal added successfully.");
		Waits.ForElementToBeClickable(driver, ViewQualityDashboard.getAddNewQAPIGoalElement(driver));
		ViewQualityDashboard.AddCustomGoal(driver);
		sa.assertEquals(ViewQualityDashboard.getSuccessAlert(driver).getText(), "New goal added successfully.");
}

@AfterTest(alwaysRun = true)
public static void Teardown() {
    components.Browser.teardownTest();
}

}
