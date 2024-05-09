package utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil{

	public static Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String Excelpath) throws Throwable {
		FileInputStream fi=new FileInputStream(Excelpath);
		wb=WorkbookFactory.create(fi);
	}
	//count no of rows in a sheet
	public int rowcount(String SheetName) {
		return wb.getSheet(SheetName).getLastRowNum();

	}
	//method for reading cell data 
	public String getCellData(String SheetName, int row, int coloum) {
		String data;
		if (wb.getSheet(SheetName).getRow(row).getCell(coloum).getCellType()==CellType.NUMERIC) {
			int celldata=(int) wb.getSheet(SheetName).getRow(row).getCell(coloum).getNumericCellValue();
			data=String.valueOf(celldata);

		}else                  
		{
			data=wb.getSheet(SheetName).getRow(row).getCell(coloum).getStringCellValue();
		}
		return data;
	}
	//method for set cell data
	public void setcelldata(String SheetName,int row, int coloums,String status, String writeExcelpath) throws Throwable {
		//get sheet from wb
		Sheet ws=wb.getSheet(SheetName);
		//get row from sheet
		Row rownum=ws.getRow(row);
		//create a cell in a row
		Cell cell=rownum.createCell(coloums);
		//write status
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) {
			CellStyle style=wb.createCellStyle();
			Font font =wb.createFont();
			//colour with green
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(coloums).setCellStyle(style);


		}else if (status.equalsIgnoreCase("Fail")) {
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(coloums).setCellStyle(style);

		}else if (status.equalsIgnoreCase("Blocked")) {
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(coloums).setCellStyle(style);

		}
		FileOutputStream fo=new FileOutputStream(writeExcelpath);
		wb.write(fo);

	}
	
}