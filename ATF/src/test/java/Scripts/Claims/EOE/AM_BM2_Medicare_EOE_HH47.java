package Scripts.Claims.EOE;

import AM.Billing.Remittance.RemittanceShortcut;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AM_BM2_Medicare_EOE_HH47 {

	    /************************************************************************************
	     'Class name                     : 	AM_BM2_Medicare_EOE_HH47
	     'JIRA ID						:	HH-47
	     'Description                    : 	Verify the Condition, Occurrence and Remark codes Prefill on the EOE worksheet
	     'Input Parameters           	:
	     'Output Parameters        		:
	     'Assumptions                    : 	Test Data is present in the Global Sheet.
	     'Use                            : 	Verify the codes on the EOE worksheet from Episode Manager>Edit>Conditions

	     'Tags                           : 	EOE OccurrenceCodes
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
	        Report.generateReportsFile("html", "AM_BM2_Medicare_EOE_HH47");
	        Report.SetTestName("AM_BM2_Medicare_EOE_HH47", "Verify the Condition and occurrence codes from episode condition make it to the EOE worksheet");
	        Report.assignCategory("EOE");
	        Report.assignCategory("Regression");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH47.xlsx";
	        dataSheetName = "CreatePatientYesNo";
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	        /****************************************************************/
	        //@Step - Create New Patient, if Required
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
	            //@ Load Data and Add New Patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");
	            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
	        }

	        if (!GlobalData.getPatientFullName().isEmpty()) {
	            PM_PatientName = GlobalData.getPatientFullName();
	        } else {
	            PM_PatientName = Datatable.GetValue("PM_PatientName");
	        }
	        String insuranceName = Datatable.GetValue("Ins_Primary");

	        //Edit Episode Condition
	        Datatable.loadDataSheet(dataFileName, "EditConditionRAP");
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        AM.Patient.PatientManager.SelectActivePatient(driver);
	        AM.Menu.TopMenu.Select(driver, "Edit/Condition");
	        AM.Episode.EditCondition.FillEditConditionForm(driver);

	        //@Step Fill out OASIS
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
	        if (Datatable.GetValue("OASISCheck").equals("Yes")) {
	            AM.Forms.Nursing.OasisShortcut.FillOutOasisStartOfCare(driver);
	        }

	        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
	        if (Datatable.GetValue("CMS485Check").equals("Yes")) {
	            AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
	        }

	        //Go to Billing Manager and Create RAP
	        AM.Billing.Claims.ClaimsManagerShortcut.MoveRAPFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

	        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
	        Waits.ForGlobalAjaxLoader(driver);
	        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
	        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);
	     	
	        //Verify Prefill codes
	     	String dataPageName = "EditConditionRAP";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);

	        //Refresh
	        AM.Billing.Claims.RAP_Worksheet.btn_RefreshData(driver).click();
	        
	     	//Verify Prefill codes again
	     	dataPageName = "EditConditionRAP";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);

	        //Save and Close
	        AM.Billing.Claims.RAP_Worksheet.btn_SaveAndClose(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);

	        //Get the worksheet
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
	        Waits.ForGlobalAjaxLoader(driver);
	        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
	        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);

	        //Verify Prefill codes after Save
	        dataPageName = "EditConditionRAP";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);
	     	
	     	//Process RAP
	     	AM.Billing.Claims.RAP_Worksheet.btn_SaveAndClose(driver);
	     	AM.Billing.Claims.ClaimsManagerShortcut.MoveRAPFromPendingApprovalToReadyToSend(driver, PM_PatientName);
	     	String claimNumber = AM.Billing.Claims.ClaimsManagerShortcut.MoveRAPFromReadyToSendToPendingPaymentBySendManually_getClaimNumber(driver, PM_PatientName);

			//@Step - Adding claims in Remittance
			RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "RAPRemittance", insuranceName, claimNumber);
	     	
	     	//Edit Episode Condition
	        Datatable.loadDataSheet(dataFileName, "EditConditionEOE");
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        AM.Patient.PatientManager.SelectActivePatient(driver);
	        AM.Menu.TopMenu.Select(driver, "Edit/Condition");
	        AM.Episode.EditCondition.FillEditConditionForm(driver);
	        
	        //Go to Billing Manager and Create EOE
	        AM.Billing.Claims.ClaimsManagerShortcut.MoveEOEFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

	        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
	        Waits.ForGlobalAjaxLoader(driver);
	        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
	        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);
	     	
	        //Verify Prefill codes
	     	dataPageName = "EditConditionEOE";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);

	        //Refresh
	        AM.Billing.Claims.EOE_Worksheet.btn_RefreshData(driver).click();
	        
	     	//Verify Prefill codes again
	        dataPageName = "EditConditionEOE";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);

	        //Save and Close
	        AM.Billing.Claims.EOE_Worksheet.btn_SaveAndClose(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);

	        //Get the worksheet
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
	        Waits.ForGlobalAjaxLoader(driver);
	        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
	        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	        Waits.ForGlobalAjaxLoader(driver);

	        //Verify Prefill codes after Save
	        dataPageName = "EditConditionEOE";
	     	AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
	     	AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);
	    }

	    @AfterTest(alwaysRun = true)
	    public static void Teardown() {
	        Browser.teardownTest();
	    }

	}

