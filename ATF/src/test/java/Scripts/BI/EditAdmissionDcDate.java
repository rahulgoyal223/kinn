package Scripts.BI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import components.Browser;
import components.Report;
import components.Waits;


	public class EditAdmissionDcDate {

		/************************************************************************************
		'Class name                     : 	AM_BI.AM.EditAdmissionDcDate
		'JIRA ID						:	
		'Description                    : 	To edit Discharge Date and Discharge Status listed under the patient's admission
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	New Patient task has been run
		'Use                            : 	Updates admission DC Date and DC status to be picked up by ETL
		'Tags                           : 	BI
		 ************************************************************************************/
		
		public static void main(String[] args) throws Exception {
			EditAdmissionDcDate();
		}

		
		@Test
	    public static void EditAdmissionDcDate() throws Exception {
	        //public void test() throws Exception {

	    	   	
	    	String PM_PatientName = "BIAuto, Patient10";
	        /******************************************************************
	         * Mandate to call below lines at every test case start up
	         ******************************************************************/
	    	//@Reports Configuration
	        Report.generateReportsFile("html","AM_BI_Edit_EpisodeList");
	        Report.SetTestName("AM_BI__AdmissionSource", "Edit EpisodeList");
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
	        AM.Menu.TopMenu.Select(driver, "View/Episode List");
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EpisodeList.lnk_Admission(driver).click();
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EditAdmission.txt_EE_DischargeDate(driver).click();
	        AM.Patient.EditAdmission.txt_EE_DischargeDate(driver).clear();
	        AM.Patient.EditAdmission.txt_EE_DischargeDate(driver).sendKeys("02/15/2017");      
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EditAdmission.lst_DischargeStatus(driver).selectByIndex(1);
	        Thread.sleep(Waits.getSleepLevelFive());
	        AM.Patient.EditAdmission.btn_Save(driver).click();
	        Thread.sleep(Waits.getSleepLevelFive());
	        }
		  @AfterClass(alwaysRun = true)
		  public static void Teardown() {
		  components.Browser.teardownTest();
		  }
		}