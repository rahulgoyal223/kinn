package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

    public class AM_BM2_Medicare_EOE_HH2486 {
	/************************************************************************************
	'Class name                     :AM_BM2_Medicare_EOE_HH2486
	'JIRA ID						:HH-2486
	'Description                    :To Verify that the user is able to update status of the EOE claim to Pending Payment
	'Input Parameters           	:Patient Name, MRN
	'Output Parameters        		:Patient Name,MRN
	'Assumptions                    :Test Data is present in the Global Sheet.
	'Use                            :The following test verifies the Verify that the user is able to update status of the EOE claim to Pending Payment
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
				*****************************************************************/
				 //@Reports Configuration			
			     Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2486");
			     Report.SetTestName("AM_BM2_Medicare_EOE_HH2486", "Verify that the user is able to update status of the EOE claim to Pending Payment");
			     Report.assignCategory("EOE");
			     //@Open Application and submit credentials
			     AM.Login.openAppAndSubmitCredentials();
			     //@ Get Current WebDriver
			     WebDriver driver = Browser.getDriver();
			     //@Import Test data sheet
			     String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH2486.xlsx";
			     dataSheetName = "AM_86035_MC_OASIS";
			     Datatable.loadDataSheet(dataFileName, dataSheetName);
			     //**********************************************************************//
			     		  //@@Step  :To create new patient
			     		  if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			     		  //@Step :To load sheet
			     		  Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			     		  AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			     		  AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			     		  AM.Patient.AddNewPatient.AddPatient(driver);           
				       	  }          
			        	  if (!GlobalData.getPatientFullName().isEmpty()) {
			        	  PM_PatientName = GlobalData.getPatientFullName();
			        	  }else {
			        	  PM_PatientName = Datatable.GetValue("PM_PatientName");
			        	  }
			        	  Datatable.loadDataSheet(dataFileName, dataSheetName);
			        	  Datatable.loadDataSheet(dataFileName, "AM_86035_MC_OASIS");    
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
			       		   AM.Patient.PatientManager.SelectActivePatient(driver);        
			       		   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        

			       		   AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);

			       		      
			       		   //@ Step - Approve Scheduled Task
			       		   AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			       		   components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
			       		   AM.Patient.PatientManager.SelectActivePatient(driver);        
			       		   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        

			       		  AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);

			       		   }  
			       		 
			       		   //@ Step - Fill CMS 485 form from orders tab in episode manager
			               Datatable.loadDataSheet(dataFileName, "AM_86035_MC_CMS485");	
			               if(Datatable.GetValue("CMS485Check").equals("Yes")){
			               AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			           	   AM.Patient.PatientManager.SelectActivePatient(driver);
			           	   Datatable.setCurrentRow(1);
			           	   AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");    
			           	   AM.Episode.EpisodeManager.ScheduleTask(driver);
			           	   AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
			           	   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");            	
			           	   AM.Forms.Orders.CMS485.FillCMS485Form(driver);
			           	   AM.Forms.Orders.CMS485.SubmitCMS485(driver);
			               }
			               Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_86035_RAP");  
			       		   AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
					       components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
					       AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
					       AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
					       AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
					       if(!GlobalData.getPatientMRNumber().isEmpty()){
					       AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
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
						   	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
						   	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
								
						    //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
						   	Thread.sleep(Waits.getSleepLevelFive());
							AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
							AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
							AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
							AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
						    AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
						     	
						    Thread.sleep(Waits.getSleepLevelFive());
							AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
							AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
							Thread.sleep(Waits.getSleepLevelFive());
							
							if(!GlobalData.getPatientMRNumber().isEmpty()){
					        AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
					        } 
					        else{
							AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					        	}
							AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);					
							int col1Index = components.ksGrid.getColumnIndex(driver, "Claim#");
							int row1Index = components.ksGrid.getRowIndex(driver, PM_PatientName );
							String claimnum = components.ksGrid.getCellData(driver, row1Index, col1Index);
				              
				            //@Step - Navigating to Remittance Managers page
				            Thread.sleep(Waits.getSleepLevelFive());
				            AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");             
				            AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");       
				              
				            //@Step - Adding claims in Remittance
				            Thread.sleep(Waits.getSleepLevelFive());
				            Thread.sleep(Waits.getSleepLevelFive());
				            AM.Menu.TopMenu.Select(driver, "File/New/Remittance");
				            AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
				            AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
				            AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
				            Thread.sleep(Waits.getSleepLevelFive());
				            AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
				            
				            //@Step - Verifying the Patient in RAP paid tab
				            AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				            AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
				            AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
				            if(!GlobalData.getPatientMRNumber().isEmpty()){
				            AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
							} 
							else{
							AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
							}
				            		            
				            //@Step Creation of EOE in Billing Manager
				            AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
				            components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
						    AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
						    AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
						    AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
						    if(!GlobalData.getPatientMRNumber().isEmpty()){
						    AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
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
						   	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
						   	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
								
						    //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
						   	Thread.sleep(Waits.getSleepLevelFive());
							AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
							AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
							AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
							AM.Billing.Claims.ClaimsManager.btn_RS_markPendingPayment(driver).click();
							SwitchWindow.toTopWindow(driver);
							AM.Billing.Claims.ClaimsManager.btn_PP_Yes(driver).click();
							AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Pending Payment and can be reviewed in the Pending Payment tab.");

							Thread.sleep(Waits.getSleepLevelFive());
							AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
							AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
							Thread.sleep(Waits.getSleepLevelFive());
							if(!GlobalData.getPatientMRNumber().isEmpty()){
						    AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
						    } 
						    else{
						    AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
						    }
							AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
					     	

	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
