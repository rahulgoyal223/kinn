package KHScripts.Insurance;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class KH_SMK_CreateInsurance_KH137 {

	/************************************************************************************
	'Class name                     : 	KH_SMK_CreateInsurance_KH137
	'JIRA ID						:	KH-137
	'Description                    : 	This test will create a insurance and verify it is selectable for a patient.
	'Input Parameters           	: 	Payer Type, Invoice Type
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created insurance is visible under primary insurance field in patient creation.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		KH_SMK_CreateInsurance_KH137();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_SMK_CreateInsurance_KH137() throws Exception {
		
		
    String dataSheetName = null;
    String LG_Login = null;
    Report.generateReportsFile("html","KH_SMK_CreateInsurance_KH137");
    Report.SetTestName("KH_SMK_CreateInsurance_KH137", "KH_SMK_CreateInsurance_KH137_verifies that the created insurance is visible under primary insurance field");
    Report.assignCategory("SMK");
    KH.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Insurance\\Medicare\\KH_SMK_KH137.xlsx";
    dataSheetName = "KH_SMK_KH137";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
     if (Datatable.GetValue("CreateInsurance").equals("Yes")) {
    	//@Step :To load sheet and add new Insurance
        Datatable.loadDataSheet(dataFileName, "CreateInsurance"); 
        //@Step :To select the required menu
        KH.Menu.TopMenu.Select(driver, "File/New/Insurance");
        Waits.ForBrowserLoad(driver);
        KH.Insurance.AddNewInsurance.FillAddNewInsurance(driver);
        KH.Insurance.AddNewInsurance.btn_NI_AddInsurance(driver).click();
        Waits.ForBrowserLoad(driver);
        Report.attachScreenShotToReport(driver); 
        
     }
     
     else{
    	    	
    	System.out.print("\n CreateInsurance Field is not set to Yes.");
    	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreateInsurance Field is not set to Yes");
    	Assert.fail("CreateInsurance Yes is not given in the data sheet");
    	    }
     
     if (!GlobalData.getLoginCredentials().isEmpty()){
     	
     	LG_Login = GlobalData.getLoginCredentials();
     	
     }
     
     else {
     	Datatable.GetValue("CreatePatient");
     }  
     
     Datatable.loadDataSheet(dataFileName, dataSheetName);
     
     //@Step :To verify and fill the Login page
     if(Datatable.GetValue("CreatePatient").equals("Yes")){
    	Datatable.loadDataSheet(dataFileName, "CreatePatient");
    	KH.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    	Waits.ForGlobalAjaxLoader(driver);
    	KH.Menu.TopMenu.Select(driver, "File/New/Patient");
    	Waits.ForBrowserLoad(driver);
		KH.Patient.AddNewPatient.FillAddNewPatient(driver);
		Report.attachScreenShotToReport(driver);
		KH.Patient.AddNewPatient.chk_PD_RaceAmericanIndian(driver).click();
		Report.attachScreenShotToReport(driver);
		KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();	
		Report.attachScreenShotToReport(driver);
		Waits.ForGlobalAjaxLoader(driver);
		KH.Admission.AddNewAdmission.VerifyPrimaryInsurance(driver);
        Report.attachScreenShotToReport(driver); 
     }
     
     else{
	    	
     	System.out.print("\n CreatePatient Field is not set to Yes.");
     	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
     	Assert.fail("CreatePatient Yes is not given in the data sheet");
     	    }
	}
        
        @AfterClass(alwaysRun = true)
    	public static void Teardown() {
    		components.Browser.teardownTest();
    	}
     
	}
