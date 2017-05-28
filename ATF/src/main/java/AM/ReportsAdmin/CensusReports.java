package AM.ReportsAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import components.Report;

public class CensusReports {
	// @Method to select task inside Census report
	public static void SelectTask(WebDriver driver, String task) {
		boolean isTaskSelected = false;
		List<WebElement> census = driver.findElements(By.tagName("a"));
		for (WebElement cen : census) {
			String centxt = cen.getText();
			if (centxt.trim().equals(task)) {
				cen.click();
				break;
			}
		}
		if (isTaskSelected) {
			Report.Log(Status.PASS, "" + task
					+ " link is selected successfully");
		} else {
			Report.Log(Status.FAIL, "" + task
					+ " link is NOT selected successfully");
		}
	}

	//@Method to select link inside Census report
	public static void clickLink(WebDriver driver, String linkname) {		
		boolean isLinkClicked = false;
		List<WebElement> census = driver.findElements(By.xpath("//*[contains(@href,'/am/reports/') and @class = 'hotbox']"));
		for(WebElement cen: census) {
			String centxt = cen.getText();
			if(centxt.trim().equals(linkname)) {
				cen.click();
				break;
			}
		}	
	if (isLinkClicked) {
        Report.Log(Status.PASS, "" + linkname + " link is selected successfully");
    } else {
        Report.Log(Status.FAIL, "" + linkname + " link is NOT selected successfully");
    }
  }

}
