package KH.Menu;

import com.aventstack.extentreports.Status;
import components.Report;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TopMenu {

    public static void Select(WebDriver driver, String MenuItems) throws InterruptedException {
        try {
        	Report.attachScreenShotToReport(driver);
			toNavigateMenuBar(driver, MenuItems);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);*/
    }

    //@ Inner methods to select menu item
    private static void toNavigateMenuBar(WebDriver driver, String Menus) throws Exception {
        String[] parts = Menus.split("/");
        int count = parts.length;
        if (count == 1) {
            String part1 = parts[0]; // Main Menu
            toSelectMainMenu(driver, part1);
        }
        if (count == 2) {
            String part1 = parts[0];
            toSelectMainMenu(driver, part1);
            String part2 = parts[1];
            toSelectSubmenu(driver, part2);
        }
        if (count == 3) {                 
            if(Menus.contains("Reports / Admin")) {
            	 String[] parts1 = Menus.split("/");
            	 int count1 = parts1.length;
            	 String part1 = parts[0];
            	 String part2 = parts[1];
                 String part3 = parts[2];
                 toSelectMainMenu(driver, part1);
                 toSelectSubmenu(driver, part2.concat("/").concat(part3));
            }
            else {
            String part1 = parts[0];
            toSelectMainMenu(driver, part1);
            String part2 = parts[1]; 
            toMouseHoverOnSubmenu(driver, part2);
            String part3 = parts[2];
            toSelectSubmenu(driver, part3);
            }            
        }
    }

    private static void toSelectMainMenu(WebDriver driver, String mainMenu) throws Exception {

        try {
            boolean isMenuItemFound = false;
            //Thread.sleep(Waits.getSleepLevelTwo());
            List<WebElement> mainMnu = driver.findElements(By.xpath("//*[contains(@class,'menuButton') or @data-toggle='dropdown' or @class='ng-binding']"));
            for (int i = 0; i < mainMnu.size(); i++) {
                String mainMnu1 = mainMnu.get(i).getText();
                if (mainMnu1.trim().equals(mainMenu)) {
                    driver.findElement(By.linkText(mainMenu)).click();
                    isMenuItemFound = true;
                    break;
                }
            }
            if (isMenuItemFound) {
                Report.Log(Status.PASS, "Main menu - " + mainMenu + " is selected");
            } else {
                Report.Log(Status.FAIL, "Main menu - " + mainMenu + " is NOT selected");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private static void toMouseHoverOnSubmenu(WebDriver driver, String subMenu) throws Exception {
        try {
            boolean isMenuItemFound = false;
            List<WebElement> subMnu = driver.findElements(By.xpath("//*[@class='menuItemText' or @class='ng-binding']"));
            for (WebElement Mnu : subMnu) {
                String rnValue = Mnu.getText().trim();
                if (rnValue.trim().contains(subMenu)) {
                    Actions builder = new Actions(driver);
                    builder.moveToElement(Mnu).build().perform();
                    isMenuItemFound = true;
                    break;
                }
            }
            if (isMenuItemFound) {
                Report.Log(Status.PASS, "SubMain menu - " + subMenu + " is selected");
            } else {
                Report.Log(Status.FAIL, "SubMain menu - " + subMenu + " is NOT selected");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static void toSelectSubmenu(WebDriver driver, String subMenu1) throws Exception {
        try {
            boolean isMenuItemFound = false;
            List<WebElement> subMenu2 = driver.findElements(By.xpath("//*[(contains(@href,'/am/') and @class='menuitem')"
                    + "or (contains(@href,'../EditEpisode.cfm?Episodekey=') and @class='menuitem')"
                    + "or (contains(@href,'/AM/') and @class='menuitem')"
                    + "or (contains(@href,'/am/') and @class='menuitem')"
                    + "or (contains(@href,'/AM/') and @class='ng-binding')"
                    + "or (contains(@href,'/am/') and @class='ng-binding')"
                    + "or (contains(@href,'/EHR/'))]"));
            for (WebElement Mnu : subMenu2) {
                String rnValue = Mnu.getText().trim();
                if (rnValue.contains(subMenu1)) {
                    Mnu.click();
                    isMenuItemFound = true;
                    break;
                }

            }
            if (isMenuItemFound) {
                Report.Log(Status.PASS, "SubMain menu - " + subMenu1 + " is selected");
            } else {
                Report.Log(Status.FAIL, "SubMain menu - " + subMenu1 + " is NOT selected");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
