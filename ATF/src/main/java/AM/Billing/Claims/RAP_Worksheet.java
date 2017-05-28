package AM.Billing.Claims;

import components.Select2;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by austin.ledbetter on 2/7/2017.
 */
public class RAP_Worksheet {
    public static void EnterOccurrenceCode(WebDriver driver, String occurenceCodeId, String entry) {
        Select2.EnterSelect2Code(driver, occurenceCodeId, entry);
    }

    public static void EnterConditionCode(WebDriver driver, String conditionCodeId, String entry) {
        Select2.EnterSelect2Code(driver, conditionCodeId, entry);
    }

    public static void ClearConditionCode(WebDriver driver, String conditionCodeId){
        Select2.ClearSelect2Code(driver, conditionCodeId);
    }

    public static void ClearOccurrenceCode(WebDriver driver, String occurrenceCodeId){
        Select2.ClearSelect2Code(driver, occurrenceCodeId);
    }

    public static WebElement GetConditionCode(WebDriver driver, String conditionCodeId) {
        return Select2.GetSelect2DropDown(driver, conditionCodeId);
    }

    public static WebElement GetOccurrenceCode(WebDriver driver, String occurrenceCodeId) {
        return Select2.GetSelect2DropDown(driver, occurrenceCodeId);
    }

    public static WebElement OccurrenceDate1(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate1"));
        return element;
    }

    public static WebElement OccurrenceDate2(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate2"));
        return element;
    }
    public static WebElement OccurrenceDate3(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate3"));
        return element;
    }
    public static WebElement OccurrenceDate4(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate4"));
        return element;
    }
    public static WebElement OccurrenceDate5(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate5"));
        return element;
    }
    public static WebElement OccurrenceDate6(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate6"));
        return element;
    }
    public static WebElement OccurrenceDate7(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate7"));
        return element;
    }
    public static WebElement OccurrenceDate8(WebDriver driver){
        WebElement element = driver.findElement(By.id("occurenceDate8"));
        return element;
    }

    public static WebElement btn_SaveAndClose(WebDriver driver) {
        WebElement element = driver.findElement(By.id("submitBtn"));
        return element;
    }

    public static WebElement btn_RefreshData(WebDriver driver) {
        WebElement element = driver.findElement(By.id("refreshBtn"));
        return element;
    }

    public static WebElement txt_Remarks(WebDriver driver) {
        WebElement element = driver.findElement(By.id("remarks"));
        return element;
    }

    public static void ClearConditionCodes(WebDriver driver) {
        for(int i = 1; i<= 11; i++){
            ClearConditionCode(driver, "conditionCode" + i);
        }
    }

    public static void ClearOccurrenceCodes(WebDriver driver) {
        for(int i = 1; i<= 8; i++){
            ClearOccurrenceCode(driver, "occurenceCode" + i);
        }
    }
}
