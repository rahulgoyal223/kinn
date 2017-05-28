package AM.ReportsAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import components.Report;

public class Therapy {

	public static void clickLink(WebDriver driver, String linkname) {	
	boolean isLinkClicked = false;
    List<WebElement> menus = driver.findElements(By.xpath("//*[contains(@href,'/am/reports/') and contains(@class,'HotBox')]"));
    for(WebElement menu : menus) {
    	if(menu.getText().equals(linkname)) {
    		menu.click();
    		isLinkClicked = true;
    		break;
    	}
    if(isLinkClicked = true) {
    	Report.Log(Status.PASS, ""+menu+" under "+linkname+" is selected successfully");
    }else {
    	Report.Log(Status.FAIL, ""+menu+" under "+linkname+" is NOT selected successfully");		                       
    	break;
    	}
      }
	}
}
