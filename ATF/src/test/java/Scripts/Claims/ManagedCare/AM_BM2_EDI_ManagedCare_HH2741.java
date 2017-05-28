package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
import org.testng.annotations.Test;

public class AM_BM2_EDI_ManagedCare_HH2741 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_EDI_ManagedCare_HH2741
	'JIRA ID						:	HH-2741
	'Description                    : 	To Verify the functionality of EDI file check in
										Primary Managed Care
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the functionality of
										EDI file of a patient for the primary payer under
										Managed Care in BM2
	'Tags                           : 	Smoke Test
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		
	 	   Test();
	  }
			//Note - Create a patient with Managed care insurance(Eg: AETNA)
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void Test() throws Exception {
				
			String PM_PatientName = null;
		    String dataSheetName = null;
		    
		    Report.generateReportsFile("html","AM_BM2_EDI_ManagedCare_HH2741");
		    Report.SetTestName("AM_BM2_EDI_ManagedCare_HH2741", "Verify the functionality of EDI file of a patient for the Primary payer under Managed care in BM2");
		    Report.assignCategory("ManagedCare");
		    AM.Login.openAppAndSubmitCredentials();
		    WebDriver driver = Browser.getDriver();
		    String dataFileName = "Claims\\ManagedCare\\AM_BM2_EDI_ManagedCare_HH2741.xlsx";
		    dataSheetName = "AM_BM2_EDI_MC_OASIS";
		    
		    Datatable.loadDataSheet(dataFileName, dataSheetName);
		    
		   /*****************************************************************/
	        //@@Step  :To create new patient
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) 
	        {
	          //@Step :To Load The Sheet
	          Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	          AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	          AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	          AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	          AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");			            
	        }
	        
	        if (!GlobalData.getPatientFullName().isEmpty()){
	            PM_PatientName = GlobalData.getPatientFullName();
	        }
	        else{
	        Datatable.GetValue("PM_PatientName");
	        }  		            
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_EDI_MC_OASIS");    
	        //@Step :To verify and fill the OASIS
	        if(Datatable.GetValue("OASISCheck").equals("Yes")){
	        //@ Step - Fill patient OASIS demographics form and Save
	          AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	          AM.Patient.PatientManager.SelectInActivePatient(driver);        
	          AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	          AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
	          AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	          AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	          AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
	          AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);     
	            
	        //@ Step - Schedule Task in Episode Manager
	          AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
	          AM.Patient.PatientManager.SelectInActivePatient(driver);        
	          AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        

	          AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);

	            
	        //@ Step - Approve Scheduled Task
	          AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	          components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
	          AM.Patient.PatientManager.SelectInActivePatient(driver);        
	          AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	          AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
	         }

	         //@ Step - Fill CMS 485 form from orders tab in episode manager
	         Datatable.loadDataSheet(dataFileName, "AM_BM2_EDI_MC_CMS485");	
	         if (Datatable.GetValue("CMS485Check").equals("Yes")){
	           AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	           AM.Patient.PatientManager.SelectInActivePatient(driver);        
	           AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	           AM.Episode.EpisodeManager.ScheduleTask(driver);
	           AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	           AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	           AM.Forms.Orders.CMS485.btn_Submit(driver).click();
	        
	          //@Step - Approve CMS 485 form
	           AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	           AM.Patient.PatientManager.SelectInActivePatient(driver);
	           components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	           AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	           AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	           AM.Forms.Orders.CMS485.btn_Approve(driver).click();
	          }

	        Datatable.loadDataSheet(dataFileName, "AM_BM2_EDI_ManagedCare_HH2741");
	        
	        //@Step - Search for Patient in Ready tab
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
	     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	     	if(!GlobalData.getPatientMRNumber().isEmpty())
	     	{
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        } 
	        else
	        {
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }
	     	//@Step - Create Claim for the Patient and verifying the successful message
	       	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	     	AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	     	//@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	     	Thread.sleep(Waits.getSleepLevelFive());
	     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	     	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
	     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
	     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
		    
	     	Thread.sleep(Waits.getSleepLevelFive());
	        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
			AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
		   //@Step - generate EDI file
			Thread.sleep(Waits.getSleepLevelFive());
			Thread.sleep(Waits.getSleepLevelFive());
			Thread.sleep(Waits.getSleepLevelFive());
		    AM.Billing.Claims.ClaimsManager.lnk_RS_Savefile(driver).click();
		    
		    components.Verify.PartialPageTitle(driver, "https://staging.kinnser.net/AM/temp/");
		    
			if(!GlobalData.getPatientMRNumber().isEmpty()) {
	            AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, GlobalData.getPatientMRNumber());
	        } 
	        else {
	        	AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, Datatable.GetValue("RD_MrecordNumber"));
	       }		
			
			AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, Datatable.GetValue("Address"));
			AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, Datatable.GetValue("Zipcode"));
			
			if(!GlobalData.getPatientMRNumber().isEmpty()) {
	            AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, GlobalData.getPatientFullName());
	        } 
	        else {
	        	AM.Billing.Claims.ClaimsManager.VerifyPatientDetails(driver, Datatable.GetValue("PM_PatientName"));
	       }		
			
			
   }
}
