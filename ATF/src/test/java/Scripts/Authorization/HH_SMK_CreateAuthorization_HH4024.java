package Scripts.Authorization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;

public class HH_SMK_CreateAuthorization_HH4024 {

	/************************************************************************************
	  'Class name 		 : HH_SMK_CreateAuthorization_HH4024 
	  'JIRA ID 			 : HH-4024
	  'Description 	 	 : Creating Authorization, Reconcile Authorization, Update Authorization and Delete Authorization 
	  'Input Parameters  : Patient Name, Authorization Number
	  'Output Parameters : Patient Name 
	  'Assumptions  	 : Test Data is present in the Global Sheet. 
	  'Use 				 : The following script creates a patient, adds a task, creates an authorization, reconcile the Authorization, update the authorization and deletes the authorization. 
	  'Tags 			 : Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		HH_SMK_CreateAuthorization_HH4024();
	}

	@Test(groups= {"AM_Regression", "AM_Clinical" })
	public static void HH_SMK_CreateAuthorization_HH4024() throws Exception {
    	
		String dataSheetName = null;  
    	String PM_PatientName = null;
    	WebElement element =null;
    	String strInputValue=null;		
	  /******************************************************************
	  * Mandate to call below lines at every test case start up
	  * Initialize browser, login, setup for extent reports
	  * and load any necessary datasheets for test
	  ******************************************************************/
       //@Reports Configuration
      Report.generateReportsFile("html","HH_SMK_CreateAuthorization_HH4024");
      Report.SetTestName("HH_SMK_CreateAuthorization_HH4024", "Creating Authorization, Reconcile Authorization, Update Authorization and Delete Authorization");
      Report.assignCategory("Authorization");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
     //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
     //@Import Test data sheet
      String dataFileName = "Authorizations\\HH_SMK_CreateAuthorization_HH4024.xlsx";
      dataSheetName = "HH_AuthorizationManager_HH4024";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
     //**********************************************************************
     
      //@@Step  :To create new patient
      if (Datatable.GetValue("CreatePatient").equals("Yes")) 
      {
      	  Datatable.loadDataSheet(dataFileName, "CreatePatient");            
          AM.Menu.TopMenu.Select(driver, "File/New/Patient");
          AM.Patient.AddNewPatient.FillAddNewPatient(driver);
          Report.attachScreenShotToReport(driver);
          AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
          AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
      }	      
      if (!GlobalData.getPatientFullName().isEmpty()) {
          PM_PatientName = GlobalData.getPatientFullName();
      }
      else {
      Datatable.GetValue("PM_PatientName");
      }   
      
      Datatable.loadDataSheet(dataFileName, dataSheetName);
    
      //@Step - Navigate to patient manager and select active patient
      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
      AM.Patient.PatientManager.SelectActivePatient(driver);
      
      //@Step - Navigate to Add Authorization and Add one
      Datatable.loadDataSheet(dataFileName, "HH_AuthorizationManager_HH4024"); 
      AM.Menu.TopMenu.Select(driver, "File/New/Authorization");
      AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
      element = AM.Authorization.AddNewAuthorization.txt_D_AuthorizationNumber(driver);
      strInputValue = element.getAttribute("value");
      Report.attachScreenShotToReport(driver);
      AM.Authorization.AddNewAuthorization.btn_A_InsertUpdateAuth(driver).click();
      
      //@Step - To navigate reconcile authorization and update
      AM.Menu.TopMenu.Select(driver, "View/Reconcile Authorization");
      AM.Authorization.ReconcileAuthorization.lst_OT_Action(driver).selectByVisibleText("Move to "+strInputValue+"");
      AM.Authorization.ReconcileAuthorization.btn_UpdateVisit(driver).click();
      AM.Authorization.ReconcileAuthorization.VerifyReconciledMVTask(driver, "0");
      
      //@Step - Navigate to Add Authorization and Add one
      Datatable.loadDataSheet(dataFileName, "HH_AuthorizationManager_HH4024");
      AM.Menu.TopMenu.Select(driver, "File/New/Authorization");
      AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
      element = AM.Authorization.AddNewAuthorization.txt_D_AuthorizationNumber(driver);
      strInputValue = element.getAttribute("value");
      Report.attachScreenShotToReport(driver);
      AM.Authorization.AddNewAuthorization.btn_A_InsertUpdateAuth(driver).click();
      
      //@Step - Open newly created authorization in edit mode
      AM.Authorization.AuthorizationList.editAuthorization(driver, strInputValue);
     
      //@step - Update the authorization
      Datatable.loadDataSheet(dataFileName, "HH_AuthorizationManager_HH4024");
      AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
      element = AM.Authorization.AddNewAuthorization.txt_D_AuthorizationNumber(driver);
      strInputValue = element.getAttribute("value");
    
      //@step - Add attachment to the authorization
      driver.findElement(By.id("attachment")).sendKeys(Config.getSourcePath()+"test\\resources\\attachments\\TestAttachment.txt");
      driver.findElement(By.id("uploadAttachmentattachment")).click();
   	  Assert.assertTrue(driver.findElement(By.linkText("Delete")).isDisplayed(), "Unable to upload attachment");
      Report.attachScreenShotToReport(driver);
      AM.Authorization.AddNewAuthorization.btn_A_InsertUpdateAuth(driver).click();
      
      //@step - Delete the authorization
      String status;
      AM.Authorization.AuthorizationList.deleteAuthorization(driver, strInputValue);
      status = AM.Authorization.AuthorizationList.getAuthorizationStatus(driver, strInputValue);
      Report.attachScreenShotToReport(driver);
      Assert.assertTrue(status.equalsIgnoreCase("Deleted"), "The Desired authorization is not Deleted");
    
      //@step - Activate the deleted authorization
      AM.Authorization.AuthorizationList.editAuthorization(driver, strInputValue);;
      Select statusDropDown = AM.Authorization.AddNewAuthorization.lst_D_Status(driver);
      statusDropDown.selectByVisibleText("Active");
      AM.Authorization.AddNewAuthorization.btn_A_InsertUpdateAuth(driver).click();
      status = AM.Authorization.AuthorizationList.getAuthorizationStatus(driver, strInputValue);
      Report.attachScreenShotToReport(driver);
      Assert.assertTrue(status.equalsIgnoreCase("Active"), "The Desired authorization status is not Active");
	}
	
	@AfterClass(alwaysRun=true)
	public void Teardown() {
		components.Browser.teardownTest();
	}
      
}
