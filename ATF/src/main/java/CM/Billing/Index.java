package CM.Billing;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by austin.ledbetter on 1/26/2017.
 */
public class Index {
    public static WebElement lnk_HardClose_Dashboard(WebDriver driver) {
        WebElement element = null;
        Select list = null;
        element = driver.findElement(By.id("hardCloseDashboard"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

    public static WebElement lnk_GL_Export(WebDriver driver) {
        WebElement element = null;
        Select list = null;
        element = driver.findElement(By.id("go-to-exportgl"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

}
