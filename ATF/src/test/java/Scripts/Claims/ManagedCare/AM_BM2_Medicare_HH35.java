package Scripts.Claims.ManagedCare;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_HH35 {
	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH35
	'JIRA ID						:	HH-35
	'Description                    : 	Prevent Spamming of Print View button
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	Verify Print view button can not be spammed
	'Tags                           : 	ManagedCare
	 ************************************************************************************/
	
	/*@Test
	public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH35();
	}*/
		
	@Test
	public static void AM_BM2_ManagedCare_HH35() throws Exception {

    	//@Reports Configuration
		String dataSheetName = null;
	    String PM_PatientName = null;
	     /******************************************************************
	     * Mandate to call below lines at every test case start up
	     * 
	     ******************************************************************/
    	Report.generateReportsFile("html","AM_BM2_ManagedCare_HH35");
		Report.SetTestName("AM_BM2_ManagedCare_HH35","Test Print View of UB04");
        Report.assignCategory("ManagedCare");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet			       
		String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH35.xlsx";
	    dataSheetName = "AM_BM2_ManagedCare_HH35";
	    Datatable.loadDataSheet(dataFileName, dataSheetName);	     
	    //**********************************************************************	
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
       
      //Go to Billing Manager and Create Managed Care
       
        AM.Billing.Claims.ClaimsManagerShortcut.MoveManagedCareFromReadyToPendingApproval(driver, PM_PatientName,
				Ins_Primary);
 	 	
 	   //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Pending Approval");
 	   Waits.ForGlobalAjaxLoader(driver);
 
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
  	   // Spam Print View icon
        for(int i=0 ; i < Spam_Count; i++)
        {
        AM.Billing.Claims.ClaimsManager.btn_RS_PrintView(driver).click();
        }
        
 	   AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
 	   Waits.ForBrowserLoad(driver);
 	   // Spam UB04 Print button on Worksheet
 	   for(int i=0 ; i < Spam_Count; i++)
 	  {
 	   AM.Billing.Claims.ClaimsManager.btn_ubo4preview(driver).click();
 	  }
 	   
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
	}


    @AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
