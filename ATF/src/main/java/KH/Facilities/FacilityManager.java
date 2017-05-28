package KH.Facilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

public class FacilityManager {
	
	private static WebElement element = null;
	private static Select list = null;

	// @Edit Facility Objects
	// ## Show Entries
	public static Select lst_FM_ShowEntries(WebDriver driver) {
		element = driver.findElement(By.id("facPageSize"));
		list = new Select(driver.findElement(By.id("facPageSize")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	
	// ## Facility Name*
	public static WebElement txt_FM_FilterSearch(WebDriver driver) {
		element = driver.findElement(By.id("facFilter"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement SelectFacility(WebDriver driver, String facility)
			throws Exception {

		try {
			Waits.ForGlobalAjaxLoader(driver);
			String FullName = facility;
			boolean isFacilitySelected = false;
			WebElement table = driver.findElement(By.xpath("//*[@id='searchTextResults']/tbody[1]"));
			List<WebElement> allrows = table.findElements(By.xpath("//*[@id='searchTextResults']/tbody[1]/tr/td[1]/span[2]/span/a"));
			int count = allrows.size();
			for (int i=0; i<=count; i++)
			{
				String Text = allrows.get(i).getText().trim();
				if(Text.equals(FullName))
				{
					element = driver.findElement(By.xpath("//*[@id='searchTextResults']/tbody[1]/tr[" + (i+2) + "]/td[1]/span[2]/span/a"));
					element.click();
					isFacilitySelected = true;
					Report.Log(Status.PASS, "Facility - '" + FullName
							+ "' is selected");
					Assert.assertTrue(isFacilitySelected, "Facility is selected");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
}
