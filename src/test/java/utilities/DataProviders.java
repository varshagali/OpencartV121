package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProviders
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		String path=".\\testData\\Opencart_LoginData.xlsx";  //taking xl file from database  (.) reprasentting currect 
		
		ExcelUtility xlutil=new ExcelUtility(path);  //creating an object for xlUtility	
		
		int totalrows=xlutil.getRowcount("Sheet1");
		int totalcols=xlutil.getCellcount("Sheet2", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //created for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[1-1][j]=xlutil.getCellData("Sheet1",i,j);  //1,0
			}
		}
		return logindata; // returning two dimension array
		
	}

}
