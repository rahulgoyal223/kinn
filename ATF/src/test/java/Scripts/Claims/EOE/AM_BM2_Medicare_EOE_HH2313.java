package Scripts.Claims.EOE;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import AM.Billing.Claims.ClaimsManager;
import AM.Billing.Claims.ClaimsManagerShortcut;
import AM.Billing.Remittance.RemittanceShortcut;
import AM.Billing.Reports.BillingReportShortcuts;
import AM.Episode.EditEpisode;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2313 {
	

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2313
	'JIRA ID						:	HH-2313
	'Description                    : 	To verify that user is able to add/update PCR number on edit episode and EOE claim worsheet has that number once claim is created
	'Input Parameters           	: 
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able
										to Add/Update PCR number and verify claim is carrying that number.
										
	'Tags                           : 	Regression
	/************************************************************************************/

	 public static void main(String[] args) throws Exception {
		 AM_BM2_Medicare_EOE_HH2313();
		     }
		 
	 @Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
	     public static void AM_BM2_Medicare_EOE_HH2313() throws Exception {
	String dataSheetName = null;
	String PM_PatientName = null;
	String RD_MrecordNumber = null;
	/******************************************************************
	* Mandate to call below lines at every test case start up
	******************************************************************/
	//@Reports Configuration			
	Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2313");
	Report.SetTestName("AM_BM2_Medicare_EOE_HH2313", "To verify that user is able to Add/Update PCR and check EOE claims");
	Report.assignCategory("EOE");
	//@Open Application and submit credentials
	//@Open Application and submit credentials
	String driverpath = Config.getDriversPath();
	String Btype = Config.getBrowserType();
	String Url = Config.getAppUrl();
	WebDriver driver = Browser.getDriver(Btype, Url, driverpath);
	//This is the login for 2 sisters agency which has clinicbranch state set to IL for testing 
	AM.Login.SubmitCredentials(driver, "randres.2shhc", Config.getAppPassword());

	driver.get(Config.getAppUrl() + "am/hotbox.cfm");
	//@Import Test data sheet
	String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH2313.xlsx";
	dataSheetName = "CreatePatientYesNo";
	Datatable.loadDataSheet(dataFileName, dataSheetName);
	//**********************************************************************//
	 //@Step - Create New Patient, if Required
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
	             //@ Load Data and Add New Patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");
	            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
	         }
	         String patientFirstName = GlobalData.getPatientFirstName();
	         if (patientFirstName.isEmpty()) {
	             patientFirstName = Datatable.GetValue("PD_FisrtName");
	         }
	         if (!GlobalData.getPatientFullName().isEmpty()) {
	             PM_PatientName = GlobalData.getPatientFullName();
	         } else {
	             PM_PatientName = Datatable.GetValue("PM_PatientName");
	         }
	         String insuranceName = Datatable.GetValue("Ins_Primary");
	         String cliniciansig = Datatable.GetValue("OA_clinicianSignature");
	         
	  //@Step Fill out OASIS
	          Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
	              if (Datatable.GetValue("OASISCheck").equals("Yes")) {
	            AM.Forms.Nursing.OasisShortcut.FillOutOasisStartOfCare(driver);
	           }
	                 
	     //@Step - CMS 485 form
	            Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
	             if (Datatable.GetValue("CMS485Check").equals("Yes")) {
	              AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
	                 }
	 
	      //Go to Edit>Episode and fill the PCR number
	 		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	 		AM.Patient.PatientManager.SelectActivePatient(driver);
	 		AM.Menu.TopMenu.Select(driver, "Edit/Episode");
	 		AM.Episode.EditEpisode.txt_PCR_TrackingNumber(driver).sendKeys("23232323232323");
	 		AM.Episode.EditEpisode.txt_PCR_Comment(driver).sendKeys("this is a test comment");
	 		AM.Episode.EditEpisode.btn_EE_Update(driver).click();
	 		AM.Episode.EditEpisode.clk_btnYes(driver, "Yes");
	        
	 		//Go to Billing Manager and Create RAP
	         String rapClaimNumber = ClaimsManagerShortcut.MoveRAPFromReadyAllTheWayToPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);
	 
	         //@Step - Adding claims in Remittance
	         RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);
	 
	         //@Step - EOE Creating claim and checking for PCR number on EOE worksheet
	          ClaimsManagerShortcut.MoveEOEFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);
	         AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	         Waits.ForGlobalAjaxLoader(driver);
	         AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
	         Waits.ForGlobalAjaxLoader(driver);
	         ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
	         ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
	         AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	         Waits.ForGlobalAjaxLoader(driver);
	         
	         String PageSource = driver.getPageSource();
	         if(PageSource.contains("PCR Tracking #"))
	        	    System.out.println("PCR option is present on the page");
	        	else
	        	    System.err.println("PCR option is not present on the page");
	         
	         if(PageSource.contains("23232323232323"))
	        	    System.out.println("PCR number is present on the page");
	        	else
	        	    System.err.println("PCR number is not present on the page");
	         
	         Assert.assertTrue((PageSource.contains("PCR Tracking #")), "PCR number option is present on the EOE worsksheet");
	         Assert.assertTrue((PageSource.contains("23232323232323")), "PCR number is present on the EOE worsksheet");
	     
	         //Go to Edit>Episode again and update the PCR number
		 		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		 		AM.Patient.PatientManager.SelectActivePatient(driver);
		 		AM.Menu.TopMenu.Select(driver, "Edit/Episode");
		 		Waits.ForGlobalAjaxLoader(driver);
		 		AM.Episode.EditEpisode.txt_PCR_TrackingNumber(driver).clear();
		 		AM.Episode.EditEpisode.txt_PCR_TrackingNumber(driver).sendKeys("78787878787878");
		 		AM.Episode.EditEpisode.txt_PCR_Comment(driver).clear();
		 		AM.Episode.EditEpisode.txt_PCR_Comment(driver).sendKeys("this is updated comment");
		 		AM.Episode.EditEpisode.btn_EE_Update(driver).click();
		 		Waits.ForGlobalAjaxLoader(driver);
		 		AM.Episode.EditEpisode.clk_btnYes(driver, "Yes");
		 		Waits.ForGlobalAjaxLoader(driver);
		 		
		 //Go to Billing Manager and Refresh the claim and check the PCR number
		 		  AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		 	       AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
		 	       Waits.ForGlobalAjaxLoader(driver);
		 	        ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
		 	        ClaimsManager.PA_SelectPatient(driver, PM_PatientName);	
		 	       AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		 	       AM.Billing.Claims.UB04_Worksheet.btn_Refresh(driver).click();
		 	      Waits.ForGlobalAjaxLoader(driver);
		 	       AM.Billing.Claims.UB04_Worksheet.btn_SaveandClose(driver).click();
		 	      Waits.ForGlobalAjaxLoader(driver);
		 	       AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		 	        Waits.ForGlobalAjaxLoader(driver);
		 	        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
		 	       Waits.ForGlobalAjaxLoader(driver);
		 	        ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
		 	        ClaimsManager.PA_SelectPatient(driver, PM_PatientName);	
		 	       AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
			         Waits.ForGlobalAjaxLoader(driver);
			         
			         String PageSource1 = driver.getPageSource();
			         if(PageSource1.contains("PCR Tracking #"))
			        	    System.out.println("PCR option is present on the page");
			        	else
			        	    System.err.println("PCR option is not present on the page");
			         
			         if(PageSource1.contains("78787878787878"))
			        	    System.out.println("PCR number is present on the page");
			        	else
			        	    System.err.println("PCR number is not present on the page");
			         
			         Assert.assertTrue((PageSource1.contains("PCR Tracking #")), "PCR number option is present on the EOE worsksheet");
			         Assert.assertTrue((PageSource1.contains("78787878787878")), "PCR number is present on the EOE worsksheet");
			      
			 	
	       //Check the EDI for PCR number on the 837 file
			 		  ClaimsManagerShortcut.MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);
			 		  ClaimsManagerShortcut.MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(driver, PM_PatientName);
                 String href = AM.Billing.Claims.ClaimsManager.lnk_RS_Savefile(driver).getAttribute("href");
                   Verify.TextFileContains(href, "78787878787878");
                   
           //Check PDF on Pending Payment:   
                   AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
                   AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Payment");
		 	       Waits.ForGlobalAjaxLoader(driver);
		 	        ClaimsManager.txt_PP_Searchbox(driver).sendKeys(PM_PatientName);
		 	        ClaimsManager.PP_SelectPatient(driver, PM_PatientName);	
			 		Waits.ForBrowserLoad(driver);
			 		 ClaimsManager.btn_RS_PrintView(driver).click();	
			 		Waits.ForBrowserLoad(driver);
			 		Waits.ForBrowserLoad(driver);
			 		String pdfUrl = "";
			 		String originalTab = driver.getWindowHandle();

			 		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			 		for(int i = 0; i < tabs.size(); i++)
			 		{
			 			driver.switchTo().window(tabs.get(i));
			 			pdfUrl = driver.getCurrentUrl();
			 			if(pdfUrl.endsWith("pdf"))
			 			{
			 				Report.attachScreenShotOfPDFToReport(driver);
			 				Verify.PDFText(driver, pdfUrl, "78787878787878");
			 				((JavascriptExecutor)driver).executeScript("window.close()");
			 				driver.switchTo().window(originalTab);
			 			}
			 		}
 }
	 
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
