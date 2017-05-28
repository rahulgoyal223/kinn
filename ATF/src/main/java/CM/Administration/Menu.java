package CM.Administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class Menu {

	public static WebElement lnkCorporateOASISExport(WebDriver driver) {        
        return driver.findElement(By.linkText("Corporate OASIS Export"));
    }
}
