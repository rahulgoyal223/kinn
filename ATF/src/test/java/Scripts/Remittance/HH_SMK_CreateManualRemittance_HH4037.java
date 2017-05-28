package Scripts.Remittance;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

	public class HH_SMK_CreateManualRemittance_HH4037 {

		/************************************************************************************
		'Class name                     : 	HH_SMK_CreateManualRemittance_HH4037
		'JIRA ID						:	HH-4037
		'Description                    : 	To create a manual remittance
		'Input Parameters             	: 	Patient Name, Claim Number
		'Output Parameters          	: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	To create a manual remittance								
		'Tags                           : 	Regression
		 ************************************************************************************/
		
		public static void main(String[] args) throws Exception {
			HH_SMK_CreateManualRemittance_HH4037();
		}
			
		@Test(groups = { "AM_Remittance", "SmokeTest", "AM_Regression" })
		public static void HH_SMK_CreateManualRemittance_HH4037() throws Exception {

			String dataSheetName = null;  
	    	String PM_PatientName = null;
	      //@Reports Configuration
	      Report.generateReportsFile("html","HH_SMK_CreateManualRemittance_HH4037");
	      Report.SetTestName("HH_SMK_CreateManualRemittance_HH4037", "To create a manual remittance");
	      Report.assignCategory("SMK");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	      //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	      //@Import Test data sheet
	      String dataFileName = "Claims\\Medicare\\HH_SMK_CreateManualRemittance_HH4037.xlsx";
	      dataSheetName = "AM_BM2_TP85964";
	      Datatable.loadDataSheet(dataFileName, dataSheetName);
	      //**********************************************************************
	       
	      //@@Step :To create new patient if needed
	      if (Datatable.GetValue("CreatePatient").equals("Yes")) {

	      //@Step :To load sheet
	      Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	      AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	      AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	      AM.Patient.AddNewPatient.AddPatient(driver);         
	      }  
	      
	      else{
		     	
		      	System.out.print("\n CreatePatient Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		     }
	      
	      if (!GlobalData.getPatientFullName().isEmpty()){
	      	PM_PatientName = GlobalData.getPatientFullName();
	      }else {
	      	Datatable.GetValue("PM_PatientName");
	      }  
	      
	      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");  
	      
	      //@Step :To verify and fill the OASIS
	      if(Datatable.GetValue("OASISCheck").equals("Yes")){
	      //@ Step - Fill patient OASIS demographics form and Save
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);        
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C1 Start of Care");
	      AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	      AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	      AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver); 
	      AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	      AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	      AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	      AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	      AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	      AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	      Waits.ForBrowserLoad(driver);
	      AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	      AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	      
	      //@ Step - Schedule Task in Episode Manager
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
	      AM.Patient.PatientManager.SelectActivePatient(driver);        
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C1 Start of Care");        
	      AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
	      
	      //@ Step - Approve Scheduled Task
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
	      AM.Patient.PatientManager.SelectActivePatient(driver);        
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C1 Start of Care");        
	      AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);
	      }
	      
	      else{
		     	
		      	System.out.print("\n OASISCheck Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "OASISCheck Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		     }
	      
	      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
	      
	      if (Datatable.GetValue("CMS485Check").equals("Yes")) {
	      //@ Step - Fill CMS 485 form from orders tab in episode manager
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);        
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	      AM.Episode.EpisodeManager.ScheduleTask(driver);
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	      AM.Forms.Orders.CMS485.FillCMS485Form(driver);    
	      AM.Forms.Orders.CMS485.SubmitCMS485(driver);     
	      
	      //@Step - Approve CMS 485 form
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	      AM.Forms.Orders.CMS485.ApproveCMS485(driver); 
	      }
	          
	      else{
		     	
		      	System.out.print("\n CMS485Check Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "CMS485Check Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		  }
	      
	      //@Step - : To Select the Billing Manager
	      Waits.ForBrowserLoad(driver);
	      Datatable.loadDataSheet(dataFileName, "AM_BM2_TP85964");
	      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
	      AM.Billing.Claims.ClaimsManager.FilterAndSearchInRAPReady(driver, PM_PatientName, "Palmetto GBA");
	     		
	      //@Step - Create Claim for the Patient and verifying the successful message
	      AM.Billing.Claims.ClaimsManager.SelectPatient(driver, PM_PatientName);
	      AM.Billing.Claims.ClaimsManager.CreateClaim(driver);
			
	      //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
	      AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);;
			
	      //@Step - generate Claim by clicking on Mark Pending Payment button and verifying the successful message----Ready to Send tab
	      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
	      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
	      AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
	      AM.Billing.Claims.ClaimsManager.SendMedicareClaim(driver);
			
		  //@Step - Verify the claim in RAP Pending Payment tab
	      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
	      AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
	      AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName); 
	      String claimnum = AM.Billing.Claims.ClaimsManager.getClaimNumber(driver, GlobalData.getPatientMRNumber());
	      Datatable.loadDataSheet(dataFileName, "Remittance");  
			
		  if (Datatable.GetValue("Remittance").equals("Yes")) {
		  //@Step - Navigating to Remittance Managers page
		  AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		  //@Step - Adding Remittance				
		  AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		  AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		  AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		  Report.attachScreenShotToReport(driver);
		  AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
		  }
				
		  else{
			     	
			      	System.out.print("\n Remittance Field is not set to Yes.");
			 		Report.Log(com.aventstack.extentreports.Status.FAIL, "Remittance Field is not set to Yes");
			 		Assert.fail("Yes is not given in the data sheet");
			}
		  //@Step - Verify the successful message
		  AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		  AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
		  AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
		  AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
		}
		

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	}

