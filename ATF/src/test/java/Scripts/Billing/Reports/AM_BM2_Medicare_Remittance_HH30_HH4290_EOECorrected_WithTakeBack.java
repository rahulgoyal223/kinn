package Scripts.Billing.Reports;

import AM.Billing.Claims.ClaimsManagerShortcut;
import AM.Billing.Remittance.RemittanceShortcut;
import AM.Billing.Reports.BillingReportShortcuts;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.StringToFloatConverter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class AM_BM2_Medicare_Remittance_HH30_HH4290_EOECorrected_WithTakeBack {

    /************************************************************************************
     'Class name                    : 	AM_BM2_Medicare_Remittance_HH30_HH4290_EOECorrected_WithTakeBack
     'JIRA ID						:	HH-30
     'Description                   : 	Verify PPSClaimAgingBalance report for Craig
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                   : 	Test Data is present in the Global Sheet.
     'Use                           : 	Verify that PBDR and PPS Claim Aging return the same balance after paying, adjusting, rejecting, and correcting an EOE

     'Tags                          : 	Top5
     ************************************************************************************/


    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test//(groups = {"AM_Remittance"})
    public static void Test() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html", "AM_BM2_Medicare_Remittance_HH30_HH4290_EOECorrected_WithTakeBack");
        Report.SetTestName("AM_BM2_Medicare_Remittance_HH30_HH4290_EOECorrected_WithTakeBack", "Verify PPSClaimAgingBalance report after paying, adjusting, rejecting, and correcting and refreshing an EOE");
        Report.assignCategory("RAP");
        Report.assignCategory("Ready");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Billing.Reports\\AM_BM2_Medicare_PPSClaimsAgingReport_HH30_HH4290.xlsx";
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
        String patientFirstName = GlobalData.getPatientFirstName();
        if (patientFirstName.isEmpty()) {
            patientFirstName = Datatable.GetValue("PD_FisrtName");
        }
        if (!GlobalData.getPatientFullName().isEmpty()) {
            PM_PatientName = GlobalData.getPatientFullName();
        } else {
            PM_PatientName = Datatable.GetValue("PM_PatientName");
        }
        String insuranceName = Datatable.GetValue("Ins_Primary");

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
        String rapClaimNumber = ClaimsManagerShortcut.MoveRAPFromReadyAllTheWayToPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);

        //@Step - Adding claims in Remittance
        RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);

        //@Step - EOE Creating claim
        ClaimsManagerShortcut.MoveEOEFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

        ClaimsManagerShortcut.RefreshEOEAndSave(driver, PM_PatientName);

        ClaimsManagerShortcut.MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);

        String eoeClaimNumber =  ClaimsManagerShortcut.MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(driver, PM_PatientName);

        //@Step - Adding claims in Remittance
        RemittanceShortcut.FillOutAndCompleteTakeBackRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);
        RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "EOERemittance", insuranceName, eoeClaimNumber);

        //@Step - Moving EOE from Paid to Adjusted
        ClaimsManagerShortcut.MoveEOEFromPaidToAdjustedInPendingApproval(driver, PM_PatientName, insuranceName);

        //@Step - Approve claim
        ClaimsManagerShortcut.MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);

        //@Step - Resend the claim
        eoeClaimNumber = ClaimsManagerShortcut.MoveEOEFromReadyToSendToPendingPaymentByMarkPendingPayment_getClaimNumber(driver, PM_PatientName);

        //@Step - Reject the claim
        ClaimsManagerShortcut.MoveEOEFromPendingPaymentToRejected(driver, PM_PatientName);

        //@Step - Correct the claim
        ClaimsManagerShortcut.MoveEOEFromRejectedToCorrectedInPendingApproval(driver, PM_PatientName);

        //@Step - Refresh the EOE
        ClaimsManagerShortcut.RefreshEOEAndSave(driver, PM_PatientName);

        //@Step - Approve claim
        ClaimsManagerShortcut.MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);

        //@Step - Resend the claim
        eoeClaimNumber = ClaimsManagerShortcut.MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(driver, PM_PatientName);

        //@Step - Adding claims in Remittance
        //RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "EOECorrectedRemittance1", insuranceName, eoeClaimNumber);

        //@Step - Adding claims in Remittance
        //RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "EOECorrectedRemittance2", insuranceName, eoeClaimNumber);

        String ppsClaimsAgeBalance = BillingReportShortcuts.GetBalanceForPatientFromPPSClaimsAgingReport(driver, patientFirstName);

        String pbdrBalance = BillingReportShortcuts.GetBalanceForPatientFromPatientBalanceDetailReport(driver, patientFirstName);

        DecimalFormat df = new DecimalFormat("#.##");
        Assert.assertEquals(df.format(StringToFloatConverter.ConvertToFloat(pbdrBalance)), df.format(StringToFloatConverter.ConvertToFloat(ppsClaimsAgeBalance)));
    }


    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }

}


