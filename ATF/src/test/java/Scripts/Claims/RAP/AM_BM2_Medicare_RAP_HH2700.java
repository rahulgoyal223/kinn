package Scripts.Claims.RAP;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2700 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2700
	'JIRA ID						:	HH-2700
	'Description                    : 	To Verify user is able to create a claim
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able
										to create a claim from Ready tab in Claims Manager:
										RAP
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2700();
	}
    
    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2700() throws Exception {	
	
	 String PM_PatientName = null;
	 String PA_MrecordNumber = null;
     String dataSheetName = null;
     /******************************************************************
      * Mandate to call below lines at every test case start up
      ******************************************************************/
      Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2700");
      Report.SetTestName("AM_BM2_Medicare_RAP_HH2700", "Verify user is able to create a claim");
      Report.assignCategory("RAP");
      AM.Login.openAppAndSubmitCredentials();
      WebDriver driver = Browser.getDriver();
      String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2700.xlsx";
      dataSheetName = "AM_BM2_Medicare_RAP_HH2700";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
      /*****************************************************************/
      
        PM_PatientName = Datatable.GetValue("PM_PatientName");
	    if (PM_PatientName.isEmpty() || PM_PatientName.trim().toLowerCase().equals("dynamicvalue")){
	       PM_PatientName = GlobalData.getPatientFullName();
        }
	
	    PA_MrecordNumber = Datatable.GetValue("PA_MrecordNumber");
	    if (PA_MrecordNumber.isEmpty() || PA_MrecordNumber.trim().toLowerCase().equals("dynamicvalue")){
	       PA_MrecordNumber = GlobalData.getPatientMRNumber();
        }
			
        Waits.ForBrowserLoad(driver);
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
        AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
		Waits.ForGlobalAjaxLoader(driver);
        int colIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
		int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
		String patientname = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		System.out.println(patientname);
		
        if(!GlobalData.getPatientFullName().isEmpty()){
   	      AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
        } else{
   	      AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(patientname);
        }
        AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, patientname);
        AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	    AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	  
	    //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		Waits.ForGlobalAjaxLoader(driver);
		if(!GlobalData.getPatientFullName().isEmpty()){
	   	      AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
	        } else{
	   	      AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(patientname);
	    }
		
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, patientname);
		//@Step -Check the ClaimStatus
		 colIndex = components.ksGrid.getColumnIndex(driver, "Status");
		 rowIndex = components.ksGrid.getRowIndex(driver, patientname);
		String claimStatus = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, claimStatus);
		
		//@Step -Check the Total Charges
		int colIndex1 = components.ksGrid.getColumnIndex(driver, "Total Charges");
		String Totalcharge = components.ksGrid.getCellData(driver, rowIndex, colIndex1);
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex1, Totalcharge);

		//@Step -Check the Total TOB
		int colIndex2 = components.ksGrid.getColumnIndex(driver, "TOB");
		String TOB = components.ksGrid.getCellData(driver, rowIndex, colIndex2);
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex2, TOB);


	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
