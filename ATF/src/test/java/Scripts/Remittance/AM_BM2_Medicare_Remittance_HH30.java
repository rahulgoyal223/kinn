package Scripts.Remittance;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AM_BM2_Medicare_Remittance_HH30 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_Remittance_HH30
	'JIRA ID						:	HH-2238
	'Description                    : 	Verify that EOE line item has same balance after the EOE has been rejected
	'Input Parameters           	:
	'Output Parameters        		:
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	Verify that EOE line item has same balance after the EOE has been rejected

	'Tags                           : 	Top5
	 ************************************************************************************/





	 public static void main(String[] args) throws Exception {
		 AM_BM2_Medicare_Remittance_HH30();
		}

	    @Test(groups = { "AM_Regression", "AM_Remittance" })
		public static void AM_BM2_Medicare_Remittance_HH30() throws Exception {

				String dataSheetName = null;
				String PM_PatientName = null;
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 ******************************************************************/
				//@Reports Configuration
				Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH30");
				Report.SetTestName("AM_BM2_Medicare_Remittance_HH30","Verify that EOE line item has same balance after the EOE has been rejected");
				Report.assignCategory("RAP");
				Report.assignCategory("Ready");
				//@Open Application and submit credentials
				AM.Login.openAppAndSubmitCredentials();
				//@ Get Current WebDriver
				WebDriver driver = Browser.getDriver();
				//@Import Test data sheet
				String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH30.xlsx";
				dataSheetName = "CreatePatientYesNo";
				Datatable.loadDataSheet(dataFileName, dataSheetName );
				/****************************************************************/
				//@@Step :To create new patient if needed
			      if (Datatable.GetValue("CreatePatient").equals("Yes")) {

			      	  //@Step :To load sheet
			          Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			          AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			          AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			          AM.Patient.AddNewPatient.AddPatient(driver);         
			      }     
			      
			      if (!GlobalData.getPatientFullName().isEmpty()){
			      	PM_PatientName = GlobalData.getPatientFullName();
			      }else {
			      	Datatable.GetValue("PM_PatientName");
			      }  
			      
			      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");    
			      //@Step :To verify and fill the OASIS
			      if(Datatable.GetValue("OASISCheck").equals("Yes")){
				    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
				    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
				    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
				    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
				    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
				    	AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
				    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
				    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
				    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
				    	AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
				    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
				    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
				    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
				    	AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
				    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
				    
				    	//@ Step - Schedule Task in Episode Manager
				    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
				    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
				    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
				    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");        
				    	AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
				    
				    
				    	//@ Step - Schedule Task in Episode Manager
				    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
				    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
				    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
				    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
				    	AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);   
				  }
			      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");       
			      if(Datatable.GetValue("CMS485Check").equals("Yes")){
				    	//@ Step - Fill CMS 485 form from orders tab in episode manager
				    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
				    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
				    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
				    	AM.Episode.EpisodeManager.ScheduleTask(driver);
				    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
				    	AM.Forms.Orders.CMS485.FillCMS485Form(driver);
				    	AM.Forms.Orders.CMS485.SubmitCMS485(driver);
					
				    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
				    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
				    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
				    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
				    	AM.Forms.Orders.CMS485.ApproveCMS485(driver);
			      }
			      
			      //@Step - : To Select the Billing Manager
			      Waits.ForBrowserLoad(driver);
			      Datatable.loadDataSheet(dataFileName, "RAPRemittance");
			      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			      AM.Billing.Claims.ClaimsManager.FilterAndSearchInRAPReady(driver, PM_PatientName, "Palmetto GBA");
			      
			      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
			      AM.Billing.Claims.ClaimsManager.CreateClaim(driver);
			      //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
			      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
			      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
			      AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);	
			      //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
			      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
			      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
			      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
			      AM.Billing.Claims.ClaimsManager.SendMedicareClaim(driver);

			  	  AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
			  	  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
			  	  AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);	
			      int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
			      int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
			      String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			    	
			      //@Step - Navigating to Remittance Managers page
			      Datatable.loadDataSheet(dataFileName, "RAPRemittance");
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
				  //@Step - EOE Creating claim
				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchInEOEReady(driver, PM_PatientName, "Palmetto GBA");
				  AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
				  AM.Billing.Claims.ClaimsManager.CreateClaim(driver);
				  //@Step - Approve claim
				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
				  AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);
			
				  //@Step - Resend the claim
				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready to Send");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
				  colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
				  rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
				  String eoeClaimNumber = components.ksGrid.getCellData(driver, rowIndex, colIndex);
				  AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
				  AM.Billing.Claims.ClaimsManager.SendMedicareClaim(driver);
				  //@Step - Adding claims in Remittance
				  AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
				  Waits.ForBrowserLoad(driver);
				  Datatable.loadDataSheet(dataFileName, "EOERemittance");
				  AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
				  AM.Billing.Remittance.RemittanceManager.AddClaims(driver, eoeClaimNumber);
				  AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
				  AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);

				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.BillingManager.SelectMenu(driver, "EOE/Paid");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
				  AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
				  AM.Billing.Claims.ClaimsManager.AdjustClaim(driver);

				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
				  AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);
			
				  //@Step - Resend the claim
				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready to Send");
				  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
				  colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
				  rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
				  eoeClaimNumber = components.ksGrid.getCellData(driver, rowIndex, colIndex);
				  AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
				  AM.Billing.Claims.ClaimsManager.SendMedicareClaim(driver);
				  //@Step - Adding claims in Remittance
				  AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
				  AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
				  AM.Billing.Remittance.RemittanceManager.AddClaims(driver, eoeClaimNumber);
				  AM.Billing.Remittance.EditRemittance.lnk_showClaim(driver, eoeClaimNumber).click();
				  String balance = AM.Billing.Remittance.EditRemittance.getLineItemBalance(driver, eoeClaimNumber);
				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  Thread.sleep(Waits.getSleepLevelFive());
				  AM.Billing.BillingManager.ClickLink(driver, "Reports/PPS Claims Aging Report");
				  Waits.ForGlobalAjaxLoader(driver);
				  AM.Billing.Reports.PPSClaimAgingReport_Filters.FilterAndSearchForPatient(driver, PM_PatientName);
				  Waits.ForGlobalAjaxLoader(driver);
				  int colNumber = ksGrid.getColumnIndex(driver, "Age 0-30");
				  String ppsClaimsAgeBalance = ksGrid.getCellData(driver, 0, colNumber);

				  AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				  Thread.sleep(Waits.getSleepLevelFive());
				  AM.Billing.BillingManager.ClickLink(driver, "Reports/Patient Balance Detail Report");
				  Waits.ForGlobalAjaxLoader(driver);
				  AM.Billing.Reports.PatientBalanceDetailReport_Filters.FilterAndSearchForPatient(driver, PM_PatientName);
				  Waits.ForGlobalAjaxLoader(driver);
				  colNumber = ksGrid.getColumnIndex(driver, "Balance");
				  int subTotalRowNumber = ksGrid.getRowIndex(driver, "Palmetto Gba SubTotals");
				  String pbdrBalance = ksGrid.getCellData(driver, subTotalRowNumber, colNumber);
		
				  DecimalFormat df = new DecimalFormat("#.##");
				  Assert.assertEquals(df.format(StringToFloatConverter.ConvertToFloat(balance)), df.format(StringToFloatConverter.ConvertToFloat(ppsClaimsAgeBalance)));
				  Assert.assertEquals(df.format(StringToFloatConverter.ConvertToFloat(balance)), df.format(StringToFloatConverter.ConvertToFloat(pbdrBalance)));
		}

		@AfterClass(alwaysRun = true)
		public static void Teardown() {
			Browser.teardownTest();
		}
		
	}


