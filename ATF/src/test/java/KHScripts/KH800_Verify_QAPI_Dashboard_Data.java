package KHScripts;


import components.Browser;
import components.Report;

import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;



public class KH800_Verify_QAPI_Dashboard_Data{


		/****************************************************************
		 *Class name		: KH_HIS_KH800
		 *Description		: Test to verify QAPI Restricted Clinic Setting and screen
		 *Input Parameters	: None
		 *Output Parameters	: None
		 *Assumptions		: User has permission to view Quality Dashboard
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
			Report.generateReportsFile("html","KH_QAPI");
			Report.SetTestName("KH_CreateRNIA","KH_View QAPI");
			Report.assignCategory("TBD");
			Report.assignCategory("Not Ready");
			//@Open Application and submit credentials
			KH.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			//String dataFileName = "KHClinical\\HISMeasures\\KH_HISMeasures_KH793.xlsx";
			//String dataSheetName = "RNIA";
			//Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/

			Database.Queries.enableQAPIRestrictedClinicSetting();

			KH.Menu.TopMenu.Select(driver, "Go To/Reports '/ Admin");
			
			KH.ReportsAdmin.ViewQualityDashboard.getQualityDashboardElement(driver).click();
			
			components.Verify.ExactPageTitle(driver, "Quality Dashboard | Kinnser Software");
						
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);
			
	}

	@AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
