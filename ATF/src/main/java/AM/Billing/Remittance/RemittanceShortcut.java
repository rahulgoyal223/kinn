package AM.Billing.Remittance;

import DataSource.Datatable;
import components.Waits;
import org.openqa.selenium.WebDriver;

/**
 * Created by austin.ledbetter on 2/15/2017.
 */
public class RemittanceShortcut {
    public static void FillOutAndCompleteRemittance(WebDriver driver, String dataFileName, String dataSheetName, String insuranceName, String claimNumber) throws Exception {
        SetupRemittanceWithClaim(driver, dataFileName, dataSheetName, insuranceName, claimNumber);
        RemittanceManager.FillClaimDetails(driver, 1);
        RemittanceManager.btn_AR_SaveandComplete(driver).click();
        Thread.sleep(Waits.getSleepLevelThree());
    }

    public static void FillOutAndCompleteTakeBackRemittance(WebDriver driver, String dataFileName, String dataSheetName, String insuranceName, String claimNumber) throws Exception{
        SetupRemittanceWithClaim(driver, dataFileName, dataSheetName, insuranceName, claimNumber);
        RemittanceManager.FillClaimDetailsForTakeBack(driver, 1);
        RemittanceManager.btn_AR_SaveandComplete(driver).click();
        Thread.sleep(Waits.getSleepLevelThree());
    }

    public static void SetupRemittanceWithClaim(WebDriver driver, String dataFileName, String dataSheetName, String insuranceName, String claimNumber) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");
        RemittanceManager.goToNewRemittance(driver);
        Thread.sleep(Waits.getSleepLevelFive());
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        RemittanceManager.AddRemittance(driver);
        RemittanceManager.lst_AR_Insurance(driver).selectByVisibleText(insuranceName);
        RemittanceManager.AddClaims(driver, claimNumber);
    }
}
