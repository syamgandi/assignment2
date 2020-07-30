package labs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel 
{
    public static FileInputStream fi;
    public static FileOutputStream fos;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFCell cell;
    public static XSSFRow row;
	public static String exceldata=null;
	public static String value;
	
	public static void readFile(String file)
	{
		try {		
			File f= new File("./DataFiles/"+file+".xlsx");
			fi= new FileInputStream(f);
			wb= new XSSFWorkbook(fi);
			
		} catch (Exception e) 		{
			System.out.println("File not available");
		}	
	}
	
	public static String readData(String fname,String Sheetname,int row,int column) 
	{
		readFile(fname);		
		sh= wb.getSheet(Sheetname);
		cell=sh.getRow(row).getCell(column);		
		String exceldata;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			exceldata="";
		}
		/*wb.close();
		fi.close();*/
		return exceldata;
	}
}
