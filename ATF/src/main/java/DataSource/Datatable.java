package DataSource;

import com.aventstack.extentreports.Status;
import components.Config;
import components.Report;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Datatable {

	// @ Declaring Global variables
	private static int CurrentRow = 1;
	private static XSSFRow row;
	private static XSSFSheet sh;
	private static XSSFWorkbook wb;
	private static FileInputStream fs;

	// Defining datatable methods
	private static XSSFSheet SetCurrentSheet(String filename, String sheetname) {
		try {
			fs = new FileInputStream(filename);
			wb = new XSSFWorkbook(fs);
			sh = wb.getSheet(sheetname);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sh;
	}

	public static int GetRowCount() {
		try {
			return sh.getPhysicalNumberOfRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int GetColumnCount() {
		try {
			row = sh.getRow(0);
			return (int) row.getLastCellNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String GetValue(String columnvalue) throws Exception, IOException, InterruptedException{
		try {
			int rownum = GlobalData.getCurrentDataRow();
			row = sh.getRow(rownum);
			XSSFRow toprow = sh.getRow(0);
			FormulaEvaluator evaluator = wb.getCreationHelper()
					.createFormulaEvaluator();
			for (int columnnum = 0; columnnum < toprow.getLastCellNum(); columnnum++) {

				DataFormatter formatter = new DataFormatter();
				Cell cell = toprow.getCell(columnnum);
				String columnname = formatter.formatCellValue(cell);

				if (columnvalue.equals(columnname)) {
					Cell cell2 = row.getCell(columnnum);
					String value = formatter.formatCellValue(cell2, evaluator);
					Report.Log(Status.INFO, "Value '" + value
							+ "' is read from data table column '" + columnname
							+ "'");
					return value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Report.Log(Status.INFO, "Column '" + columnvalue
				+ "' is not found in sheet '" + sh.getSheetName());
		return "No Column Found";
	}

	public static void loadDataSheet(String fileName, String sheetName) {
		String testDataLocation = Config.getSourcePath()
				+ "test\\resources\\testdata\\";
		SetCurrentSheet((testDataLocation + fileName), sheetName);
		Report.Log(Status.INFO, "Test data sheet '" + sheetName
				+ "' is loaded from '" + testDataLocation);
		Datatable.setCurrentRow(1);// This line sets the global data to 1 every
									// time the test script begins
	}

	// Added on 17-Nov-2016
	public static String SetValue(String filename, String Sheetname,
			int rownum, String columnvalue, String txttowrite) throws Exception {
		row = sh.getRow(rownum);
		XSSFRow toprow = sh.getRow(0);
		for (int columnnum = 0; columnnum < toprow.getLastCellNum(); columnnum++) {

			DataFormatter formatter = new DataFormatter();
			Cell cell = toprow.getCell(columnnum);
			String columnname = formatter.formatCellValue(cell);

			if (columnvalue.equals(columnname)) {
				Cell cells = row.getCell(columnnum);
				cells.setCellValue(txttowrite);
				FileOutputStream fos = new FileOutputStream(
						components.Config.getSourcePath()
								+ "test\\resources\\testdata\\" + "" + filename
								+ "");
				wb.write(fos);
				System.out.println(txttowrite);
				fos.close();
				break;

			}

		}
		return txttowrite;
	}

	public static int getCurrentRow() {
		return CurrentRow;
	}

	public static void setCurrentRow(int currentRow) {
		Report.Log(Status.INFO, "Datatable current row is set to - "
				+ currentRow);
		GlobalData.setCurrentDataRow(currentRow);
		CurrentRow = currentRow;
	}
}