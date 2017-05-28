package Scripts.Remittance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_Remittance_HH2744 {

	/************************************************************************************
	'Class name              : 	AM_BM2_Medicare_Remittance_HH2744
	'JIRA ID				 :	HH-2744
	'Description             :  Verify the process the creating a Bad Debt/Write off adjustment in Remittance Manager for the amount of the write-off.
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies creating a Bad Debt/Write off adjustment
	'Tags                    : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_Remittance_HH2744();
	}
	 @Test//(groups = { "AM_Regression", "AM_Remittance" })
	public static void AM_BM2_Medicare_Remittance_HH2744() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
      Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH2744");
      Report.SetTestName("AM_BM2_Medicare_Remittance_HH2744", "Verify the process the creating a Bad Debt/Write off create an adjustment in Remittance Manager for the amount of the write-off");
      Report.assignCategory("Remittance");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
      //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
      //@Import Test data sheet
      String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH2744.xlsx";
      dataSheetName = "AM_BM2_Medicare_Remittance";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
      //**********************************************************************     

      //@ Step - To navigate to billing manager
	  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
	  Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
	  //@ Step - To navigate to the pending payment tab in Claims Manager-RAP
	  AM.Billing.Claims.ClaimsManager.SelectClaimFromRAPPendingPayment(driver);
	  int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
	  int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
	  int patientIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
	  PM_PatientName = components.ksGrid.getCellData(driver, rowIndex, patientIndex);
	  String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
	  System.out.println(claimnum);
		
	  if(!claimnum.isEmpty()){
		  //@Step - Navigating to Remittance Managers page
		  AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 	
		  Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		  AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 	
		  Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
		
		  //@Step - Adding Remittance				
		  AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		  AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		  AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		  AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		  AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
	  } else{
		  AM.DataCreation.DataCreation.CreateAndMoveRAPToPendingPayment(driver);
		  int altcolIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
		  int altrowIndex = components.ksGrid.getRowIndex(driver, "1.");
		  String altclaimnum = components.ksGrid.getCellData(driver, altrowIndex, altcolIndex);
		  System.out.println(altclaimnum);
			
		  Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_Remittance");
		  AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 	
		  Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		  AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 	
		  Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
			
		  //@Step - Adding Remittance				
		  AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		  AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		  AM.Billing.Remittance.RemittanceManager.AddClaims(driver, altclaimnum);
		  AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		  AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
	  }

      //@Step - Verifying the Patient in RAP paid tab---------Paid tab
      
      AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
      
      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.VoidClaim(driver);
      
      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");
      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayerAndType(driver, PM_PatientName, "Palmetto GBA", "Void");
      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
      
      //TODO handle popup modal and make it a method.
      //@step Click the comment button
      AM.Billing.Claims.ClaimsManager.btn_RD_Comment(driver).click();
      Waits.ForBrowserLoad(driver);

      //Failing here, not switching to the modal !!!! UUURGGHHHH
      SwitchWindow.toModalDialog(driver);
      WebElement modalTextBox = driver.findElement(By.id("comment-box"));
      Waits.fluentWaitIsEnabled(driver, modalTextBox, 2);
      AM.Billing.Claims.ClaimsManager.txt_AC_AddComment(driver).click();
      AM.Billing.Claims.ClaimsManager.txt_AC_AddComment(driver).sendKeys("Good");      
      AM.Billing.Claims.ClaimsManager.btn_RC_SaveComment(driver).click();      
      SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
      String comment = AM.Billing.Claims.ClaimsManager.clk_greenicon(driver).getAttribute("tooltip");
      System.out.println(comment);
      //TODO validate comment? add valid assert
	}
	
    /*@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }*/

}
