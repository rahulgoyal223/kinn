package AM.Billing.Claims;

import components.Waits;
import components.ksGrid;

import org.openqa.selenium.WebDriver;

/**
 * Created by austin.ledbetter on 2/15/2017.
 */
public class ClaimsManagerShortcut {
    public static void MoveRAPFromReadyToPendingApproval(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(insuranceName);
        ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RD_Createclaim(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
    }

    public static void MoveRAPFromPendingApprovalToReadyToSend(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_PA_Approve(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
    }

    public static String MoveRAPFromReadyToSendToPendingPaymentBySendManually_getClaimNumber(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready to Send");
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        int colIndex = ksGrid.getColumnIndex(driver, "Claim#");
        int rowIndex = ksGrid.getRowIndex(driver, PM_PatientName);
        String claimNumber = ksGrid.getCellData(driver, rowIndex, colIndex);
        ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RS_Claimactions(driver).click();
        ClaimsManager.btn_RS_Sendmanually(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.	Save File");
        return claimNumber;
    }

    public static String MoveRAPFromReadyAllTheWayToPendingPayment_getClaimNumber(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        MoveRAPFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);
        MoveRAPFromPendingApprovalToReadyToSend(driver, PM_PatientName);
        return MoveRAPFromReadyToSendToPendingPaymentBySendManually_getClaimNumber(driver, PM_PatientName);
    }

    public static void MoveRAPFromRejectedToPendingApprovalByCorrect(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Rejected/Cancelled");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.lst_NR_Type(driver).selectByVisibleText("Rejected");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RC_Claimactions(driver).click();
        ClaimsManager.btn_RC_Correct(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
    }

    public static void MoveRAPFromPendingPaymentToRejected(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Payment");
        ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_PP_Markrejected(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
    }

    public static void MoveRAPFromReadyToSendToPendingPaymentByMarkPendingPayment(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready to Send");
        ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RS_Claimactions(driver).click();
        ClaimsManager.btn_RS_Markpendingpayment(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
    }

    public static String MoveEOEFromReadyAllTheWayToPendingPayment_getClaimNumber(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        MoveEOEFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);
        MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);
        return MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(driver, PM_PatientName);
    }

    public static void MoveEOEFromReadyToPendingApproval(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
        ClaimsManager.lst_RD_Payer(driver).selectByVisibleText(insuranceName);
        ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RD_Createclaim(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
    }
    
    
    public static String MoveEOEFromReadyToPendingApproval_getClaimNumber(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.lst_RD_Payer(driver).selectByVisibleText(insuranceName);
        ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RD_Createclaim(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
        ClaimsManager.lst_RD_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        int colIndex = ksGrid.getColumnIndex(driver, "Claim#");
        int rowIndex = ksGrid.getRowIndex(driver, PM_PatientName);
        String eoeClaimNumber = ksGrid.getCellData(driver, rowIndex, colIndex);
        return eoeClaimNumber;
    }
    

    public static void MoveEOEFromPaidToAdjustedInPendingApproval(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Paid");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.lst_RD_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_PD_Adjust(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        Thread.sleep(Waits.getSleepLevelTwo());
    }

    public static String MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready to Send");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        int colIndex = ksGrid.getColumnIndex(driver, "Claim#");
        int rowIndex = ksGrid.getRowIndex(driver, PM_PatientName);
        String eoeClaimNumber = ksGrid.getCellData(driver, rowIndex, colIndex);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RS_Claimactions(driver).click();
        ClaimsManager.btn_RS_Sendmanually(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
        return eoeClaimNumber;
    }

    public static String MoveEOEFromReadyToSendToPendingPaymentByMarkPendingPayment_getClaimNumber(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready to Send");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        int colIndex = ksGrid.getColumnIndex(driver, "Claim#");
        int rowIndex = ksGrid.getRowIndex(driver, PM_PatientName);
        String eoeClaimNumber = ksGrid.getCellData(driver, rowIndex, colIndex);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RS_Claimactions(driver).click();
        ClaimsManager.btn_RS_Markpendingpayment(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
        return eoeClaimNumber;
    }

    public static void MoveEOEFromPendingApprovalToReadyToSend(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
    }

    public static void MoveEOEFromPendingPaymentToRejected(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Payment");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_PP_Markrejected(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
    }

    public static void MoveEOEFromRejectedToCorrectedInPendingApproval(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Rejected/Cancelled");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.lst_NR_Type(driver).selectByVisibleText("Rejected");
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RC_Correct(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
    }

    public static void RefreshEOEAndSave(WebDriver driver, String PM_PatientName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.Edit_PA_icon(driver).click();
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);
        UB04_Worksheet.btn_Refresh(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
        UB04_Worksheet.btn_SaveandClose(driver).click();
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);
    }
    
	public static void MoveManagedCareFromReadyToPendingApproval(WebDriver driver, String PM_PatientName, String insuranceName)
			throws InterruptedException, Exception {
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(insuranceName);
        AM.Billing.Claims.ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
 	    AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	}

    public static void MoveManagedCareFromPendingApprovalToReadyToSend(WebDriver driver, String PM_PatientName, String insuranceName)
            throws InterruptedException, Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Pending Approval");
        AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
    }

    public static String MoveManagedCareFromReadyToSendToPendingPaymentByMarkPendingPayment_getClaimNumber(WebDriver driver, String PM_PatientName, String insuranceName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready to Send");
        AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText(insuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        int colIndex = ksGrid.getColumnIndex(driver, "Claim #");
        int rowIndex = ksGrid.getRowIndex(driver, PM_PatientName);
        String managedCareClaimNumber = ksGrid.getCellData(driver, rowIndex, colIndex);
        ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
        ClaimsManager.btn_RS_Claimactions(driver).click();
        ClaimsManager.btn_RS_Markpendingpayment(driver).click();
        ClaimsManager.btn_PP_Yes(driver).click();
        ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
        return managedCareClaimNumber;
    }

    public static void MoveManagedCareSecondaryFromReadyToPendingApproval(WebDriver driver, String PM_PatientName, String insuranceName)
            throws InterruptedException, Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Secondary Payer/Ready");
        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText(insuranceName);
        AM.Billing.Claims.ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
    }
}
