package Scripts.Claims.ManagedCare;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_ManagedCare_HH2393 {
	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2393
	'JIRA ID						:	HH-2393
	'Description                    : 	Verify that the user is able to add outside lab charges to a HCFA 1500  -08-05  claim in the "Pending Approval" tab.
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able to add outside lab charges to a HCFA 1500  -08-05  claim in the "Pending Approval" tab.
	'Tags                           : 	Regression
	 ************************************************************************************/
	//TODO fix Occurrence Codes selects
	/*public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH2393();
	}
		
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })*/
	public static void AM_BM2_ManagedCare_HH2393() throws Exception {

    	//@Reports Configuration
		String dataSheetName = null;
	    String PM_PatientName = null;
	     /******************************************************************
	     * Mandate to call below lines at every test case start up
	     * 
	     ******************************************************************/
    	Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2393");
		Report.SetTestName("AM_BM2_ManagedCare_HH2393","AM_85478_Claims Manager_HCFA 1500 -08-05-Verify the process of"
				+ " adding a outside lab charges to a claim in'Pending Approval' tab with payer source as Non- Medicare(HMO/Per visit)-Medicare advantage plan.");
        Report.assignCategory("ManagedCare");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet			       
		String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2393.xlsx";
	    dataSheetName = "AM_BM2_ManagedCare";
	    Datatable.loadDataSheet(dataFileName, dataSheetName);	     
	    //**********************************************************************	          
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
	            Datatable.loadDataSheet(dataFileName, "VerifyOASIS");    
	            //@Step :To verify and fill the OASIS
	            if(Datatable.GetValue("OASISCheck").equals("Yes")){
	            //@ Step - Fill patient OASIS demographics form and Save
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
	            Datatable.loadDataSheet(dataFileName, "Verify485");	
	            if (Datatable.GetValue("CMS485Check").equals("Yes")){
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
	            }

	            Datatable.loadDataSheet(dataFileName, dataSheetName);	 
		        //@ Creation of Billing Manager
		     	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
		     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
		     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
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
		     	Waits.ForBrowserLoad(driver);
		     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		     	Waits.ForGlobalAjaxLoader(driver);
		     	if(!GlobalData.getPatientMRNumber().isEmpty()){
		               AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		        }else {
		            AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		        }
		     	AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.FillOutsideLabCharge(driver);
		     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_SaveApprove(driver).click();
		     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim has been successfully approved.");
		     	
		     	//@Step - generate Claim print view and verifying the charge----Ready to Send tab
				AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
				Waits.ForGlobalAjaxLoader(driver);
		     	if(!GlobalData.getPatientMRNumber().isEmpty()){
		               AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		        }else {
		            AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		        }
				AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
				AM.Billing.Claims.ClaimsManager.btn_RS_PrintView(driver).click();
				
				Actions builder = new Actions(driver);
				builder.sendKeys(Keys.CONTROL + "2");
				
				System.out.println(driver.getPageSource());
				
				driver.findElement(By.xpath("//*[@id='icon']")).click();
				
				//TODO Add Assert on print view
				//PDDocument pdoc = PDDocument.load("C:\\Users\\108619\\Downloads\\");
						

	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
