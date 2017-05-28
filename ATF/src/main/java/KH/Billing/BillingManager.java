package KH.Billing;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BillingManager {
    public WebDriver driver;

    //Input type : RAP/Pending creation
    public static void SelectMenu(WebDriver driver, String MenuItems) throws Exception {
    	Waits.ForGlobalAjaxLoader(driver);
    	NavigateBillingManagerLinks(driver, MenuItems);
        Waits.ForGlobalAjaxLoader(driver);
    }

    //Input type : Just Link text(Case Sensitive)
    public static void ClickLink(WebDriver driver, String Menus) throws Exception {
    	Waits.ForBrowserLoad(driver);
        ClickLinks(driver, Menus);
        Waits.ForBrowserLoad(driver);
    }

    //Reusable to navigate thru Menu in Billing Manager screen - Input Type(Eg) : Medicare/Remittance Manager
    private static void ClickLinks(WebDriver driver, String Menus) throws Exception {
        try {
            String[] parts = Menus.split("/");
            //System.out.println("The length of the String : "+count);
            String part1 = parts[0];

            switch (part1) {
                //To Navigate thru Medicare	Menus
                case "Medicare":
                    String part2 = parts[1];
                    if (part2.equals("Remittance Manager")) {
                        driver.findElement(By.id("medicare-remittance-manager-link")).click();
                        Report.Log(Status.PASS, "Remittance Manager link under Medicare is clicked successfully");
                        break;
                    } else if (part2.equals("Generate NOEs")){
                        driver.findElement(By.id("medicare-generate-noes-link")).click();
                        Report.Log(Status.PASS, "Generate NOEs link under Medicare is clicked successfully");
                        break;
                    }
                    break;

                //To Navigate thru Managed Care Menus
                case "Managed Care":
                    String part21 = parts[1];
                    //System.out.println(part21);
                    if (part21.equals("Remittance Manager")) {
                        driver.findElement(By.id("managed-care-remittance-manager-link")).click();
                        Report.Log(Status.PASS, "Remittance Manager link under Managed Care is clicked successfully");
                        break;
                    }
                    break;

                //To Navigate thru Room and Board Menus
                case "Room and Board":
                    String part31 = parts[1];
                    //System.out.println(part21);
                    if (part31.equals("Remittance Manager")) {
                        driver.findElement(By.id("room-and-board-remittance-manager-link")).click();
                        Report.Log(Status.PASS, "Remittance Manager link under Room and Board is clicked successfully");
                        break;
                    }
                    break;

                //To Navigate thru Reports Links
                case "Reports":
                    String part41 = parts[1];
                    //System.out.println(part31);
                    List<WebElement> rptlnks = driver.findElements(By.xpath("//*[contains(@id,'Report') or contains(@id,'-link')]"));
                    boolean isReportLinkSelected = false;
                    for (int i = 0; i <= rptlnks.size(); i++) {
                        String reportlnk = rptlnks.get(i).getText();
                        if (part41.equals(reportlnk)) {
                            driver.findElement(By.linkText("" + part41 + "")).click();
                            isReportLinkSelected = true;
                            break;
                        }
                    }
                    if (isReportLinkSelected) {
                        Report.Log(Status.PASS, "" + part41 + " link under Reports is clicked successfully");
                    } else {
                        Report.Log(Status.FAIL, "" + part41 + " link under Reports is NOT clicked successfully");
                    }
                    break;

                //To Navigate thru Hard Close Links
                case "Hard Close":
                    String part51 = parts[1];
                    boolean isManagedCareLinkSelected = false;
                    List<WebElement> hclnks = driver.findElements(By.xpath("//*[contains(@href,'#/HM/hard-close') or contains(@href,'#/HM/billing/prior-period-adjustment-report')]"));
                    for (int i = 0; i <= hclnks.size(); i++) {
                        String hardlnk = hclnks.get(i).getText();
                        if (part51.equals(hardlnk)) {
                            driver.findElement(By.linkText("" + part51 + "")).click();
                            isManagedCareLinkSelected = true;

                            break;
                        }
                    }
                    if (isManagedCareLinkSelected) {
                        Report.Log(Status.PASS, "" + part51 + " link under Hard Close is clicked successfully");
                    } else {
                        Report.Log(Status.PASS, "" + part51 + " link under Hard Close is NOT clicked successfully");
                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //@ Inner methods to select sub-items on button menus
    private static void NavigateBillingManagerLinks(WebDriver driver, String Menus) throws Exception {
       Waits.ForBrowserLoad(driver);
    	try {
            String[] parts = Menus.split("/");
            int count  = parts.length;
            String part1 = parts[0];
            
            switch (part1) {
                case ("Medicare"):
                    ClickClaimsManagerButton(driver, "medicareClaims");
                    break;

                case ("Managed Care"):
                    ClickClaimsManagerButton(driver, "managedCareClaims");
                    break;

                case ("Room and Board"):
                    ClickClaimsManagerButton(driver, "roomAndBoardClaims");
                    break;
                }

            if (count == 2) {
                String part2 = parts[1];
                SelectLinks(driver, part2);
            }
            if (count == 3) {
				
				String part2 = parts[1];
				String part3 = parts[2];
				String newPart = part2.concat("/").concat(part3);
				SelectLinks(driver, newPart);
			
            }
    	}
			
		 catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Reusable to click any button in a page
	private static void ClickButtons(WebDriver driver, String mainMenu)
			throws Exception {

		try {
			boolean isButtonclicked = false;
			List<WebElement> mainMnu = driver
					.findElements(By.tagName("button"));
			for (int i = 1; i <= mainMnu.size(); i++) {
				String mainMnuItems = mainMnu.get(i).getText();
				if (mainMnuItems.equals(mainMenu)) {
					mainMnu.get(i).click();
					isButtonclicked = true;
					break;
				}
			}
			if (isButtonclicked) {
				Report.Log(Status.PASS, "" + mainMenu
						+ " Button is clicked successfully");
			} else {
				Report.Log(Status.FAIL, "" + mainMenu
						+ " Button is NOT clicked successfully");
			}

		} catch (Exception e) {
			throw e;
		}

	}

    //Click the correct Claims Manager button
    private static void ClickClaimsManagerButton(WebDriver driver, String claimsType) throws Exception {
        try {
            driver.findElement(By.id(claimsType)).click();
        } catch (Exception e) {
            throw e;
        }

    }

    //Reusable to select the links in Billing manager screen
    private static void SelectLinks(WebDriver driver, String subMenu1) throws Exception {
        try {        	
            boolean isLinkSelected = false;
            List<WebElement> subMenu2 = driver.findElements(By.xpath("//*[contains(@href,'#/HM/')]"));
            for (WebElement Mnu : subMenu2) {
                if (Mnu.getText().trim().equals(subMenu1)) {
                    Mnu.click();
                    isLinkSelected = true;
                    break;
                }
            }
            if (isLinkSelected) {
                Report.Log(Status.PASS, "" + subMenu1 + " link is selected successfully");
            } else {
                Report.Log(Status.FAIL, "" + subMenu1 + " link is NOT selected successfully");
            }

        } catch (Exception e) {
            throw e;
        }


    }
}
