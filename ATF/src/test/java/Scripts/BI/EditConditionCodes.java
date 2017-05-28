package Scripts.BI;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import components.Browser;
import components.Report;
import components.Waits;

		public class EditConditionCodes {

		/************************************************************************************
		'Class name                     : 	AM_BI.AM.EditConditionCodes
		'JIRA ID						:	
		'Description                    : 	To edit condition code
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	NewPatient test has been run
		'Use                            : 	Add condition code to be picked up by ETL
		'Tags                           : 	BI
		 ************************************************************************************/
		
		public static void main(String[] args) throws Exception {
			EditConditionCodes();
		}

		
		@Test
	    public static void EditConditionCodes() throws Exception {
	        //public void test() throws Exception {

	    	  	
	    	String PM_PatientName = "BIAuto, Patient2";
	        /******************************************************************
	         * Mandate to call below lines at every test case start up
	         ******************************************************************/
	    	//@Reports Configuration
	        Report.generateReportsFile("html","AM_BI_EditConditionCodes");
	        Report.SetTestName("AM_BI__AdmissionSource", "Edit Condition Codes");
	        Report.assignCategory("BI");
	        Report.assignCategory("Patient");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	    
	        /*****************************************************************/
	      //
	        
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.PatientManager.SelectPatient(driver,PM_PatientName); 
	        AM.Menu.TopMenu.Select(driver, "Edit/Condition");
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EditCondition.lst_ConditionCode18(driver).selectByIndex(6);
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EditCondition.btn_EE_Save(driver).click();
	        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
	        Thread.sleep(Waits.getSleepLevelFive());
	        
	        
	      
	        }
		  @AfterClass(alwaysRun = true)
		  public static void Teardown() {
		  components.Browser.teardownTest();
		  }
		}
