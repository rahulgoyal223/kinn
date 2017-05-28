package Scripts.Insurance;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Verify;

public class AM_HH_SMK_CreateInsurance_HH4017 {

	/************************************************************************************
	'Class name                     : 	AM_HH_SMK_CreateInsurance_HH4017
	'JIRA ID						:	HH-4017
	'Description                    : 	This test will create a insurance and verify it is selectable for a patient.
	'Input Parameters           	: 	Payer Type, Invoice Type
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created insurance is visible under primary insurance field in patient creation.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_HH_SMK_CreateInsurance_HH4017();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
	public static void AM_HH_SMK_CreateInsurance_HH4017() throws Exception {
		
		
    String dataSheetName = null;
    
    Report.generateReportsFile("html","AM_HH_SMK_CreateInsurance_HH4017");
    Report.SetTestName("AM_HH_SMK_CreateInsurance_HH4017", "AM_HH_SMK_CreateInsurance_HH4017_verifies that the created insurance is visible under primary insurance field");
    Report.assignCategory("SMK");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Insurance\\Medicare\\AM_HH_SMK_HH4017.xlsx";
    dataSheetName = "AM_HH_SMK_HH4017";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
     if (Datatable.GetValue("CreateInsurance").equals("Yes")) {
    	//@Step :To load sheet and add new Insurance
        Datatable.loadDataSheet(dataFileName, "CreateInsurance"); 
        //@Step :To select the required menu
        AM.Menu.TopMenu.Select(driver, "File/New/Insurance");
        Verify.ExactPageHeader(driver, "Insurance");
        AM.Insurance.AddNewInsurance.FillAddNewInsurance(driver);
        AM.Insurance.AddNewInsurance.AddInsurance(driver);
        Report.attachScreenShotToReport(driver); 
        AM.Menu.TopMenu.Select(driver, "File/New/Patient");
        Verify.ExactPageHeader(driver, "Add New Patient");
        AM.Patient.AddNewPatient.VerifyPrimaryInsurance(driver);
        Report.attachScreenShotToReport(driver); 
        
     }
     
     else{
    	    	
    	System.out.print("\n CreateInsurance Field is not set to Yes.");
    	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreateInsurance Field is not set to Yes");
    	Assert.fail("CreateInsurance Yes is not given in the data sheet");
    	    }
	}
        
        @AfterClass(alwaysRun = true)
    	public static void Teardown() {
    		components.Browser.teardownTest();
    	}
}
