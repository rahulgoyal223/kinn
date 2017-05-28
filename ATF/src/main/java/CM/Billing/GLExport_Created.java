package CM.Billing;

import components.ksGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by austin.ledbetter on 1/27/2017.
 */
public class GLExport_Created {
    public static String getLatestBatchNumber(WebDriver driver) {
        String cellData = ksGrid.getCellData(driver, 1, 3);
        return cellData;
    }
}
