package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import commonFunctions.AddSupplierPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest1 extends AppUtil{
	String inputpath="./FileInput/Supplier.xlsx";
	String outputpath="./FileOutput/SupplierDatareslut.xlsx";
	String TestData ="AddSupplier";
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	public void startTest() throws Throwable{
		report = new ExtentReports("./target/Reports/Supplier.html");
		//call add customer page class
		AddSupplierPage Supplier=PageFactory.initElements(driver,AddSupplierPage.class);
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowcount(TestData);
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++) {
		logger=report.startTest("AddSupplier");
		String Suppliername=xl.getCellData(TestData, i, 0);
		String Address=xl.getCellData(TestData, i, 1);
		String City=xl.getCellData(TestData, i, 2);
		String Country=xl.getCellData(TestData, i, 3);
		String ContactPerson=xl.getCellData(TestData, i, 4);
		String phoneNumber=xl.getCellData(TestData, i, 5);
		String Email =xl.getCellData(TestData, i, 6);
		String MobileNumber =xl.getCellData(TestData, i, 7);
		String Notes =xl.getCellData(TestData, i, 8);
		boolean res = Supplier.addSuplier(Suppliername, Address, City, Country, ContactPerson, phoneNumber, Email, MobileNumber, Notes);
				
		if (res) {
			//if results is true written as a pass into status cell in test data sheet
			xl.setcelldata(TestData, i, 9, "pass", outputpath);
			logger.log(LogStatus.PASS, "Add Supplier is succes");
		}else {
			//if results is fail written as a fail into status cell in test data sheet
			logger.log(LogStatus.FAIL, "Add Supplier is failed");
		}
		report.endTest(logger);
		report.flush();
	}
	
	}


}
