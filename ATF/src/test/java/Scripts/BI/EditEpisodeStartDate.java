package Scripts.BI;




import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import components.Browser;
import components.Report;
import components.Waits;

public class EditEpisodeStartDate {

	/************************************************************************************
	'Class name                     : 	AM_BI.AM.EditEpisode
	'JIRA ID						:	
	'Description                    : 	To edit episode start and end date
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	New Patient test has been run & patients added
	'Use                            : 	Update Start & End date to be picked up by ETL
	'Tags                           : 	BI
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		EditEpisodeStartDate();
	}

	
	@Test
    public static void EditEpisodeStartDate() throws Exception {
        //public void test() throws Exception {

    	   	
    	String PM_PatientName = "BIAuto, Patient8";
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BI_EditEpisode");
        Report.SetTestName("AM_BI_EditEpisode", "Edit Episode");
        Report.assignCategory("BI");
        Report.assignCategory("Episode");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
    
        /*****************************************************************/
      //
        
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectPatient(driver,PM_PatientName); 
        AM.Menu.TopMenu.Select(driver, "Edit/Episode");
        Thread.sleep(Waits.getSleepLevelFive());
        AM.Episode.EditEpisode.txt_EE_StartDate(driver).click();
        AM.Episode.EditEpisode.txt_EE_StartDate(driver).clear();
        AM.Episode.EditEpisode.txt_EE_StartDate(driver).sendKeys("02/15/2017");        
        AM.Episode.EditEpisode.txt_EE_EndDate(driver).click();
        AM.Episode.EditEpisode.txt_EE_EndDate(driver).clear();
        AM.Episode.EditEpisode.txt_EE_EndDate(driver).sendKeys("04/15/2017");
        AM.Episode.EditEpisode.btn_EE_Update(driver).click();
        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        Thread.sleep(Waits.getSleepLevelFive());
        
        }
	  @AfterClass(alwaysRun = true)
	  public static void Teardown() {
	  components.Browser.teardownTest();

}}