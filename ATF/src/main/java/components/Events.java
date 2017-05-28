package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Events {
	public static Actions Fire(WebDriver driver) {
		Actions builder = new Actions(driver);
		return builder;
	}

}
