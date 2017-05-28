package Scripts.Claims.RAP;

import java.util.ArrayList;

import components.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;

public class AM_BM2_Medicare_RAP_HH2238 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2238
	'JIRA ID						:	HH-2238
	'Description                    : 	Verify that PDF is showing the 59 column with right date from pateint insurance
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that PDF is showing the 59 column with right date from pateint insurance
										
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	
	    	

	    	
	 public static void main(String[] args) throws Exception {
			Test();
		}
	    
	    @Test
		public static void Test() throws Exception {	
			
		String dataSheetName = null;
		String PM_PatientName = null;
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2238");
			Report.SetTestName("AM_BM2_Medicare_RAP_HH2238","Verify that claim preview is not generated for a patient without zipcode");
			Report.assignCategory("RAP");
			Report.assignCategory("Ready");
			//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2238.xlsx";			
			dataSheetName = "AM_BM2_Medicare_RAP_HH2238";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/			            
		       //@Step - Create New Patient, if Required
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
	        	//@ Load Data and Add New Patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");	            
	         }	        
	        
	        if (!GlobalData.getPatientFullName().isEmpty()){
	        	PM_PatientName = GlobalData.getPatientFullName();
	        }else {
	        	PM_PatientName = Datatable.GetValue("PM_PatientName");
	        }
	        
	      
	        //Update the Insured relation option on the Edit>Insurance
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            AM.Patient.PatientManager.SelectActivePatient(driver);    
	        AM.Menu.TopMenu.Select(driver, "Edit");
	        AM.Insurance.PatientInsurance.lnk_Insurance(driver).click();
	        AM.Insurance.PatientInsurance.SelectInsurance(driver, "Palmetto GBA").click();
	        AM.Insurance.PatientInsurance.lst_relationtoins(driver).selectByVisibleText("Spouse");
	        AM.Insurance.PatientInsurance.btn_update(driver).click();
            
	        //@Step Fill out OASIS
             Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
	        if(Datatable.GetValue("OASISCheck").equals("Yes")) {
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
	            AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	            AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	            AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
	            AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
	            AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	    	    AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	    	    AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();;
	    	    AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
	    	    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	    	    AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	    	    AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
	    	    AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
	            
	            //@ Step - Schedule Task in Episode Manager
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        

	            AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
	            

	          
	          
	            
	            //@ Step - Approve Scheduled Task
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	            components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	            AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
	        }
	        
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
	        if(Datatable.GetValue("CMS485Check").equals("Yes")) {
	        	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");    
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	            AM.Episode.EpisodeManager.ScheduleTask(driver);
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	            AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	            AM.Forms.Orders.CMS485.btn_Submit(driver).click();      
	            
	            //@Step - Approve CMS 485 form
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");       
	            AM.Patient.PatientManager.SelectActivePatient(driver);
	            components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");		            
	            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
	            AM.Forms.Orders.CMS485.chk_ReturnToClinician(driver).click();
	            AM.Forms.Orders.CMS485.txt_ElectronicSignature(driver).sendKeys(Datatable.GetValue("clinicianSignature"));
	            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
	        }
	        
	      //Go to Billing Manager and Create RAP
	       
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
		AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText("Palmetto GBA");
		AM.Billing.Claims.ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");

	   //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		String pdfUrl = "";
		String originalTab = driver.getWindowHandle();
		AM.Billing.Claims.ClaimsManager.btn_ubo4preview(driver).click();
		Thread.sleep(Waits.getSleepLevelTwo());

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for(int i = 0; i < tabs.size(); i++)
		{
		   driver.switchTo().window(tabs.get(i));
		   pdfUrl = driver.getCurrentUrl();
		   if(pdfUrl.endsWith("pdf"))
		   {
			 Report.attachScreenShotOfPDFToReport(driver);
		     Verify.PDFText(driver, pdfUrl, PM_PatientName + " 01");
		     ((JavascriptExecutor)driver).executeScript("window.close()");
		     driver.switchTo().window(originalTab);
		   }
		}


		//When we can assert this within ATF, come back and assert the value.
		//Assert.fail("This test may have passed, but there is not code to verify the PDF output. Please see the attached screenshot of UB04.  Box 59 should have a value of 01");


		//go back to Patient Insurance and update the relation

		//Update the Insured relation option on the Edit>Insurance
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		AM.Patient.PatientManager.SelectActivePatient(driver);
		AM.Menu.TopMenu.Select(driver, "Edit");
		AM.Insurance.PatientInsurance.lnk_Insurance(driver).click();
		AM.Insurance.PatientInsurance.SelectInsurance(driver, "Palmetto GBA").click();
		AM.Insurance.PatientInsurance.lst_relationtoins(driver).selectByVisibleText("Child");
		AM.Insurance.PatientInsurance.btn_update(driver).click();

		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
		AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
		Waits.ForBrowserLoad(driver);
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		AM.Billing.Claims.UB04_Worksheet.btn_Refresh(driver).click();
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.UB04_Worksheet.btn_SaveandClose(driver).click();

		AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		AM.Billing.Claims.ClaimsManager.btn_ubo4preview(driver).click();
		Waits.ForBrowserLoad(driver);
		pdfUrl = "";


		tabs = new ArrayList<String>(driver.getWindowHandles());
		for(int i = 0; i < tabs.size(); i++)
		{
			driver.switchTo().window(tabs.get(i));
			pdfUrl = driver.getCurrentUrl();
			if(pdfUrl.endsWith("pdf"))
			{
				Report.attachScreenShotOfPDFToReport(driver);
				Verify.PDFText(driver, pdfUrl, PM_PatientName + " 19");
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


