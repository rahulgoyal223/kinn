package Scripts.BI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import components.Browser;
import components.Report;

public class EditPatientGender {

	/************************************************************************************
	'Class name                     : 	AM_BI.AM.EditPatientGender
	'JIRA ID						:	
	'Description                    : 	To edit patient gender 
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	Test patient data already exits
	'Use                            : 	
	'Tags                           : 	BI
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		EditPatientGender();
	}

	
	@Test
    public static void EditPatientGender() throws Exception {
        //public void test() throws Exception {

    		
    	String PM_PatientName = "BIAuto, Patient4";
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BI_EditPatientGender");
        Report.SetTestName("AM_BI_EditPatient", "Edit patient gender");
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
        AM.Patient.EditPatient.EditPatientGender(driver).selectByIndex(1);
        AM.Patient.AddNewPatient.btn_UpdatePatient(driver).click();
        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }
	  @AfterClass(alwaysRun = true)
	  public static void Teardown() {
	  components.Browser.teardownTest();
	  }
	}
