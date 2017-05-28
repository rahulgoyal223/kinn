package Scripts.Claims.RAP;


import java.util.ArrayList;

import AM.Billing.Claims.ClaimsManagerShortcut;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * Created by James.Loser on 2/23/2017.
 */
public class AM_BM2_Medicare_RAP_HH35 {
    /************************************************************************************
     'Class name                     : 	AM_BM2_Medicare_RAP_HH35
     'JIRA ID						:	HH-35
     'Description                    : 	Prevent Spamming of Print View button
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                    : 	Test Data is present in the Global Sheet.
     'Use                            : 	Verify Print view button can not be spammed
     'Tags                           : 	Medicare
     ************************************************************************************/

    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test
    public static void Test() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH35");
        Report.SetTestName("AM_BM2_Medicare_RAP_HH35","Test Print View of UB04");
        Report.assignCategory("RAP");
        Report.assignCategory("Ready");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH35.xlsx";
        dataSheetName = "AM_BM2_Medicare_RAP_HH35";
        Datatable.loadDataSheet(dataFileName, dataSheetName );
        /****************************************************************/
        String Ins_Primary = null;
        int Spam_Count = 20;
        int Spam_Threshold = (int) (Spam_Count * .25);
        
        //@Step - Create New Patient, if Required
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
            //@ Load Data and Add New Patient
            Datatable.loadDataSheet(dataFileName, "CreatePatient");
            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }
        Ins_Primary = Datatable.GetValue("Ins_Primary");
        

        if (!GlobalData.getPatientFullName().isEmpty()){
            PM_PatientName = GlobalData.getPatientFullName();
        }else {
            PM_PatientName = Datatable.GetValue("PM_PatientName");
        }

        //@Step Fill out OASIS
        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
        if (Datatable.GetValue("OASISCheck").equals("Yes")) {
            AM.Forms.Nursing.OasisShortcut.FillOutOasisStartOfCare(driver);
        }
        
        //@Step - CMS 485 form
        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
        if (Datatable.GetValue("CMS485Check").equals("Yes")) {
            AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
        }

        //Go to Billing Manager and Create RAP
        ClaimsManagerShortcut.MoveRAPFromReadyToPendingApproval(driver, PM_PatientName, Ins_Primary);
        
        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        Waits.ForGlobalAjaxLoader(driver);      
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
   	   // Spam Print View icon
        for(int i=0 ; i < Spam_Count; i++)
        {
        AM.Billing.Claims.ClaimsManager.btn_RS_PrintView(driver).click();
        }
        
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
        
      //@Step - Sending the Claim and verifying the successful message------Ready To Send tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready to Send");
        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(Ins_Primary);
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
   	   // Spam Print View icon
        for(int i=0 ; i < Spam_Count; i++)
        {
        AM.Billing.Claims.ClaimsManager.btn_RS_PrintView(driver).click();
        }
        
        //Check number of PDF
   	   Waits.ForBrowserLoad(driver);
   	   String pdfUrl = "";
          String originalTab = driver.getWindowHandle();

   	   ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
   	   for(int i = 0,c = 1;  i < tabs.size(); i++)
   	   {
   		   driver.switchTo().window(tabs.get(i));		 		   
   		   pdfUrl = driver.getCurrentUrl();
   		   if(pdfUrl.endsWith("pdf"))
   		   {
                 Report.attachScreenShotOfPDFToReport(driver);
                 //Verify.PDFText(driver, pdfUrl, PM_PatientName);
                 c++;
                 System.out.println(c);
   		   }		 
   		   if(c==Spam_Threshold)
   		   {
   			  System.out.print("\n Too many PDFs created from button spam.");
   			  Report.Log(com.aventstack.extentreports.Status.FAIL, "Too many PDFs created from button spam");
   			  Assert.fail("Too many PDFs created from button spam");
   			  
   		   }
   	   }
   	  ((JavascriptExecutor)driver).executeScript("window.close()");
        driver.switchTo().window(originalTab);
        
        //Check EDI
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
        AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
        String href = AM.Billing.Claims.ClaimsManager.lnk_RS_Savefile(driver).getAttribute("href");
        //Verify.TextFileContains(href, "SBR*P*18");
       
    }

    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }
}
