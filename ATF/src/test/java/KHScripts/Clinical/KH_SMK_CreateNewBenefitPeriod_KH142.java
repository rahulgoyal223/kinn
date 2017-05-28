package KHScripts.Clinical;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class KH_SMK_CreateNewBenefitPeriod_KH142 {


	/************************************************************************************
	'Class name                     : 	KH_SMK_CreateNewBenefitPeriod_KH142
	'JIRA ID						:	KH-142
	'Description                    : 	To create a new benefit period for a patient 
	'Input Parameters             	: 	Patient Name, Benefit Period Beginning Date & Benefit Period Ending Date.
	'Output Parameters          	: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	To create a new benefit period for a patient								
	'Tags                           :   Smoke Test
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		KH_SMK_CreateNewBenefitPeriod_KH142();
	}
		
	@Test(groups = { "SmokeTest", "KH_Clinical", "KH_Regression" })
	public static void KH_SMK_CreateNewBenefitPeriod_KH142() throws Exception {

		String dataSheetName = null;  
    	String PM_PatientName = null;
      //@Reports Configuration
      Report.generateReportsFile("html","KH_SMK_CreateNewBenefitPeriod_KH142");
      Report.SetTestName("KH_SMK_CreateNewBenefitPeriod_KH142", "To create a new benefit period for a patient");
      Report.assignCategory("SMK");
      //@Open Application and submit credentials
      KH.Login.openAppAndSubmitCredentials();
      //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
      //@Import Test data sheet
      String dataFileName = "KHClinical\\KH_SMK_CreateNewBenefitPeriod_KH142.xlsx";
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
      
      if (!GlobalData.getPatientFullName().isEmpty()){
      	PM_PatientName = GlobalData.getPatientFullName();
      }else {
      	Datatable.GetValue("PM_PatientName");
      } 
      
      Datatable.loadDataSheet(dataFileName, "BenefitPeriod");  
      
      //@Step :To fill the Admission
      if(Datatable.GetValue("BenefitPeriod").equals("Yes")){
      KH.Menu.TopMenu.Select(driver, "File/New/Benefit Period");
      Waits.ForBrowserLoad(driver);
      KH.BenefitPeriod.AddNewBenefitPeriod.FillAddNewBenefitPeriod(driver);
      Report.attachScreenShotToReport(driver);
      KH.BenefitPeriod.AddNewBenefitPeriod.btn_BP_SaveAndSubmit(driver).click();
      Waits.ForBrowserLoad(driver);
      KH.BenefitPeriod.AddNewBenefitPeriod.VerifyAlertmessage(driver, "A new Benefit Period has been created for this patient.");
      KH.Menu.TopMenu.Select(driver, "View/Benefit Period List");
      KH.BenefitPeriod.AddNewBenefitPeriod.VerifyBenefit(driver, GlobalData.getDate());
      }
      
      else{
	     	
          System.out.print("\n BenefitPeriod Field is not set to Yes.");
          Report.Log(com.aventstack.extentreports.Status.FAIL, "BenefitPeriod Field is not set to Yes");
          Assert.fail("Yes is not given in the data sheet");
          }
      
	}
	

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}

