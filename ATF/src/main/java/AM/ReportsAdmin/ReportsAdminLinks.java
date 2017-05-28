package AM.ReportsAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;


public class ReportsAdminLinks {
    static String strInputValue;
    private static WebElement element = null;
    private static Select list = null;

		public static  WebElement lnk_insurance(WebDriver driver) {
		    element = driver.findElement(By.linkText("Insurance"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}


}