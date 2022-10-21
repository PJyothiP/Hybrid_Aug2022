package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	XSSFWorkbook wb;
	public ExcelOperations(String path) throws IOException {
		FileInputStream f = new FileInputStream(path);
		wb = new XSSFWorkbook(f);
	}
	
	public int getRowCount(String sheet) {
		return wb.getSheet(sheet).getLastRowNum();
	}
	
	public int getColumnCount(String sheet) {
		return wb.getSheet(sheet).getRow(0).getLastCellNum();
	}
	
	public String getData(int row,int columnn,String sheet) {
		DataFormatter df = new DataFormatter();
		return df.formatCellValue(wb.getSheet(sheet).getRow(row).getCell(columnn));
	}
	
}
