package AM.Billing.Reports;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by austin.ledbetter on 2/6/2017.
 */
public class PatientBalanceDetailReport_Filters {
    public static WebElement btn_branch_selectall(WebDriver driver) {
        return Common_ReportsFilters.btn_branch_selectall(driver);
    }

    public static  WebElement btn_payertype_selectall(WebDriver driver) {
        return Common_ReportsFilters.btn_payertype_selectall(driver);
    }

    public static  WebElement btn_insurance_selectall(WebDriver driver) {
        return Common_ReportsFilters.btn_insurance_selectall(driver);
    }

    public static  WebElement btn_ApplyFilters(WebDriver driver) {
        return Common_ReportsFilters.btn_ApplyFilters(driver);
    }

    public static void FilterAndSearchForPatient(WebDriver driver, String patientName) throws Exception {
    	Waits.fluentWaitIsEnabled(driver, btn_insurance_selectall(driver), 3);
    	btn_insurance_selectall(driver).click();
		btn_payertype_selectall(driver).click();
		btn_branch_selectall(driver).click();
		EnterPatientSearchTerm(driver, patientName);
		chk_Unbilled(driver).click();
		btn_ApplyFilters(driver).click();
    }
    public static WebElement cbo_PatientSearch(WebDriver driver){
        WebElement select2Div = driver.findElement(By.id("s2id_select2Filter"));
        Waits.ForElementVisibility(driver, select2Div);
        WebElement dropdown = select2Div.findElement(By.className("select2-choice"));
        Waits.ForElementVisibility(driver, dropdown);
        return dropdown;
    }

    public static void EnterPatientSearchTerm(WebDriver driver, String patientSearchString){
        WebElement specificPatient = driver.findElement(By.id("specificPatient"));
        specificPatient.click();
        WebElement dropdown = cbo_PatientSearch(driver);
        dropdown.click();
        WebElement input = driver.findElement(By.className("select2-input"));
        Waits.ForElementVisibility(driver, input);
        input.sendKeys(patientSearchString);
        WebElement firstItem = driver.findElements(By.className("select2-result-selectable")).get(0);
        firstItem.click();
    }

    public static WebElement chk_Unbilled(WebDriver driver) {
        WebElement unbilled = driver.findElement(By.id("checkbox1"));
        Waits.ForElementToBeClickable(driver, unbilled);
        return unbilled;
    }
}
