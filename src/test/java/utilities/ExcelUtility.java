package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;	
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	public int getRowcount(String sheetName) throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook. getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	public int getCellcount(String sheetName,int rowNum) throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook. getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName,int rowNum,int colnum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook. getSheet(sheetName);
		row=sheet.getRow(rowNum);	
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e){
			data="";
			
		}
		return data;
		
	}
	
	public void setCellData(String sheetName,int rowNum,int colnum,String data) throws IOException {
		File xlfile= new File(path);
		if(!xlfile.exists()) {
			workbook =new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook =new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1)
			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
			
			if(sheet.getRow(rowNum)==null)
				sheet.createRow(rowNum);
			row=sheet.getRow(rowNum);
			
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close(); 
			
	}

	public void fillGrennColor(String sheetName,int rowNum,int colnum) throws IOException {
		fi=new FileInputStream(path);
		workbook =new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rowNum);
		cell=row.createCell(colnum);
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);;
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	public void fillRedColor(String sheetName,int rowNum,int colnum) throws IOException {
	fi=new FileInputStream(path);
	workbook =new XSSFWorkbook(fi);
	sheet=workbook.getSheet(sheetName);
	
	row=sheet.getRow(rowNum);
	cell=row.createCell(colnum);
	
	style.setFillForegroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);;
	
	cell.setCellStyle(style);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();
}
}
