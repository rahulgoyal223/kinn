package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

public class AM_BM2_ManagedCare_HH2399 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2399
	'JIRA ID						:	HH-2399
	'Description                    :   To Verify that claim is available in Pending Approval tab when 
										it is corrected from Canceled tab
									
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the Verify the data displayed for the 
										patient claim when claim is corrected in Pending Approval Tab

	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH2399();
	}
	
	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_ManagedCare_HH2399() throws Exception {

			String dataSheetName = null;
		    String PM_PatientName = null;
		 	//@Reports Configuration			
	        Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2399");
	        Report.SetTestName("AM_BM2_ManagedCare_HH2399", "AM_86037_BM2 Managed Care_Verify that claim is available in Pending Approval tab when it is "
	        					+ "corrected from Canceled tab");
	        Report.assignCategory("ManagedCare");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2399.xlsx";
	        dataSheetName = "AM_TPI86037_MC_OASIS";
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
	        	   Datatable.loadDataSheet(dataFileName, "AM_TPI86037_MC_OASIS");    
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
	               Datatable.loadDataSheet(dataFileName, "AM_TPI86037_MC_CMS485");	
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
	               }
	        
	               Datatable.loadDataSheet(dataFileName, "AM_TPI86037_MC_OASIS");  
	       		   AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			       components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			       AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
			       AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("MHMO Advantage Plan");
			       AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
			       Waits.ForGlobalAjaxLoader(driver);
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
				   	Waits.ForGlobalAjaxLoader(driver);
				   	if(!GlobalData.getPatientMRNumber().isEmpty()){
					       AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
					}else {
					       AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					}
				   	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
				   	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
				   	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
						
				    //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
				   	Thread.sleep(Waits.getSleepLevelFive());
				   	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
					Waits.ForGlobalAjaxLoader(driver);
					if(!GlobalData.getPatientMRNumber().isEmpty()){
					       AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
					}else {
					       AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					}
					AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
					AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
					AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
				    AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "MHMO Advantage Plan: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
				     	
				    Thread.sleep(Waits.getSleepLevelFour());
				    AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
				    Waits.ForGlobalAjaxLoader(driver);
					AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
					AM.Billing.Claims.ClaimsManager.btn_PP_Reject(driver).click();
					AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
				    
					Thread.sleep(Waits.getSleepLevelFour());
					AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");
					AM.Billing.Claims.ClaimsManager.lst_NR_Type(driver).selectByVisibleText("Rejected");
					Waits.ForGlobalAjaxLoader(driver);
					AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
					AM.Billing.Claims.ClaimsManager.btn_RC_ClaimsCorrect(driver).click();
					SwitchWindow.toTopWindow(driver);
					AM.Billing.Claims.ClaimsManager.btn_PP_Yes(driver).click();
					AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
				   	Thread.sleep(Waits.getSleepLevelFive());
				   	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
				   	Waits.ForGlobalAjaxLoader(driver);
				   	if(!GlobalData.getPatientMRNumber().isEmpty()){
					AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
					} 
					else{
					AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					}
				   	
		 	       	
				   	//Verify Fields should be displayed and data should match with patient information
				    AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);     
				    int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
				    int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
				    String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndex, claimnum);
				    
				    int colIndexMRN = components.ksGrid.getColumnIndex(driver, "MRN");     
				    String claimnumMRN = components.ksGrid.getCellData(driver, rowIndex, colIndexMRN);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndexMRN, claimnumMRN);    	
				        
				    int colIndexTotal = components.ksGrid.getColumnIndex(driver, "Total Charges");     
				    String claimnumTotal = components.ksGrid.getCellData(driver, rowIndex, colIndexTotal);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndexTotal, claimnumTotal);
				    
				    int colIndexStatus = components.ksGrid.getColumnIndex(driver, "Status");     
				    String claimnumStatus = components.ksGrid.getCellData(driver, rowIndex, colIndexStatus);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndexStatus, claimnumStatus);
				    
				    //As per the manual test case instruction we have checked TOB
				    int colIndexTOB = components.ksGrid.getColumnIndex(driver, "TOB");     
				    String claimnumTOB = components.ksGrid.getCellData(driver, rowIndex, colIndexTOB);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndexTOB, claimnumTOB);
				    
				    int colIndexBillingPeriod = components.ksGrid.getColumnIndex(driver, "Billing Period");     
				    String claimnumBillingPeriod = components.ksGrid.getCellData(driver, rowIndex, colIndexBillingPeriod);
				    components.ksGrid.verifyCellData(driver, rowIndex, colIndexBillingPeriod, claimnumBillingPeriod);

	}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }

}
