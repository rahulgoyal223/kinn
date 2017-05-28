
package Scripts.Patient;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;
public class HH_SMK_CreatePatient_HH4009 {

	/************************************************************************************
	'Class name                     : 	HH_SMK_CreatePatient_HH4009
	'JIRA ID						:	HH-4009
	'Description                    : 	To create a new Patient and verify that the created patient is in active patients list.
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that a new created patient is visible as active.
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		HH_SMK_CreatePatient_HH4009();
		
	}
		@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
		public static void HH_SMK_CreatePatient_HH4009() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration
    	Report.generateReportsFile("html","AM_CreatePatient_HH4009");
		Report.SetTestName("AM_CreatePatient_HH4009","To create a new Patient");
        Report.assignCategory("Create Patient");

		//@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
			       
		String dataFileName = "Clinical\\AM_CreatePatient_HH4009.xlsx";
	    dataSheetName = "CreatePatient";
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	     //**********************************************************************	          
		
	     //@@Step  :To create new patient
	     if (Datatable.GetValue("CreatePatient").equals("Yes")) 
	     	{
			   //@Step :To Load The Sheet
			   Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			   AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			   AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			   Report.attachScreenShotToReport(driver);
			   AM.Patient.AddNewPatient.chk_AF_AngryDogAtHouse(driver).click();
			   Report.attachScreenShotToReport(driver);
			   AM.Patient.AddNewPatient.AddPatient(driver);
			   Report.attachScreenShotToReport(driver);
			 }
	     
	     else{
	     	
	      	System.out.print("\n CreatePatient Field is not set to Yes.");
	 		Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
	 		Assert.fail("Yes is not given in the data sheet");
	     }
	     if (!GlobalData.getPatientFullName().isEmpty()) {
	        	PM_PatientName = GlobalData.getPatientFullName();
	     }else {
	        	PM_PatientName = Datatable.GetValue("PM_PatientName");
	     }
	     Datatable.loadDataSheet(dataFileName, dataSheetName);
	     System.out.println("Patient Name is : "+ PM_PatientName);
	     	
	     //@ Step -  :To navigate to patient manager
	     AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
	     Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	     AM.Patient.PatientManager.SelectActivePatient(driver);
	     Waits.ForBrowserLoad(driver);
	     AM.Episode.EpisodeManager.VerifyPatientName(driver, PM_PatientName);
		}
	     
	     @AfterClass(alwaysRun = true)
	    	public static void Teardown() {
	 	        components.Browser.teardownTest();
	      }
	}
	