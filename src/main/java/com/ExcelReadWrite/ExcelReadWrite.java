package com.ExcelReadWrite;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

	public static FileInputStream file;
	public static FileOutputStream fileOP;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFCell cell;
	public static XSSFRow row;

	/*
	 * Method Name : readExcel
	 * Method Usage : Getting the Relative path for excel from Source Excel folder and reading the file.
	 */

	public static String readExcel(int RowNum, int ColNum) throws Exception {

		String filePath = "./src/main/java/datatable/BookMyShow.xlsx";
		file = new FileInputStream(filePath);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheetAt(0);

		try {
			cell = ws.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;



		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
}