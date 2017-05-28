package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class ksGrid {
	/* Claims Manager Grid Operation methods - Start */
	public static String getCellData(WebDriver driver, int rowIndex,
			int colIndex) {
		Waits.ForGlobalAjaxLoader(driver);
		WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
		if (table.getText().trim()
				.contains("There are currently no records to display.")) {
			Report.Log(Status.INFO,
					"There are currently no records to display.");
		} else {
			WebElement allRows = table.findElements(By.xpath(".//tbody/tr"))
					.get(rowIndex);
			String cellData = allRows.findElements(By.xpath(".//td"))
					.get(colIndex).getText();
			return cellData;
		}
		return "";
	}

	public static String getCellDataFromFooter(WebDriver driver, int colIndex){
		Waits.ForGlobalAjaxLoader(driver);
		WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
		if (table.getText().trim()
				.contains("There are currently no records to display.")) {
			Report.Log(Status.INFO,
					"There are currently no records to display.");
		} else {
			WebElement allRows = table.findElements(By.xpath(".//tfoot"))
					.get(0);
			String cellData = allRows.findElements(By.xpath(".//td"))
					.get(colIndex).getText();
			return cellData;
		}
		return "";
	}

	public static String verifyCellData(WebDriver driver, int rowIndex,
			int colIndex, String cellValue) {
		Waits.ForGlobalAjaxLoader(driver);
		WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
		if (table.getText().trim()
				.contains("There are currently no records to display.")) {
			Report.Log(Status.INFO,
					"There are currently no records to display.");
		} else {
			WebElement allRows = table.findElements(By.xpath(".//tbody/tr"))
					.get(rowIndex);
			String cellData = allRows.findElements(By.xpath(".//td"))
					.get(colIndex).getText();
			if (cellValue.trim().toLowerCase()
					.equals(cellData.trim().toLowerCase())) {
				Report.Log(Status.PASS, "value - '" + cellValue
						+ "' is dispayed in row - '" + rowIndex + "'");
			} else {
				Report.Log(Status.FAIL, "value - '" + cellValue
						+ "' is NOT dispayed in row - '" + rowIndex + "'");
				Assert.fail("value - '" + cellValue
						+ "' is NOT dispayed in row - '" + rowIndex + "'");
			}
			return cellData;
		}
		return "";
	}

	public static int getColumnIndex(WebDriver driver, String ColumnName) {
		WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
		List<WebElement> allHeaders = table.findElements(By.tagName("th"));
		int i = 0;
		for (WebElement header : allHeaders) {
			if (header.getText().trim().equals(ColumnName)) {
				return i;
			}
			i = i + 1;
		}
		return 0;
	}

	public static int getRowIndex(WebDriver driver, String rowValue) {
		WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
		List<WebElement> allRows = table.findElements(By.xpath(".//tbody/tr"));
		int i = 0;
		for (WebElement row : allRows) {
			if (row.getText().trim().contains(rowValue)) {
				return i;
			}
			i = i + 1;
		}
		return 0;
	}
}
