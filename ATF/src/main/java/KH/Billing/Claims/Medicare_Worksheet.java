package KH.Billing.Claims;

import components.Select2;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by austin.ledbetter on 2/7/2017.
 */
public class Medicare_Worksheet {
    public static void EnterOccurrenceCode(WebDriver driver, String occurenceCodeId, String entry) {
        Select2.EnterSelect2Code(driver, occurenceCodeId, entry);
    }

    public static void EnterConditionCode(WebDriver driver, String conditionCodeId, String entry) {
        Select2.EnterSelect2Code(driver, conditionCodeId, entry);
    }

    public static WebElement GetConditionCode(WebDriver driver, String conditionCodeId) {
        return Select2.GetSelect2DropDown(driver, conditionCodeId);
    }

    public static WebElement GetOccurrenceCode(WebDriver driver, String occurrenceCodeId) {
        return Select2.GetSelect2DropDown(driver, occurrenceCodeId);
    }

    public static WebElement btn_SaveAndClose(WebDriver driver) {
        WebElement element = driver.findElement(By.id("submitBtn"));
        return element;
    }
}
