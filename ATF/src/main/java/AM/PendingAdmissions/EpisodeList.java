package AM.PendingAdmissions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EpisodeList {

	private static Select listbox;

	public static Select lst_EL_Add(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("createNewOptions")));
		return listbox;
	}
}
