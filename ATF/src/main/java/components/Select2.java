package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by austin.ledbetter on 2/8/2017.
 */
public class Select2 {
    public static void EnterSelect2Code(WebDriver driver, String codeId, String entry) {
        String filterId = "s2id_" + codeId;
        WebElement dropdown = cbo_Select2DropDown(driver, filterId);
        dropdown.click();
        WebElement input = driver.findElement(By.cssSelector("input[class='select2-input select2-focused']"));

        input.sendKeys(entry);
        driver.findElement(By.className("select2-result")).click();
    }

    public static void ClearSelect2Code(WebDriver driver, String codeId){
        String filterId = "s2id_" + codeId;
        WebElement dropdown = cbo_Select2DropDown(driver, filterId);

        WebElement clearIcon = dropdown.findElement(By.cssSelector("abbr[class='select2-search-choice-close']"));
        clearIcon.click();
    }

    private static WebElement cbo_Select2DropDown(WebDriver driver, String filterId) {
        WebElement select2Div = driver.findElement(By.id(filterId));
        Waits.ForElementVisibility(driver, select2Div);
        WebElement dropdown = select2Div.findElement(By.className("select2-choice"));
        Waits.ForElementVisibility(driver, dropdown);
        return dropdown;
    }

    public static WebElement GetSelect2DropDown(WebDriver driver, String codeId) {
        String filterId = "s2id_" + codeId;
        return cbo_Select2DropDown(driver, filterId);
    }
}
