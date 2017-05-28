package Scripts.KMail;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.TimeDate;

public class HH_SMK_Kmail_HH4025 {
	/************************************************************************************
	'Class name                     : 	HH_SMK_Kmail_HH4025
	'JIRA ID						:	HH-4025
	'Description                    : 	Test script to Compose, Send and Delete a email.
	'Input Parameters           	: 	subject
	'Output Parameters        		: 	
	'Assumptions                    : 	
	'Use                            : 	The Following test composes and send email and later delete the same from inbox.
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		HH_SMK_Kmail_HH4025();
		
	}
		@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
		public static void HH_SMK_Kmail_HH4025() throws Exception {
	
			//@Reports Configuration
	    	Report.generateReportsFile("html","HH_SMK_Kmail_HH4025");
			Report.SetTestName("HH_SMK_Kmail_HH4025","To Compose, Send and Delete KMail");
	        Report.assignCategory("KMail");
	
			//@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			
				       
			String subject = "AutoEmail_"+TimeDate.getUniqueInteger();
		     //**********************************************************************	
			
			//Navigate to Send Message Page
			AM.Menu.TopMenu.Select(driver, "File/New/Message");
			
			//Validating  Send Message page
			AM.KMail.ComposeKMail.validateComposeKMailPage(driver);
			
			//Composing and Sending Mail
			AM.KMail.ComposeKMail.ComposeMail(driver, subject);
			Report.attachScreenShotToReport(driver);
			AM.KMail.ComposeKMail.sendMessage(driver);
			
			// Selecting and Deleting the sent mail
			AM.KMail.KMailManager.SelectMailBySubject(driver, subject);
			Report.attachScreenShotToReport(driver);
			AM.KMail.KMailManager.DeleteMailBySubject(driver, subject);
		
			//Navigate to deleted email's page and validating the deleted email
			AM.Menu.TopMenu.Select(driver, "View/Deleted Messages");
			AM.KMail.KMailManager.verifyDeletedEmail(driver, subject);
			Report.attachScreenShotToReport(driver);
		}
		
	     @AfterClass(alwaysRun = true)
	    	public static void Teardown() {
	 	        components.Browser.teardownTest();
	      }
}
