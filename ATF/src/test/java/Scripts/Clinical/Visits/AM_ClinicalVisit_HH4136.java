package Scripts.Clinical.Visits;

import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;

public class AM_ClinicalVisit_HH4136 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH4136
		'JIRA ID						:	HH-4136
		'Description                    :   To verify the box 26 of 485 print view displays correctly for 485 v3 	
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the box 26 of 485 print view displays correctly for 485 v3
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4136();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH4136() throws Exception {

		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH4136");
		Report.SetTestName("AM_ClinicalVisit_HH4136", "To verify the box 26 of 485 print view displays correctly for 485 v3");
		Report.assignCategory("ClinicalVisit");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		
		//@Step : Open Existing episode
		driver.get((new URI(driver.getCurrentUrl()).resolve("/am/Patient/editEpisode.cfm?EpisodeKey=7811367").toString()));

		//@Step : Open Orders Tab
		AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders"); 

		//@ Step - Open CMS 485 Print Preview
		AM.Episode.EpisodeManager.PrintViewScheduleTasks(driver, "CMS 485");
		AM.Episode.EpisodeManager.OpenPrintViewTab(driver);
        
        // Step - Verify the text displayed in box 26 is the expected
        
        String expectedBox26v3Verbiage = "26. I Certify/ Recertify that this patient is confined to his/her home "
        		+ "and needs intermittent skilled nursing care, physical therapy and/or speech therapy or continue "
        		+ "to need occupational therapy. The patient is under my care, and I have authorized the services "
        		+ "on this plan of care and will periodically review the plan.";

        try {
        	Assert.assertEquals(
            		AM.Forms.Orders.CMS485.printBoxNumber(driver, 26).getText().trim(),
            		expectedBox26v3Verbiage
            		);
        	Report.Log(Status.PASS, "Box 26. verbiage is correct");
         
        } catch(AssertionError ae){
        	Report.Log(Status.FAIL, "Box 26. verbiage is wrong. Error: " + ae.getMessage());
        	throw ae;
        }
        
	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}

}


