package KHScripts.Remittance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

	public class KH_SMK_Remittance_KH153 {
		private static WebElement element;

		/************************************************************************************
		'Class name                     : 	KH_SMK_Remittance_KH153
		'JIRA ID						:	KH-153
		'Description                    : 	To create a manual remittance
		'Input Parameters             	: 	Patient Name, Claim Number
		'Output Parameters          	: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	To create a manual remittance								
		'Tags                           : 	Regression
		 ************************************************************************************/
		
		public static void main(String[] args) throws Exception {
			KH_SMK_Remittance_KH153();
		}
			
		@Test(groups = { "KH_Remittance", "KH_Regression", "SmokeTest" })
		public static void KH_SMK_Remittance_KH153() throws Exception {

			String dataSheetName = null;  
	    	String PM_PatientName = null;
	      //@Reports Configuration
	      Report.generateReportsFile("html","KH_SMK_Remittance_KH153");
	      Report.SetTestName("KH_SMK_Remittance_KH153", "To create a manual remittance");
	      Report.assignCategory("SMK");
	      //@Open Application and submit credentials
	      KH.Login.openAppAndSubmitCredentials();
	      //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	      //@Import Test data sheet
	      String dataFileName = "KHRemittance\\Remittance\\KH_SMK_Remittance_KH153.xlsx";
	      dataSheetName = "KH_BM2_TP85964";
	      Datatable.loadDataSheet(dataFileName, dataSheetName);
	      //**********************************************************************
	       
	      //@@Step :To create new patient if needed
	      if (Datatable.GetValue("CreatePatient").equals("Yes")) {

	      //@Step :To load sheet
	      Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	      KH.Menu.TopMenu.Select(driver, "File/New/Patient");
	      Waits.ForBrowserLoad(driver);
	      KH.Patient.AddNewPatient.FillAddNewPatient(driver);
	      KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
	      Report.attachScreenShotToReport(driver);
	      KH.Patient.AddNewPatient.clk_btnYes(driver, "Yes"); 
	      Waits.ForBrowserLoad(driver);
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
	      
	      Datatable.loadDataSheet(dataFileName, "Admission");  
	      
	      //@Step :To fill the Admission
	      if(Datatable.GetValue("Admission").equals("Yes")){
	      KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
	      KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();
	      Report.attachScreenShotToReport(driver);
	      Waits.ForBrowserLoad(driver);
	      }
	      
	      else{
		     	
		      	System.out.print("\n Admission Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "Admission Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		     }
	      
	      Datatable.loadDataSheet(dataFileName, "KH_BM2_RNInitialAssessment");  
	      
	      //@Step :To verify and fill the RNInitialAssessment
	      if(Datatable.GetValue("RNInitialAssessment").equals("Yes")){
	      //@ Step - Fill patient Task Details form and Save
	      KH.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      Waits.ForBrowserLoad(driver);
	      KH.Patient.PatientManager.SelectActivePatient(driver);        
	      KH.BenefitPeriod.BenefitPeriodManager.SelectTaskTab(driver, "Nursing");
	      KH.BenefitPeriod.BenefitPeriodManager.ScheduleTask(driver);
	      Report.attachScreenShotToReport(driver);
	      KH.BenefitPeriod.BenefitPeriodManager.datatable_Task_Details(driver).click();
	      KH.BenefitPeriod.TaskDetails.FillTaskDetails(driver);
	      Report.attachScreenShotToReport(driver);
	      KH.BenefitPeriod.TaskDetails.btn_UpdateTask(driver).click();
	      }
	      
	      else{
		     	
		      	System.out.print("\n RNInitialAssessment Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "RNInitialAssessment Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		     }
	      
	      Datatable.loadDataSheet(dataFileName, "KH_BM2_NursingVisit");
	      
	      if (Datatable.GetValue("Nursing").equals("Yes")) {
	      //@ Step - Fill Skilled Nursing Visit
	    	  
		  KH.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		  KH.Patient.PatientManager.SelectActivePatient(driver);        
		  KH.BenefitPeriod.BenefitPeriodManager.SelectTaskTab(driver, "Nursing");
		  KH.BenefitPeriod.BenefitPeriodManager.ScheduleTask(driver);
		  Report.attachScreenShotToReport(driver);
		  KH.BenefitPeriod.BenefitPeriodManager.datatable_Task_Details2(driver).click();
		  KH.BenefitPeriod.TaskDetails.FillTaskDetails(driver);
	      Report.attachScreenShotToReport(driver);
	      KH.BenefitPeriod.TaskDetails.btn_UpdateTask(driver).click();
	      }
		      
	      else{
		     	
		      	System.out.print("\n Nursing Field is not set to Yes.");
		 		Report.Log(com.aventstack.extentreports.Status.FAIL, "Nursing Field is not set to Yes");
		 		Assert.fail("Yes is not given in the data sheet");
		  }
	      
	      //@Step - : To Select the Billing Manager
	      Waits.ForBrowserLoad(driver);
	      Datatable.loadDataSheet(dataFileName, "KH_BM2_TP85964");
	      KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	      KH.Billing.BillingManager.SelectMenu(driver, "Medicare/Ready");
			
	      //@ Step - To select the required option from drop down
	      KH.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	      Waits.ForGlobalAjaxLoader(driver);
			
	      //@ Step - To get the required value from sheet
	      if(!PM_PatientName.isEmpty()){
		     	 KH.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(PM_PatientName);;
		  } 
		  else{
		     	 KH.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		  }
	     		
	      //@Step - Create Claim for the Patient
	      KH.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	      KH.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	      Waits.ForBrowserLoad(driver);
	      Report.attachScreenShotToReport(driver);
	      
	      //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	      KH.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	      Waits.ForGlobalAjaxLoader(driver);
	      if(!PM_PatientName.isEmpty()){
		     	 KH.Billing.Claims.ClaimsManager.txt_TD_Searchbox(driver).sendKeys(PM_PatientName);;
		  } 
		  else{
		     	 KH.Billing.Claims.ClaimsManager.txt_TD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		  }
	      KH.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	      KH.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
	      KH.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
	      Report.attachScreenShotToReport(driver);
	      
	      //@Step - generate Claim by clicking on Mark Pending Payment button and verifying the successful message----Ready to Send tab
	      KH.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
	      Waits.ForGlobalAjaxLoader(driver);
	      if(!PM_PatientName.isEmpty()){
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(PM_PatientName);;
		  } 
		  else{
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		  }
	      KH.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	      KH.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
	      Report.attachScreenShotToReport(driver);
			
		  //@Step - Verify the claim in Pending Payment tab
	      Waits.ForBrowserLoad(driver);
	      KH.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
	      Waits.ForGlobalAjaxLoader(driver);
	      if(!PM_PatientName.isEmpty()){
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(PM_PatientName);;
		  } 
		  else{
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		  }
	      KH.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName); 
	      String claimnum = KH.Billing.Claims.ClaimsManager.getClaimNumber(driver, GlobalData.getPatientFullName(), 5);
	      Datatable.loadDataSheet(dataFileName, "Remittance");  
			
		  if (Datatable.GetValue("Remittance").equals("Yes")) {
		  //@Step - Navigating to Remittance Managers page
		  KH.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		  Waits.ForBrowserLoad(driver);
		  //@Step - Adding Remittance				
		  KH.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		  KH.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		  KH.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
		  Report.attachScreenShotToReport(driver);		  
		  KH.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
		  }
				
		  else{
			     	
			      	System.out.print("\n Remittance Field is not set to Yes.");
			 		Report.Log(com.aventstack.extentreports.Status.FAIL, "Remittance Field is not set to Yes");
			 		Assert.fail("Yes is not given in the data sheet");
			}
		  //@Step - Verify the successful message
		  KH.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
		  KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		  KH.Billing.BillingManager.SelectMenu(driver, "Medicare/Paid");
		  Waits.ForGlobalAjaxLoader(driver);
		  if(!PM_PatientName.isEmpty()){
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(PM_PatientName);
		  } 
		  else{
		     	 KH.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		  }
		  KH.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
		  Report.attachScreenShotToReport(driver);
		}
		

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	}

