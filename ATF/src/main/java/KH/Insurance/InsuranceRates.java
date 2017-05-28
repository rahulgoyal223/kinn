package KH.Insurance;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import DataSource.Datatable;
import com.aventstack.extentreports.Status;
import components.Report;

public class InsuranceRates {
	private static WebElement element = null;
	private static String strInputValue;
	
	//@ Fill level of care rate information.
	public static void FillLevelOfCareRates(WebDriver driver) throws Exception {
	
		try {
			
			List<WebElement> revenueCodes = txt_LOC_RevenueCode(driver);
			List<WebElement> hcpcsCodes = txt_LOC_HCPCS(driver);

			// Set revenue code and hcpcs fields for each rown in data source
			for (int i = 1; i < Datatable.GetRowCount(); i++) {

				Datatable.setCurrentRow(i);

				if (i <= revenueCodes.size()) {
					WebElement revenueCode = revenueCodes.get(i-1);
					strInputValue = Datatable.GetValue("LOC_RevenueCode");
					if (!strInputValue.trim().isEmpty()) {
						revenueCode.clear();
						revenueCode.sendKeys(strInputValue);
					}
				}

				if (i <= hcpcsCodes.size()) {
					WebElement hcpcsCode = hcpcsCodes.get(i-1);
					strInputValue = Datatable.GetValue("LOC_HCPCS");
					if (!strInputValue.trim().isEmpty()) {
						hcpcsCode.clear();
						hcpcsCode.sendKeys(strInputValue);
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			Report.Log(Status.FAIL, "Insurance Rate Level of Care details have NOT been filled");
		}

		return;
	}
	
	// @Level Of Care Rates
	public static WebElement div_LOC_Heading(WebDriver driver) {
		element = driver.findElement(By.id("locHeading"));
		return element;
	}

	public static List<WebElement> txt_LOC_RevenueCode(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.id("RevenueCode_"));
		return elements;
	}

	public static List<WebElement> txt_LOC_HCPCS(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.id("BillingCode_"));
		return elements;
	}

	// @Action Objects
	
	public static WebElement btn_Update(WebDriver driver) {
		element = driver.findElement(By.id("updateBtn"));
		return element;
	}

}
