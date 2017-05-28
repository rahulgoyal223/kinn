package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2694 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2694
	'JIRA ID						:	HH-2694
	'Description                    : 	Verify that Remarks can be added to an EOE claims in EOE claim worksheet
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	To Verify that user is able to add Remarks to the claim
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
		public static void Test() throws Exception {

				String dataSheetName = null;
			    String PM_PatientName = null;
				String RD_MrecordNumber = null;
				
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 * TODO - Reason why we have mandate statements
				*****************************************************************/
				 //@Reports Configuration			
			     Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2694");
			     Report.SetTestName("AM_BM2_Medicare_EOE_HH2694", "To verify that user is able to Add/Update Patient Status on the claim worksheet");
			     Report.assignCategory("EOE");
			     //@Open Application and submit credentials
			     AM.Login.openAppAndSubmitCredentials();
			     //@ Get Current WebDriver
			     WebDriver driver = Browser.getDriver();
			     //@Import Test data sheet
			     String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH2694.xlsx";
			     dataSheetName = "AM_85332_MC_OASIS";
			     Datatable.loadDataSheet(dataFileName, dataSheetName);
			     
			     //**********************************************************************//
			     		  //@@Step  :To create new patient
			     		  if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			     		  //@Step :To load sheet
			     		  Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			     		  AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			     		  AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			     		  AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
			     		  AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");           
				       	  }          
			        	  if (!GlobalData.getPatientFullName().isEmpty()) {
			        	  PM_PatientName = GlobalData.getPatientFullName();
			        	  }else {
			        	  PM_PatientName = Datatable.GetValue("PM_PatientName");
			        	  }
			        	  Datatable.loadDataSheet(dataFileName, dataSheetName);
			        	  Datatable.loadDataSheet(dataFileName, "AM_85332_MC_OASIS");    
			       		  if(Datatable.GetValue("OASISCheck").equals("Yes")){
			  
			       		   //@ Step - Fill patient OASIS demographics form and Save
			         	   AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			           	   AM.Patient.PatientManager.SelectActivePatient(driver);
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
			       		 
			       		   //@ Step - Fill CMS 485 form from orders tab in episode manager
			               Datatable.loadDataSheet(dataFileName, "AM_85332_MC_CMS485");	
			               if(Datatable.GetValue("CMS485Check").equals("Yes")){
			               AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			           	   AM.Patient.PatientManager.SelectActivePatient(driver);
			           	   Datatable.setCurrentRow(1);
			           	   AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");    
			           	   AM.Episode.EpisodeManager.ScheduleTask(driver);
			           	   AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
			           	   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");            	
			           	   AM.Forms.Orders.CMS485.FillCMS485Form(driver);
			           	   AM.Forms.Orders.CMS485.btn_Submit(driver).click();
			           	   
			           	Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_85332_RAP");  
			       		   AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
					       components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
					       AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
					       AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
					       AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
					       if(!GlobalData.getPatientMRNumber().isEmpty()){
					       AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
					       } 
					       else{
					       AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					       }
						     
						   //@Step - Create Claim for the Patient and verifying the successful message
						   AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
						   AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
						   AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
						 	
						   //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
						   	Thread.sleep(Waits.getSleepLevelFive());
						   	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
						   	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
							AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.txt_PD_Remarks(driver).clear();
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.txt_PD_Remarks(driver).click();
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.txt_PD_Remarks(driver).sendKeys(Datatable.GetValue("Remarks"));
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_SaveClose(driver).click();
							AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim data has been saved.");
						   	Thread.sleep(Waits.getSleepLevelFive());
						    AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
							AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
							AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_Refresh(driver).click();
							AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.verifyEOEClaimPatientStatus(driver, 2, 2, 3);	
							
			               }
	    		}
		
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    
}
