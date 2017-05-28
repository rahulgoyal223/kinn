package Calltrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import components.Report;
import components.Waits;

public class Mirroring {

	public static void RunMirrorShob(WebDriver driver) throws InterruptedException {
		driver.findElement(By.linkText("Mirroring")).click();		
		Waits.ForBrowserLoad(driver);
		driver.findElement(By.linkText("Run Mirror Job")).click();		
		Report.attachScreenShotToReport(driver);
	}
}
