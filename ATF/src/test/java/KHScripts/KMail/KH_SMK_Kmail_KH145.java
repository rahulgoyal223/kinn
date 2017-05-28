package KHScripts.KMail;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import components.Browser;
import components.Report;
import components.TimeDate;

public class KH_SMK_Kmail_KH145 {
	
	/************************************************************************************
	'Class name                     : 	KH_SMK_Kmail_KH145
	'JIRA ID						:	KH-145
	'Description                    : 	Test script to Compose, Send and Delete a email.
	'Input Parameters           	: 	subject
	'Output Parameters        		: 	
	'Assumptions                    : 	
	'Use                            : 	The Following test composes and send email and later delete the same from inbox and validate the same.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		KH_SMK_Kmail_KH145();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_SMK_Kmail_KH145() throws Exception {
		

	    Report.generateReportsFile("html","KH_SMK_Kmail_KH145");
	    Report.SetTestName("KH_SMK_Kmail_KH145", "Test to Compose, Send, Delete and verify the Email");
	    Report.assignCategory("SMK");
	    KH.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    String subject = "AutoEmail_"+TimeDate.getUniqueInteger(); 
	   /*****************************************************************/
	    
	    //Navigating to Send Message Page
	    KH.Menu.TopMenu.Select(driver, "Go To/Inbox");
	    KH.Menu.TopMenu.Select(driver, "File/New/Message");
	    
	    //Validating  Send Message page	    
	    KH.Kmail.NewMessage.validateComposeKMailPage(driver);
	    
	    //Composing and Sending Mail
	    KH.Kmail.NewMessage.ComposeMail(driver, subject);
	    Report.attachScreenShotToReport(driver);
	    KH.Kmail.NewMessage.sendMessage(driver);
	    
	    //Selecting the sent mail and deleting same from Inbox
	    KH.Kmail.Inbox.SelectMailBySubject(driver, subject);
	    Report.attachScreenShotToReport(driver);
	    KH.Kmail.Inbox.DeleteMailBySubject(driver, subject);
	    
	    //Navigating to Deleted mails page and validating the deleted email
	    KH.Menu.TopMenu.Select(driver, "View/Deleted Messages");
	    KH.Kmail.Inbox.verifyDeletedEmail(driver, subject);
	    Report.attachScreenShotToReport(driver);
	}
	
	@AfterClass(alwaysRun = true)
  	public static void Teardown() {
  		components.Browser.teardownTest();
  	}
   

}
