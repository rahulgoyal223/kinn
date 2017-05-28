package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2467 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2467
	'JIRA ID						:	HH-2467
	'Description                    : 	To Verify that user is able to partially pay a RAP Claim
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the functionality of
										user is able to partially pay a RAP Claim
	'Tags                           : 	Smoke Test
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2467();
	}
    
    @Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2467() throws Exception {
		
	    String dataSheetName = null;
	    String AR_RemittanceNumber = null;
	    
	    Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2467");
	    Report.SetTestName("AM_BM2_Medicare_RAP_HH2467", " AM_84504_BM2_RAP_Verify that user is able to partially pay a RAP Claim");
	    Report.assignCategory("RAP");
	    AM.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2467.xlsx";
	    dataSheetName = "AM_BM2_Medicare_RAP_HH2467";
	    
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    
	   /*****************************************************************/
	    //@ Step - To navigate to billing manager
		AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
		
		//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Payment");
		AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
		Waits.ForGlobalAjaxLoader(driver);

		
		int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
		int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
		String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		System.out.println(claimnum);
		
		//@Step - Navigating to Remittance Managers page
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 				
		AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 
		
		//@Step - Adding Remittance			
		Waits.ForBrowserLoad(driver);
		AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		Waits.ForBrowserLoad(driver);
		AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
		AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
      	

      	//@Step - Navigating to Remittance Managers page
		Waits.ForBrowserLoad(driver);
      	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 		
        AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");	
        Waits.ForElementVisibility(driver, AM.Billing.Remittance.RemittanceManager.btn_RM_Filters(driver));
        AM.Billing.Remittance.RemittanceManager.btn_RM_Filters(driver).click();
        AM.Billing.Remittance.RemittanceManager.btn_RM_SelectAll(driver).click();
        AM.Billing.Remittance.RemittanceManager.btn_RM_ApplyFilters(driver).click();
        Waits.ForBrowserLoad(driver);
                    
        if(!GlobalData.getRemittanceNumber().isEmpty()){
            AR_RemittanceNumber = GlobalData.getRemittanceNumber();
 		   } else{
 		    Datatable.GetValue("AR_RemittanceNumber");
 		 }
            
                     
        if(!GlobalData.getRemittanceNumber().isEmpty()){
 		    AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getRemittanceNumber());;
 		   } else{
 			AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(AR_RemittanceNumber);
 		 }
            
        AM.Billing.Remittance.RemittanceManager.ClickTask(driver, AR_RemittanceNumber);
        AM.Billing.Remittance.RemittanceManager.btn_RM_Reopen(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Re-Opened");

            
        if(!GlobalData.getRemittanceNumber().isEmpty()){
           AR_RemittanceNumber = GlobalData.getRemittanceNumber();
		  } else{
		   Datatable.GetValue("AR_RemittanceNumber");
		}
          
                   
        if(!GlobalData.getRemittanceNumber().isEmpty()){
		   AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getRemittanceNumber());;
		  } else{
		   AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(AR_RemittanceNumber);
		}
          
        AM.Billing.Remittance.RemittanceManager.ClickTask(driver, AR_RemittanceNumber);
        AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver,"The Payment Remittance has been saved and completed.");  
        
        //@Step - Verifying the Patient in RAP paid tab---------Paid tab
        Waits.ForBrowserLoad(driver);
		AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");	
		Waits.ForBrowserLoad(driver);
		AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
		Waits.ForGlobalAjaxLoader(driver);
		if(!GlobalData.getPatientMRNumber().isEmpty()){
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getClaimNumer());;
	      } 
	      else{
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(claimnum);
	      }
		AM.Billing.Claims.ClaimsManager.VerifyClaim(driver, claimnum); 
        
	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
