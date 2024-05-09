package commonFunctions;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;



public class AddSupplierPage {
	WebDriver driver;
	//constructor for invoking web driver methods

	public AddSupplierPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="(//a[@href='a_supplierslist.php'])[2]")
	WebElement ObjClickSupplierlink;
	@FindBy(xpath="(//span[@data-phrase='AddLink'])[1]")
	WebElement objClickAddIcon2;
	@FindBy(xpath="//input[@id='x_Supplier_Number']")
	WebElement objSupplierNumber;
	@FindBy(name="x_Supplier_Name")
	WebElement objSupplierName;
	@FindBy(name="x_Address")
	WebElement objAdders2;
	@FindBy(name="x_City")
	WebElement objCity2;
	@FindBy(name="x_Country")
	WebElement objCountry2;
	@FindBy(name="x_Contact_Person")
	WebElement objContactPerson2;
	@FindBy(name="x_Phone_Number")
	WebElement objPhoneNumber2;
	@FindBy(name="x__Email")
	WebElement objEmail2;
	@FindBy(name="x_Mobile_Number")
	WebElement objMobileNumber2;
	@FindBy(name="x_Notes")
	WebElement objNotes2;
	
	@FindBy(id ="btnAction")
	WebElement ObjAddButton2;
	
	@FindBy(xpath ="//button[normalize-space()='OK!']")
	WebElement ObjClickConfirmOk2;
	
	@FindBy(xpath ="//button[@class='ajs-button btn btn-primary']")
	WebElement ObjClickAlertOk2;
	
	@FindBy(xpath ="//span[@class='glyphicon glyphicon-search ewIcon']")
	WebElement ObjSearchPanel2;
	
	@FindBy(xpath ="//input[@id='psearch']")
	WebElement ObjSearchTextbox2;
	
	@FindBy(xpath ="//button[@id='btnsubmit']")
	WebElement ObjSearchButton2;
	
	@FindBy(xpath ="//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
	WebElement SupplierTable;

	public boolean addSuplier(String Sname,String Address,String city,String Country,String SPerson,String pNumber,String Emial,
			String Mnumber,String Notes) throws Throwable {
		Actions ac = new Actions(driver);
		ac.moveToElement(this.ObjClickSupplierlink).click().perform();
		ac.moveToElement(this.objClickAddIcon2).click().perform();
		String Exp_Data= this.objSupplierNumber.getAttribute("value");
		this.objSupplierName.sendKeys(Sname);
		this.objAdders2.sendKeys(Address);
		this.objCity2.sendKeys(city);
		this.objCountry2.sendKeys(Country);
		this.objContactPerson2.sendKeys(SPerson);
		this.objPhoneNumber2.sendKeys(pNumber);
		this.objEmail2.sendKeys(Emial);
	
		this.objMobileNumber2.sendKeys(Mnumber);
		this.objNotes2.sendKeys(Notes);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		ac.moveToElement(ObjAddButton2).click().perform();
		this.ObjClickConfirmOk2.click();
		Thread.sleep(3000);
		this.ObjClickAlertOk2.click();
		Thread.sleep(3000);
		if(!this.ObjSearchTextbox2.isDisplayed())
			this.ObjSearchPanel2.click();
		this.ObjSearchTextbox2.clear();
		this.ObjSearchTextbox2.sendKeys(Exp_Data);
		this.ObjSearchButton2.click();
		Thread.sleep(2000);
		String Act_Data =this.SupplierTable.getText();
		if(Act_Data.equals(Exp_Data))
		{
			Reporter.log(Act_Data+"       "+Exp_Data+"   "+"Supplier Number Matching",true);
			return true;
		}
		else
		{
			Reporter.log(Act_Data+"       "+Exp_Data+"   "+"Supplier Number is Not Matching",true);
			return false;
		}
	}

}