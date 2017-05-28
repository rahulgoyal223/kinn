package Scripts.BI;



	
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import components.Browser;
import components.Report;

	public class EditPatientEmrgncyName {

		/************************************************************************************
		'Class name                     : 	AM_BI.AM.EditPatient
		'JIRA ID						:	
		'Description                    : 	To edit patient Emergency Contact Name 
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	The  New Patient task has been run & patients exist.
		'Use                            : 	To update Contact Name and Phone to be captured in ETL. Phone updated with faulty data
		'Tags                           : 	BI
		 ************************************************************************************/
		
		public static void main(String[] args) throws Exception {
			 EditPatientEmrgncyName();
		}

		
		@Test
	    public static void  EditPatientEmrgncyName() throws Exception {
	        //public void test() throws Exception {

	    		
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
	        AM.Patient.EditPatient.EmergencyContact(driver, "Emer Gency");
	        AM.Patient.EditPatient.ContactPhone(driver, "T773996538");
	        AM.Patient.AddNewPatient.btn_UpdatePatient(driver).click();
	        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
	        }
		  @AfterClass(alwaysRun = true)
		  public static void Teardown() {
		  components.Browser.teardownTest();
		  }
		}

