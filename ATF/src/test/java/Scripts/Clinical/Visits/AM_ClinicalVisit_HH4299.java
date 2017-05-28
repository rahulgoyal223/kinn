package Scripts.Clinical.Visits;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_ClinicalVisit_HH4299 {

	/************************************************************************************
	'Class name              : 	AM_ClinicalVisit_HH4299
	'JIRA ID				 :	HH-4299
	'Description             :  To verify that Additional diagnosis ICD-10 code selected are displayed correctly
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies if the  Additional diagnosis 
								selected in Billing Oasis are displayed correctly
	'Tags                    : 	Regression, Clinical
	 ************************************************************************************/

   	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4299();
	 }
	
   	 @Test(groups = { "AM_Regression", "AM_Clinical" })
	 public static void AM_ClinicalVisit_HH4299() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;  
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
       Report.generateReportsFile("html","AM_ClinicalVisit_HH4299");
       Report.SetTestName("AM_ClinicalVisit_HH4299", "To verify that ICD-10 code selected is displayed correctly");       
       Report.assignCategory("ClinicalVisit");
       
      //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
      
       //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
       
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH4299.xlsx";
       dataSheetName = "CreatePatient";
       Datatable.loadDataSheet(dataFileName, dataSheetName);
       //********************************************************************** 
             
      //@@Step :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		} else {
				//@ Step -  :To navigate to patient manager
				AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
				AM.Patient.PatientManager.SelectActivePatient(driver);
			}		
	   System.out.println("Patient Name is : "+ PM_PatientName);
		
		//@ Step -  :To navigate to patient manager
 	   Datatable.loadDataSheet(dataFileName, "AM_CV_BillingOasis_HH4299");
       Datatable.setCurrentRow(1);
             
		//@ Step -  :To select and schedule a Task 
	   AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	   AM.Episode.EpisodeManager.AddScheduleTasks(driver);
       AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Billing OASIS");       	
       
       //Fill the task
       Waits.ForBrowserLoad(driver);
       AM.Forms.Nursing.BillingOasis.FillDemographicSection(driver);
       AM.Forms.Nursing.BillingOasis.FillPrimaryDiagnosis(driver);
       AM.Forms.Nursing.BillingOasis.FillAdditionalDiagnosis(driver);
       
       //@Step : To Save the form
       AM.Forms.Nursing.BillingOasis.Save(driver).click();
       
     //@Step to To verify that the correct values in Primary and Additional diagnosis are displayed
       String[] fields = new String[] {    	   
   	       "I110",
   	       "A0102",
   	       "A0223",
   	       "G4761",
   	       "H52511",
   	       "N501",
   	       "S12120A",
   	       "L512",
   	       "T83728A",
   	       "Y712"
       };
       
       for (int i = 0; i < fields.length; i++) {
    	   AM.Forms.Nursing.BillingOasis.CheckValue(driver, fields[i], i);
       }
	} 
   	 
   	 //@Teardown
   	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		components.Browser.teardownTest();
 	}
}
