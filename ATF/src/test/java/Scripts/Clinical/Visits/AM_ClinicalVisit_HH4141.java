package Scripts.Clinical.Visits;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Verify;

public class AM_ClinicalVisit_HH4141 {

	/************************************************************************************
	'Class name              : 	AM_ClinicalVisit_HH4141
	'JIRA ID				 :	HH-4141
	'Description             :  To verify that M1028 displays the following options: 0  No ,1 Yes, - Not assessed(No Information) in AM
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies if the patient created
								and Verify update to M1028 component
	'Tags                    : 	Regression, Clinical
	 ************************************************************************************/

   	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3943();
	 }
	
   	 @Test(groups = { "AM_Regression", "AM_Clinical" })
	 public static void AM_ClinicalVisit_HH3943() throws Exception {

		 /******************************************************************
		 * Skip test if executed before feature is released (03-01-2017)
		 * 
		 * 
		 ******************************************************************/
		if( ! isTestEnabled() ){
			System.out.println("SKIPPED Test "+ Thread.currentThread().getStackTrace()[1].getClassName() + ". It's going to start running on 03-01-2017");
			return;
		}
		
    	String dataSheetName    = null;  
    	String PM_PatientName   = null;  
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
       Report.generateReportsFile("html","AM_ClinicalVisit_HH4141");
       Report.SetTestName("AM_ClinicalVisit_HH4141", "To verify  M1028 component was updated");       
       Report.assignCategory("ClinicalVisit");
       
      //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
      
       //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
       
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH4141.xlsx";
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
 	   Datatable.loadDataSheet(dataFileName, "AM_CV_OASISC2StartOfCare_HH4141");
       Datatable.setCurrentRow(1);
             
		//@ Step -  :To select and schedule a Task
	   AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");	   
	   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
             
        //@Step Opens for OASIS-C2 Start of Care Patient History and Diagnoses
       AM.Menu.TopMenu.Select(driver, "Edit/Patient History and Diagnoses");   
       AM.Forms.Nursing.OasisSOC.VerifyM1028Options(driver);  
       
     //@Step to check default value in "Peripheral Vascular Disease (PVD)"
       if(driver.findElement(By.cssSelector("input[id^='M1028_ACTV_DIAG_PVD_PAD_C2_0']")).isSelected()==true)
       {
    	   Report.Log(
					Status.PASS, 
					" In M1028 section, for PVD; 0  is selected by default as expected"
					);
       }else {
    	   Report.Log(
    			   Status.FAIL,
    			   " In M1028 section, for PVD; 0  is not selected by default"
					); 
       }
     //@Step to check default value in Diabetes Mellitus (DM)
       if(driver.findElement(By.cssSelector("input[id^='M1028_ACTV_DIAG_DM_C2_0']")).isSelected()==true)
       {
    	   Report.Log(
					Status.PASS, 
					" In M1028 section, for DM; 0  is selected by default as expected"
					);
       }else {
    	   Report.Log(
    			   Status.FAIL,
    			   " In M1028 section, for DM; 0  is not selected by default"
					); 
       }
	}
   	 //@Teardown
  	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
  		try {
			if( isTestEnabled() )
				components.Browser.teardownTest();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
  	
  	private static boolean isTestEnabled() throws ParseException{
  		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date effectiveDate = dateFormat.parse("2017-03-01");
		return (new Date()).after(effectiveDate);
  	}
}
