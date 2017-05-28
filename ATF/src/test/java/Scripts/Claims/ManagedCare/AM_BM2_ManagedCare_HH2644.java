package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_ManagedCare_HH2644 {

		/************************************************************************************
		'Class name                     : 	AM_BM2_ManagedCare_HH2644
		'JIRA ID						:	HH-2644
		'Description                    :   To verify that user is able to create a Secondary Managed care 
											claim and to verify the data displayed in Pending Approval tab
										
		'Input Parameters           	: 	Patient Name, MRN
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies that the data displayed in Pending Approval tab
		'Tags                           : 	Regression
		 ************************************************************************************/

		public static void main(String[] args) throws Exception {
			Test();
		}
	    
		@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void Test() throws Exception {
	    	String dataSheetName = null;
		    String PM_PatientName = null;
			//**********************************************************************//
		 	//@Reports Configuration			
	        Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2644");
	        Report.SetTestName("AM_BM2_ManagedCare_HH2644", "To verfiy that user is able to create a Secondary Mangedcare claim");
	        Report.assignCategory("ManagedCare");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2644.xlsx";
	        dataSheetName = "AM_HH2644_MC_OASIS";
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
	        	   Datatable.loadDataSheet(dataFileName, "AM_HH2644_MC_OASIS");    
	       		   if(Datatable.GetValue("OASISCheck").equals("Yes")){
	       		   
	       		   //@ Step - Fill patient OASIS demographics form and Save
	         	   AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	           	   AM.Patient.PatientManager.SelectActivePatient(driver);
	      		   AM.Menu.TopMenu.Select(driver, "Edit/Episode");
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
	               Datatable.loadDataSheet(dataFileName, "AM_HH2644_MC_CMS485");	
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
	               
			        //@ Creation of Billing Manager For Primary Managed Care
	       			Datatable.loadDataSheet(dataFileName, "AM_HH2644_MC_OASIS");  
	       			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
			     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("AETNA");
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
			     	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
			     	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
			     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
					
			     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
			     	Thread.sleep(Waits.getSleepLevelFive());
					AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
					Waits.ForGlobalAjaxLoader(driver);
					AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
					AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
					AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
			     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "AETNA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
			     	
			     	Thread.sleep(Waits.getSleepLevelFive());
			     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
			     	Waits.ForGlobalAjaxLoader(driver);
			     	if(!GlobalData.getPatientMRNumber().isEmpty()){
			        AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
			        } 
			        else{
					AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
			        }
			     	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
			     	int col1Index = components.ksGrid.getColumnIndex(driver, "Claim #");
			        int row1Index = components.ksGrid.getRowIndex(driver, PM_PatientName );
			     	String claimnum = components.ksGrid.getCellData(driver, row1Index, col1Index);				
			     	
			     	//@Step - Navigating to Remittance Managers page
		            Thread.sleep(Waits.getSleepLevelFive());
		            AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");             
		            AM.Billing.BillingManager.ClickLink(driver, "Managed Care/Remittance Manager");
		            Waits.ForBrowserLoad(driver);

		            AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		            AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		            AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		            AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		            AM.Billing.Remittance.RemittanceManager.chk_PP_SecondarypayerEnable(driver).click();
		            AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
		            
		            //@Step - Verifying the Patient in RAP paid tab---------Paid tab
		            AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
		            AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Paid");
				    AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("AETNA");
				    Waits.ForGlobalAjaxLoader(driver);
				    if(!GlobalData.getPatientMRNumber().isEmpty()){
				    AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
				    } 
				    else{
					AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
				    }
					
			        //@Step- Creation of Billing Manager For Secondary Managed Care
				    AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			     	AM.Billing.BillingManager.SelectMenu(driver, "Secondary Payer/Ready");
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
				   	Thread.sleep(Waits.getSleepLevelFive());
				   	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
				   	AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
				   	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
				 	//@Step - Approving the Claim and verifying the successful message------Pending Approval tab
				   	Thread.sleep(Waits.getSleepLevelFive());
				    AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("MHMO Advantage Plan");
			     	Waits.ForGlobalAjaxLoader(driver);
			     	if(!GlobalData.getPatientMRNumber().isEmpty()){
					    AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
					    } 
					    else{
						AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
					    }
				    int colIndex = components.ksGrid.getColumnIndex(driver, "Pending Payment");
		 	       	int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
		 	       	String PendingApprovalStatus = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		 	       	components.ksGrid.verifyCellData(driver, rowIndex, colIndex,PendingApprovalStatus);
		 	       	

	}

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
}
