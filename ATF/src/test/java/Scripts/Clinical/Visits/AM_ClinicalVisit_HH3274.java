package Scripts.Clinical.Visits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_ClinicalVisit_HH3274 {		 

	/************************************************************************************
	'Class name                 : 	AM_ClinicalVisit_HH3274
	'JIRA ID					:	HH-3274
	'Description                : 	To Verify the Schedule, update and completion of OT Visit in AM.
	'Input Parameters           : 	Patient Name.
	'Output Parameters          : 	Task Status
	'Assumptions                : 	Test Data is present in the Global Sheet.
	'Use                        : 	The following test verifies the Schedule, update and completion of OT Visit in AM.
	'Tags                       : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {   
		AM_ClinicalVisit_HH3274();
	}
	
		@Test(groups = { "AM_Regression", "AM_Clinical" })
		public static void AM_ClinicalVisit_HH3274() throws Exception {
    	String dataSheetName = null;
	    String PM_PatientName = null;
    	//@Reports Configuration			
        Report.generateReportsFile("html","AM_ClinicalVisit_HH3274");
        Report.SetTestName("AM_ClinicalVisit_HH3274", "Verify First Billable Visit status in RAP Pending creation claim page when first billable is completed and non billable visit is incomplete which are scheduled different days but the visit date is same");
        Report.assignCategory("ClinicalVisit");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3274.xlsx";
        dataSheetName = "AM_2946_BM2_ClinicalVisit";
        Datatable.loadDataSheet(dataFileName, dataSheetName);
      //**********************************************************************
             
	    //@@Step  :To create new patient
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
   	   //@Step :To load sheet
       Datatable.loadDataSheet(dataFileName, "CreatePatient");            
       WebElement stale = Waits.StalenessPreset(driver);
       AM.Menu.TopMenu.Select(driver, "File/New/Patient");
       Waits.ForElementStaleness(driver,stale);
       AM.Patient.AddNewPatient.FillAddNewPatient(driver);
       AM.Patient.AddNewPatient.AddPatient(driver);           
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
       AM.Patient.PatientManager.SelectActivePatient(driver);
       Datatable.setCurrentRow(1);

	   //@ Step -  :To select and schedule a Task 
	   AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");
	   AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	   Waits.ForBrowserLoad(driver);
	   driver.findElement(By.id("Details1")).click();
	   
	   //@ Step -  : Click on View--> OT Visit 
	   Waits.ForBrowserLoad(driver);
	   AM.Menu.TopMenu.Select(driver, "View/OT Visit");
	        
	   //@Step - To load excel sheet
	   Datatable.loadDataSheet(dataFileName, "AM_2946_BM2_ClinicalVisits");
       AM.Forms.OT.OTVisitRecord.FillOTVisitRecord(driver);
       AM.Forms.OT.OTVisitRecord.FillElectronicSignature(driver);
       AM.Forms.OT.OTVisitRecord.SubmitOTVisit(driver);


	   if (!GlobalData.getPatientFullName().isEmpty()) {
	       	PM_PatientName = GlobalData.getPatientFullName();
       }else {
	       	PM_PatientName = Datatable.GetValue("PM_PatientName");
	       }
	  	Datatable.loadDataSheet(dataFileName, dataSheetName);
	  	//@ Step -  :To navigate to patient manager
	    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
	    AM.Patient.PatientManager.SelectActivePatient(driver);
	    AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");			 
		AM.Episode.EpisodeManager.VerifyStatus(driver, 1);
				
	}
		
 @AfterClass(alwaysRun = true)
public static void Teardown() {
	 components.Browser.teardownTest();
	 }
}
