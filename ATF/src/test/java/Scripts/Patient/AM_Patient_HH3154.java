package Scripts.Patient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_Patient_HH3154 {

	/************************************************************************************
	'Class name                     : 	AM_Patient_HH3154
	'JIRA ID						:	HH-3154
	'Description                    : 	Verify the outcome of removing a patient flag, while editing a patient.
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able
										to edit and remove the patient flag
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_Patient_HH3154();
	}
		
	 @Test(groups = { "AM_Regression", "AM_Clinical" })
		public static void AM_Patient_HH3154() throws Exception {
    	//@Reports Configuration
		String dataSheetName = null;
    	Report.generateReportsFile("html","AM_Patient_HH3154");
		Report.SetTestName("AM_Patient_HH3154","To Verify User can able to edit and remove the patient flag");
        Report.assignCategory("Patient");

		//@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
			       
		 String dataFileName = "Clinical\\AM_Patient_HH3154.xlsx";
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
			   AM.Patient.AddNewPatient.chk_AF_AngryDogAtHouse(driver).click();
			   AM.Patient.AddNewPatient.AddPatient(driver);		            
			 }
	     Waits.ForBrowserLoad(driver);
	     AM.Menu.TopMenu.Select(driver, "Edit/Patient");	        	        
	     AM.Patient.AddNewPatient.chk_AF_AngryDogAtHouse(driver).click();
	     AM.Patient.AddNewPatient.UpdatePatient(driver);
	     Report.attachScreenShotToReport(driver);
	}

	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
