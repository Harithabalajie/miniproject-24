
package miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dataproperties {
	public static String fromlocation;
	public static String tolocation;
	static XSSFCell orgin;
	static XSSFCell destination;
public static void dataExtract()
{
	//excel data
	try {
		
		File excelFile = new File("C:\\Users\\2318420\\eclipse-workspace\\Selenium\\testdata.xlsx");
	
		FileInputStream fis = new FileInputStream(excelFile);
	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheetAt(0);
	
		XSSFRow r1 = sheet.getRow(1);	
        orgin = r1.getCell(0);
        destination = r1.getCell(1);
        fromlocation=orgin.getStringCellValue();
        tolocation=destination.getStringCellValue();

	}

	catch(Exception e)
	{
		System.out.println(e);
	}

}

}

