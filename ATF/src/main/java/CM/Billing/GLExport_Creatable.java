package CM.Billing;

import com.aventstack.extentreports.Status;
import components.Report;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by austin.ledbetter on 1/26/2017.
 */
public class GLExport_Creatable {
    public static Select lst_month(WebDriver driver) {
        WebElement element = null;
        Select list = null;
        element = driver.findElement(By.id("month"));
        list = new Select(driver.findElement(By.id("month")));
        Waits.ForElementVisibility(driver, element);
        return list;
    }

    public static int getSelectedMonthPosition(WebDriver driver){
        List<WebElement> monthOptions = lst_month(driver).getOptions();
        int optionCounter = 0;
        int selectedMonthOptionPosition = 0;
        for (WebElement option : monthOptions) {
            optionCounter++;
            if(option.isSelected()){
                selectedMonthOptionPosition = optionCounter;
                break;
            }
        }
        return selectedMonthOptionPosition;
    }

    public static Select lst_year(WebDriver driver) {
        WebElement element = null;
        Select list = null;
        element = driver.findElement(By.id("year"));
        list = new Select(driver.findElement(By.id("year")));
        Waits.ForElementVisibility(driver, element);
        return list;
    }

    public static int getSelectedYearPosition(WebDriver driver) {
        List<WebElement> monthOptions = lst_year(driver).getOptions();
        int optionCounter = 0;
        int selectedMonthOptionPosition = 0;
        for (WebElement option : monthOptions) {
            optionCounter++;
            if(option.isSelected()){
                selectedMonthOptionPosition = optionCounter;
                break;
            }
        }
        return selectedMonthOptionPosition;
    }

    public static boolean doesRedLightExist(WebDriver driver) {
        WebElement table = driver
                .findElement(By
                        .cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
        List<WebElement> images = table.findElements(By.tagName("img"));
        for (WebElement image : images) {
            String imageSrc = image.getAttribute("src");
            if(imageSrc.contains("/AM/images/RedIcon.png")){
                return true;
            }
        }
        return false;
    }

    public static WebElement btn_RunExport(WebDriver driver) {
        WebElement element = driver.findElement(By.id("runExport"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

    public static WebElement Alt_Successfullmsg(WebDriver driver) {
        WebElement element = driver.findElement(By.id("alert"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

    public static void VerifyAlertmessage(WebDriver driver,String Msg) throws Exception {
        String getMsg;
        try {
            Waits.getSleepLevelFive();
            Waits.getSleepLevelFive();
            getMsg = Alt_Successfullmsg(driver).getText();
            if (getMsg.trim().equals(Msg.trim())) {
                Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
            } else {
                Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
        }

    }

    public static WebElement rbn_Exported(WebDriver driver) {
        WebElement element = driver.findElement(By.id("exported"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }
}
