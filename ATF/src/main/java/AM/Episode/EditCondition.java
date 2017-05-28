package AM.Episode;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Select2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import components.Waits;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditCondition {

		private static WebElement element = null;
		 private static Select list = null;
		private static String strInputValue;
		 
		  public static  WebElement lnk_Condition(WebDriver driver) {
			    element = driver.findElement(By.linkText("Condition"));
			    Waits.ForElementVisibility(driver, element);
			    return element;
			}
		    
		public static WebElement txt_Occurrencecode31a_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate31a"));
			return element;
		}
		
		public static WebElement txt_Occurrencecode31b_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate31b"));
			return element;
		}

		public static WebElement txt_Occurrencecode32a_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate32a"));
			return element;
		}
		
		public static WebElement txt_Occurrencecode32b_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate32b"));
			return element;
		}


		public static WebElement txt_Occurrencecode33a_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate33a"));
			return element;
		}
		
		public static WebElement txt_Occurrencecode33b_date(WebDriver driver) {
			element = driver.findElement(By.name("oDate33b"));
			return element;
		}

	public static WebElement txt_Occurrencecode34a_date(WebDriver driver) {
		element = driver.findElement(By.name("oDate34a"));
		return element;
	}
	
	public static WebElement txt_Occurrencecode34b_date(WebDriver driver) {
		element = driver.findElement(By.name("oDate34b"));
		return element;
	}
	
	

	public static WebElement txt_Occurrencecode35a_date(WebDriver driver) {
		element = driver.findElement(By.name("osStartDate35"));
		return element;
	}
	
	public static WebElement txt_Occurrencecode35b_date(WebDriver driver) {
		element = driver.findElement(By.name("osEndDate35"));
		return element;
	}
	

	public static WebElement txt_Occurrencecode36a_date(WebDriver driver) {
		element = driver.findElement(By.name("osStartDate36"));
		return element;
	}
	
	public static WebElement txt_Occurrencecode36b_date(WebDriver driver) {
		element = driver.findElement(By.name("osEndDate36"));
		return element;
	}

	public static WebElement txt_RemarksNotes(WebDriver driver){
		  	element = driver.findElement(By.id("RemarksNotes"));
		  	return element;
	}
		
	public static WebElement btn_save(WebDriver driver) {
		element = driver.findElement(By.id("save_button"));
		return element;
	}

	public static void EnterOccurrenceCode(WebDriver driver, String occurenceCodeId, String entry) {
		Select2.EnterSelect2Code(driver, occurenceCodeId, entry);
	}

	public static void EnterConditionCode(WebDriver driver, String conditionCodeId, String entry) {
		Select2.EnterSelect2Code(driver, conditionCodeId, entry);
	}

	public static WebElement GetConditionCode(WebDriver driver, String conditionCodeId) {
		return Select2.GetSelect2DropDown(driver, conditionCodeId);
	}

	public static WebElement GetOccurrenceCode(WebDriver driver, String occurrenceCodeId) {
		return Select2.GetSelect2DropDown(driver, occurrenceCodeId);
	}
	
// @Method To fill the EditCondition form
	public static void FillEditConditionForm(WebDriver driver) throws Exception {
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode18",Datatable.GetValue("conditionCode18"));
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode19",Datatable.GetValue("conditionCode19"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode20",Datatable.GetValue("conditionCode20"));
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode21",Datatable.GetValue("conditionCode21"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode22",Datatable.GetValue("conditionCode22"));	        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode23",Datatable.GetValue("conditionCode23"));	        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode24",Datatable.GetValue("conditionCode24"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode25",Datatable.GetValue("conditionCode25"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode26",Datatable.GetValue("conditionCode26"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode27",Datatable.GetValue("conditionCode27"));		        
        AM.Episode.EditCondition.EnterConditionCode(driver, "conditionCode28",Datatable.GetValue("conditionCode28"));        
        
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode31a",Datatable.GetValue("oCode31a"));
		strInputValue = Datatable.GetValue("oDate31a");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode31a_date(driver).clear();
			txt_Occurrencecode31a_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode31b",Datatable.GetValue("oCode31b"));   
		strInputValue = Datatable.GetValue("oDate31b");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode31b_date(driver).clear();
			txt_Occurrencecode31b_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode32a",Datatable.GetValue("oCode32a"));
		strInputValue = Datatable.GetValue("oDate32a");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode32a_date(driver).clear();
			txt_Occurrencecode32a_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode32b",Datatable.GetValue("oCode32b"));	
		strInputValue = Datatable.GetValue("oDate32b");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode32b_date(driver).clear();
			txt_Occurrencecode32b_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode33a",Datatable.GetValue("oCode33a"));	
		strInputValue = Datatable.GetValue("oDate33a");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode33a_date(driver).clear();
			txt_Occurrencecode33a_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode33b",Datatable.GetValue("oCode33b"));
		strInputValue = Datatable.GetValue("oDate33b");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode33b_date(driver).clear();
			txt_Occurrencecode33b_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode34a",Datatable.GetValue("oCode34a"));	
		strInputValue = Datatable.GetValue("oDate34a");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode34a_date(driver).clear();
			txt_Occurrencecode34a_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "oCode34b",Datatable.GetValue("oCode34b"));
		strInputValue = Datatable.GetValue("oDate34b");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode34b_date(driver).clear();
			txt_Occurrencecode34b_date(driver).sendKeys(strInputValue);
		}
        
		AM.Episode.EditCondition.EnterOccurrenceCode(driver, "osCode35",Datatable.GetValue("osCode35"));	
		strInputValue = Datatable.GetValue("osStartDate35");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode35a_date(driver).clear();
			txt_Occurrencecode35a_date(driver).sendKeys(strInputValue);
		}		strInputValue = Datatable.GetValue("osEndDate35");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode35b_date(driver).clear();
			txt_Occurrencecode35b_date(driver).sendKeys(strInputValue);
		}
        AM.Episode.EditCondition.EnterOccurrenceCode(driver, "osCode36",Datatable.GetValue("osCode36"));
		strInputValue = Datatable.GetValue("osStartDate36");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode36a_date(driver).clear();
			txt_Occurrencecode36a_date(driver).sendKeys(strInputValue);
		}		strInputValue = Datatable.GetValue("osEndDate36");
		if (!strInputValue.trim().isEmpty()) {
			txt_Occurrencecode36b_date(driver).clear();
			txt_Occurrencecode36b_date(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("remarks");
		if (!strInputValue.trim().isEmpty()) {
			txt_RemarksNotes(driver).clear();
			txt_RemarksNotes(driver).sendKeys(strInputValue);
		}
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement saveButtonElement = AM.Episode.EditCondition.btn_save(driver);
		saveButtonElement.click();
		//Waiting for staleness of save button before moving on.
		//wait.until(ExpectedConditions.stalenessOf(saveButtonElement));
	}

}



