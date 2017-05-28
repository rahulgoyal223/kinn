package AM.Episode;

import DataSource.Datatable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Create {

	// @declare all episode variables in this section
	private static WebElement element = null;
	private static Select listbox = null;

	// @ Add Episode reusable methods
	public static void FillAddEpisode(WebDriver driver) throws Exception {
		// Datatable.SetCurrentSheet("C:\\Users\\Sam\\Downloads\\TC003.xlsx",
		// "Sheet2");
		String StrInputvalue = Datatable.GetValue("Startofcaredate");
		lst_AD_startofcaredate(driver).selectByVisibleText(StrInputvalue);

	}

	// @ Create Episode Screen test objects
	public static Select lst_AD_startofcaredate(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("admissionKeySelect")));
		return listbox;
	}

	public static WebElement dt_Episodestartdate(WebDriver driver) {
		element = driver.findElement(By.id("Startdate"));
		return element;
	}

	public static Select lst_primaryinsurance(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("primaryInsurance")));
		return listbox;
	}

	public static Select lst_primaryagent(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("Clinician")));
		return listbox;
	}

	public static WebElement btn_AddEpisode(WebDriver driver) {
		element = driver.findElement(By.id("addEpisodeButton"));
		return element;

	}

	public static WebElement btn_yes(WebDriver driver) {
		element = driver.findElement(By
				.xpath("/html/body/div[5]/div[11]/div/button[1]"));
		return element;

	}

	public static WebElement title_manager(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='AMContainer']/div[7]/div/h1"));
		return element;

	}

}
