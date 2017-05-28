package Scripts.Claims.ManagedCare;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_ManagedCare_HH2397 {

		
		/************************************************************************************
		'Class name                     : 	AM_BM2_ManagedCare_HH2397
		'JIRA ID						:	HH-2397
		'Description                    : 	To Verify data displayed in Claim Worksheet after 
											modifying Episode data
		'Input Parameters           	: 	Patient Name, MRN
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies that the Verify data displayed in Claim Worksheet after modifying Episode data
		'Tags                           : 	Regression
		 ************************************************************************************/
		

		public static void main(String[] args) throws Exception {
			AM_BM2_ManagedCare_HH2397();
		}
	    
	    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_ManagedCare_HH2397() throws Exception {
	    	String dataSheetName = null;
		    String PM_PatientName = null;
			
	    	//@Reports Configuration			
	        Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2397");
	        Report.SetTestName("AM_BM2_ManagedCare_HH2397", "Verify data displayed in Claim Worksheet after modifying Episode data");
	        Report.assignCategory("ManagedCare");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2397.xlsx";
	        dataSheetName = "AM_84332_MC_OASIS";
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	      //**********************************************************************
	             
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

	                //@Step :To verify and fill the OASIS
	        		Datatable.loadDataSheet(dataFileName, "AM_84332_MC_OASIS");    
	        		 if(Datatable.GetValue("OASISCheck").equals("Yes")){
	        		      //@ Step - Fill patient OASIS demographics form and Save
	        		      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        		      Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	        		      AM.Patient.PatientManager.SelectActivePatient(driver); 
	        		      Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        		      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	        		      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
	        		      AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	        		      AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	        		      AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
	        		      AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);     
	        		      
	        		      //@ Step - Schedule Task in Episode Manager
	        		      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        		      Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	        		      AM.Patient.PatientManager.SelectActivePatient(driver);
	        		      Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        		      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	        		      AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
	        		      
	        		      //@ Step - Approve Scheduled Task
	        		      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        		      Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
	        		      AM.Patient.PatientManager.SelectActivePatient(driver);
	        		      Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        		      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	        		      AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
	        		      }  
	        		 
	        		//@ Step - Fill CMS 485 form from orders tab in episode manager
	                Datatable.loadDataSheet(dataFileName, "AM_84332_MC_CMS485");	
	                if(Datatable.GetValue("CMS485Check").equals("Yes")){
	            	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	            	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	            	AM.Patient.PatientManager.SelectActivePatient(driver);
	            	Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	            	Datatable.setCurrentRow(1);
	            	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");    
	            	AM.Episode.EpisodeManager.ScheduleTask(driver);
	            	AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
	            	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");            	
	            	AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	            	AM.Forms.Orders.CMS485.btn_Submit(driver).click();
	                }
	                
			        //@ Creation of Billing Manager
	        		Datatable.loadDataSheet(dataFileName, "AM_84332_MC_OASIS");  
			     	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			     	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
			     	Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
			     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
			     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
			     	Waits.ForGlobalAjaxLoader(driver);
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
			     	Thread.sleep(Waits.getSleepLevelFour());
			     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			     	Verify.ExactPageHeader(driver, "Claims Manager: Primary Managed Care");
			     	AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Managed Care Insurance");		      
			     	Waits.ForGlobalAjaxLoader(driver);
			     	if(!GlobalData.getPatientMRNumber().isEmpty()){
		               AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
		            } 
		            else{
		            	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		            }		     	
			     	AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
			     	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
	  		        AM.Patient.PatientManager.SelectActivePatient(driver);
			     	AM.Menu.TopMenu.Select(driver, "Edit/Episode");
			     	AM.Episode.EpisodeManager.FillReviewDiagnosisandFrequencyOrders(driver);
			     	AM.Episode.EpisodeManager.btn_UpdateDetails(driver).click();	
			     	Waits.ForBrowserLoad(driver);
			     	
			     	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			     	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
			     	Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
			     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			     	AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Managed Care Insurance");			      
			     	Waits.ForGlobalAjaxLoader(driver);
			     	if(!GlobalData.getPatientMRNumber().isEmpty()){
		               AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
		            } 
		            else{
		            	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		            }		     	
			     	AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
			     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_Refresh(driver).click();
			     	Waits.ForElementVisibility(driver, AM.Billing.Claims.ClaimsManager.Alt_PA_Successfullmsg(driver));
			    	AM.Billing.Claims.ClaimsManager.VerifyClaimsAlertmessage(driver, "Claim data has been refreshed on the page but not saved.");
			     	
			     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_SaveApprove(driver).click();
			     	Verify.ExactPageHeader(driver, "Claims Manager: Primary Managed Care");
			     	Waits.ForElementVisibility(driver, AM.Billing.Claims.ClaimsManager.Alt_PA_Successfullmsg(driver));
			     	AM.Billing.Claims.ClaimsManager.VerifyClaimsAlertmessage(driver, "Claim data has been saved.");
			     	
			     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
			     	Waits.ForBrowserLoad(driver);
			     	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			     	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
			     	Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
			     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
					AM.Billing.Claims.ClaimsManager.lst_RS_Payer(driver).selectByVisibleText("Managed Care Insurance");
					Waits.ForGlobalAjaxLoader(driver);
					if(!GlobalData.getPatientMRNumber().isEmpty()){
			               AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
			            } 
			            else{
			            	AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
			            }
					int colIndex = components.ksGrid.getColumnIndex(driver, "Ready To Send");
	 	       		int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
	 	       		String ReadyToSendStatus = components.ksGrid.getCellData(driver, rowIndex, colIndex);
	 	       		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, ReadyToSendStatus);

	}
	    
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    

}
