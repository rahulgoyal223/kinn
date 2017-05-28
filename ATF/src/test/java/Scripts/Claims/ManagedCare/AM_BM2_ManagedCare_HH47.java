package Scripts.Claims.ManagedCare;

import AM.Billing.Claims.ClaimsManagerShortcut;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AM_BM2_ManagedCare_HH47 {


    /************************************************************************************
     'Class name                     : 	AM_BM2_ManagedCare_HH47
     'JIRA ID						:	HH-47
     'Description                    : 	To Verify condition and occurrence codes flow from edit>condition to claim worksheet
     'Input Parameters           	: 	Patient Name, MRN
     'Output Parameters        		:
     'Assumptions                    : 	Test Data is present in the Global Sheet.
     'Use                            : 	To Verify condition and occurrence codes flow from edit>condition to claim worksheet
     'Tags                           : 	Regression
     ************************************************************************************/


    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test(groups = {"AM_BM2_Claims", "AM_Regression"})
    public static void Test() throws Exception {
        String dataSheetName = null;
        String PM_PatientName = null;

        //@Reports Configuration
        Report.generateReportsFile("html", "AM_BM2_ManagedCare_HH47");
        Report.SetTestName("AM_BM2_ManagedCare_HH47", "Verify condition and occurence codes flow from edit>condition to claim worksheet");
        Report.assignCategory("ManagedCare");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH47.xlsx";
        Datatable.loadDataSheet(dataFileName, "CreatePatient");
        //**********************************************************************

        //@@Step  :To create new patient
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {

            //@Step :To load sheet
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

        //Update the Insured relation option on the Edit>Condition
        Datatable.loadDataSheet(dataFileName, "EditCondition");
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Menu.TopMenu.Select(driver, "Edit/Condition");
        AM.Episode.EditCondition.FillEditConditionForm(driver);

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

        //@Step - Move Managed Care claim from Ready to Pending Approval
        ClaimsManagerShortcut.MoveManagedCareFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
        AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Managed Care Insurance");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
        Waits.ForGlobalAjaxLoader(driver);

        //@Step - Verify Prefill codes
        String dataPageName = "EditCondition";
        AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceSpanCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);

        //@Step - Refresh
        AM.Billing.Claims.UB04_Worksheet.btn_Refresh(driver).click();
        Waits.ForGlobalAjaxLoader(driver);

        //@Step - Verify Prefill codes
        AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceSpanCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);
    }


    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }


}
