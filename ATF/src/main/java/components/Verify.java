package components;

import com.aventstack.extentreports.Status;




import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Verify {


    public static void ExactPageTitle(WebDriver driver, String PageTitle) {

        try {
            Waits.ForBrowserLoad(driver);
            String title = driver.getTitle();
            if (title.trim().equals(PageTitle)) {
                Report.Log(Status.PASS, "The page title displayed is - '" + title + "'");
                Report.attachScreenShotToReport(driver);
            } else {
                Report.Log(Status.FAIL, "The page title displayed is - '" + title + "' NOT '" + PageTitle + "'");
                Report.attachScreenShotToReport(driver);
                Assert.fail("The page title displayed is - '" + title + "' NOT '" + PageTitle + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
	public static void PDFText(WebDriver driver,String PDFlink, String toVerify) throws IOException {

		driver.get(PDFlink);
		URL url = new URL(PDFlink);
		BufferedInputStream pdoc = new BufferedInputStream(url.openStream());
		PDDocument doc = PDDocument.load(pdoc);
		String text = new PDFTextStripper().getText(doc);
		if(text.contains(toVerify)) {
			Report.Log(Status.PASS, "PDF has the given text : "+toVerify+"");
		}else {
			Report.Log(Status.FAIL, "PDF does not have the given text : "+toVerify+"");
			Assert.fail("PDF does not have the given text : "+toVerify+"");
		}
		doc.close();
		pdoc.close();
		System.out.println(text);

	}

	public static void TextFileContains(String fileLink, String toVerify) throws IOException {

		URL url = new URL(fileLink);
		BufferedInputStream textStream = new BufferedInputStream(url.openStream());
		String text = IOUtils.toString(textStream);
		if(text.contains(toVerify)) {
			Report.Log(Status.PASS, "PDF has the given text : "+toVerify+"");
		}else {
			Report.Log(Status.FAIL, "PDF does not have the given text : "+toVerify+"");
			Assert.fail("PDF does not have the given text : "+toVerify+"");
		}
		System.out.println(text);

	}


	public static void PartialPageTitle(WebDriver driver, String PageTitle) {
		try {
			Waits.ForBrowserLoad(driver);
			String title = driver.getTitle();
			if (title.trim().contains(PageTitle)) {
				Report.Log(Status.PASS, "The page title displayed is - '"
						+ title + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The page title displayed is - '"
						+ title + "' NOT '" + PageTitle + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The page title displayed is - '"
						+ title + "' NOT '" + PageTitle + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ExactPageHeader(WebDriver driver, String Header) {
		try {
			Waits.ForBrowserLoad(driver);
			String header = driver.findElement(
					By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]")).getText();
			if (header.trim().equals(Header)) {
				Report.Log(Status.PASS, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The page header displayed is - '"
						+ header + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Homepage(WebDriver driver) {
		try {
			Waits.ForBrowserLoad(driver);
			String header = driver.findElement(
					By.xpath("//div[@class='left']//h1")).getText();
			if ((header.trim().equals("Patient Manager")) || (header.trim().equals("K-Mail")) || (header.trim().equals("Hotbox"))) {
				Report.Log(Status.PASS, "User landed on home page");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The page header displayed is - '"
						+ header + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	public static void ElementContainsText(WebDriver driver, String Text, WebElement element) {
		try {
			Waits.ForBrowserLoad(driver);
			String elementTxt = element.getText();
			if (elementTxt.trim().contains(Text)) {
				Report.Log(Status.PASS, "The text displayed is - '"
						+ elementTxt + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The text displayed is - '"
						+ elementTxt + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The text displayed is - '"
						+ elementTxt + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void PartialPageHeader(WebDriver driver, String Header){
		try {
			Waits.ForBrowserLoad(driver);
			String header = driver.findElement(
					By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]")).getText();
			if (header.trim().contains(Header)) {
				Report.Log(Status.PASS, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The page header displayed is - '"
						+ header + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void verifyDropdownSelectedOption(WebDriver driver, WebElement dropdown, String expectedValue, String message) {
		
		String currentValue = new Select( dropdown ).getFirstSelectedOption().getAttribute("value");

		try {

			Assert.assertEquals(currentValue, expectedValue );

			Report.Log(Status.PASS, message + currentValue );

		} catch(AssertionError ae){
			Report.Log(Status.FAIL, message + currentValue  + ". Error: " + ae.getMessage());
			Report.attachScreenShotToReport( driver, dropdown );
			throw ae;
		}
	}
	
	public static void VerifySelectedText(WebDriver driver, Select element, String SelectedText) {
		try {
			Waits.ForBrowserLoad(driver);
			String selectText = element.getFirstSelectedOption().getText();
			if (selectText.trim().contains(SelectedText)) {
				Report.Log(Status.PASS, "The text Selected is - '"
						+ selectText + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The text Selected is - '"
						+ selectText + "' NOT '" + SelectedText + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The text Selected is - '"
						+ selectText + "' NOT '" + SelectedText + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void VerifyBoolStatus(boolean current, boolean expected,
			String message, String trueSuffix, String falseSuffix) {
		try {

    		Assert.assertEquals(current, expected);
    		String passSuffix =  (expected ? trueSuffix : falseSuffix);
    		
        	Report.Log(Status.PASS, message + passSuffix );
         
        } catch(AssertionError ae){
        	String failSuffix =  ( !expected ? trueSuffix : falseSuffix);
        	Report.Log(Status.FAIL, message + failSuffix + ". Error: " + ae.getMessage());
        	throw ae;
        }
	}
	
	public static void VerifyElementNotPresent(WebElement element) {
		try{
			Assert.assertFalse(element.isDisplayed());
			Report.Log(Status.PASS, "The Element is NOT located on the page");
		} catch(AssertionError ae) {
			Report.Log(Status.FAIL, "The Element is located on the page");
		}
	}

}
