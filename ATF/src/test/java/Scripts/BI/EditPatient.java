package Scripts.BI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import components.Browser;
import components.Report;

public class EditPatient {

	/************************************************************************************
	'Class name                     : 	AM_BI.AM.EditPatient
	'JIRA ID						:	
	'Description                    : 	To edit patient address
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	MRN
	'Assumptions                    : 	The Add New Patient test has been run.
	'Use                            : 	
	'Tags                           : 	BI
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		Test();
	}

	
	@Test
    public static void Test() throws Exception {
        //public void test() throws Exception {

    	String dataSheetName = null;    	
    	String PM_PatientName = "BIIAuto, Patient1";
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BI_EditPatient");
        Report.SetTestName("AM_BI_EditPatient", "Edit patient");
        Report.assignCategory("BI");
        Report.assignCategory("Patient");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
    
        /*****************************************************************/
      //
        
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectPatient(driver,PM_PatientName); 
        AM.Menu.TopMenu.Select(driver, "Edit/Patient");
        AM.Patient.EditPatient.EditPatientAddress(driver, "Via Fortuna ; Break");
        AM.Patient.AddNewPatient.btn_UpdatePatient(driver).click();
        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }
	  @AfterTest(alwaysRun = true)
	  public static void Teardown() {
	  components.Browser.teardownTest();
	  }
	}

