package myTestCases;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_05 {
	
	public WebDriver driver;
	
	
	@BeforeClass
	  public void startup() {
		  
		  driver = new FirefoxDriver();
		  
	      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	      driver.get("http://10.207.182.108:81/opencart/");
		  

	      
	     // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	//@Test(enabled = false)
	 @Test(priority=1)
	  public void login() throws InterruptedException, IOException {
		CommonLib obj= new CommonLib(driver);
		 obj.login("jeffaiz72@gmail.com", "faiz_faiz54");
		  //Thread.sleep(5000);
		  driver.manage().window().maximize();
		  WebDriverWait w=new WebDriverWait(driver, 10);
		 // String user_text= w.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome"))).findElement(By.xpath("//a[contains(@href,'account')]")).getText();
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='welcome']/a[1]"))).getText();
		  //Assert.assertEquals(user_text, "Faisal");
	  }
	  
	  //@Test(enabled = false)
	  @Test(priority=2)
		 
	  public void footer_extras() throws IOException, InterruptedException
	  {
		
		 
		  
		  Thread.sleep(5000);
		  
		   List<WebElement> extras=driver.findElements(By.className("column"));
		   extras.get(2).findElement(By.linkText("Gift Vouchers")).click();
		   

		    File file=new File("Gift_Data.xls");
			FileInputStream fs=new FileInputStream(file);
			@SuppressWarnings("resource")
			HSSFWorkbook wb=new HSSFWorkbook(fs);
			HSSFSheet sh= wb.getSheetAt(0);	 
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				//DataFormatter formatter = new DataFormatter();
				
				WebElement f_name=driver.findElement(By.name("to_name"));
				f_name.sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
				
				WebElement l_name=driver.findElement(By.name("to_email"));
				l_name.sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
			}
			
			
			driver.findElement(By.id("voucher-7")).click();
			driver.findElement(By.name("agree")).click();
			
			List<WebElement> continue_button=driver.findElements(By.className("button"));
			continue_button.get(0).click();
			
			driver.findElement(By.className("button")).click();
			
			//List<WebElement> quant=driver.findElements(By.className("quantity"));
			//quant.get(2).findElement(By.tagName("a")).click();
			
			//driver.findElement(By.xpath(".//*[@id='content']/form/div/table/tbody/tr[1]/td[4]/a/img")).click();
			
			List<WebElement> continue_shop=driver.findElements(By.className("button"));
			continue_shop.get(5).click();
			
	  }
	  
	  //@Test(enabled = false)
	  @Test(priority=3)
		  

	  public void footer_customerService() throws InterruptedException
	  {
			
		     Thread.sleep(5000);
			//Customer service_Footer
			List<WebElement> contact_us=driver.findElements(By.className("column"));
			contact_us.get(1).findElement(By.linkText("Contact Us")).click();
			   
			
			driver.findElement(By.name("enquiry")).sendKeys("This is to Change of Address/Phone number");
			String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
			driver.findElement(By.name("captcha")).sendKeys(captchaVal);
			
			List<WebElement> button_1=driver.findElements(By.className("right"));
			button_1.get(5).click();
			AssertJUnit.assertEquals(driver.getTitle(), "Contact Us");
			
			driver.findElement(By.className("button")).click();
			
			AssertJUnit.assertEquals(driver.getTitle(), "Your Store");
			
			
	  }
	  
	  //@Test(enabled = false)
	 @Test(priority=4)
	  public void footer_WishList() throws IOException, InterruptedException
	  {
		  Thread.sleep(5000);
		  List<WebElement> contact_us=driver.findElements(By.className("column"));
		  contact_us.get(3).findElement(By.linkText("Wish List")).click();
		  
		  driver.findElement(By.className("button")).click();
		  
		  WebDriverWait continue3=new WebDriverWait(driver, 5000);
		  Assert.assertTrue(continue3.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit your account information"))).isDisplayed(),"Not Displayed");
		  
		  WebDriverWait continue4=new WebDriverWait(driver, 5000);
		  continue4.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit your account information"))).click();	  
		  
		  WebDriverWait telephone=new WebDriverWait(driver, 5000);
		  telephone.until(ExpectedConditions.visibilityOfElementLocated(By.name("telephone"))).clear();
		  
		  File file=new File("Update_no.xls");
		  FileInputStream fs=new FileInputStream(file);
		  @SuppressWarnings("resource")
		  HSSFWorkbook wb=new HSSFWorkbook(fs);
		  HSSFSheet sh= wb.getSheetAt(0);
		 
		  for(int i=1;i<=sh.getLastRowNum();i++)
		  {
			  
			  WebElement telephone1=driver.findElement(By.name("telephone"));
			  DataFormatter formatter = new DataFormatter();
			  telephone1.sendKeys(formatter.formatCellValue(sh.getRow(i).getCell(0)));
			  Assert.assertEquals(formatter.formatCellValue(sh.getRow(i).getCell(0)), "9639050715");
		  }  	  
			  List<WebElement> continue_button=driver.findElements(By.className("button"));
			  continue_button.get(1).click();
			  
			  WebDriverWait verify=new WebDriverWait(driver, 5000);
			  Assert.assertTrue(verify.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View your return requests"))).isDisplayed(),"Not Displayed");
			  
			  WebDriverWait wait=new WebDriverWait(driver, 5000);
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View your return requests"))).click();
			  
			  List<WebElement> view=driver.findElements(By.className("return-info"));
			  view.get(1).findElement(By.tagName("a")).click();
			  
			  List<WebElement> id=driver.findElements(By.className("left"));
			  
			  System.out.println(id.get(2).findElement(By.xpath("b")).getText());
			  
			 WebDriverWait wait2=new WebDriverWait(driver,5);
			 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("button"))).click();
			 
			 WebDriverWait wait3=new WebDriverWait(driver,5);
			 wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("button"))).click();
			 
			 WebDriverWait verify1=new WebDriverWait(driver, 5000);
			 Assert.assertTrue(verify1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Modify your address book entries"))).isDisplayed(),"Not Displayed");
			 
			 WebDriverWait modify=new WebDriverWait(driver, 5000);
			  modify.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Modify your address book entries"))).click();
			  
			  List<WebElement> edit=driver.findElements(By.className("button"));
			  edit.get(0).click(); 
			 
			  System.out.println("Run the Excel File");
  
			    File file1=new File("Address_Book.xls");
				FileInputStream fs1=new FileInputStream(file1);
				@SuppressWarnings("resource")
				HSSFWorkbook wb1=new HSSFWorkbook(fs1);
				HSSFSheet sh1= wb1.getSheetAt(0);	   
			  
				for(int j=1;j<=sh1.getLastRowNum();j++)
				{
					DataFormatter formatter1 = new DataFormatter();
					
					WebElement f_name=driver.findElement(By.name("firstname"));
					f_name.clear();
					f_name.sendKeys(sh1.getRow(j).getCell(0).getStringCellValue());
					
					WebElement l_name=driver.findElement(By.name("lastname"));
					l_name.clear();
					l_name.sendKeys(sh1.getRow(j).getCell(1).getStringCellValue());
					
					
					WebElement comp=driver.findElement(By.name("company"));
					comp.clear();
					comp.sendKeys(sh1.getRow(j).getCell(2).getStringCellValue());
					
					WebElement comp_id=driver.findElement(By.name("company_id"));
					comp_id.clear();
					comp_id.sendKeys(formatter1.formatCellValue(sh1.getRow(j).getCell(3)));
					
					WebElement add=driver.findElement(By.name("address_1"));
					add.clear();
					add.sendKeys(sh1.getRow(j).getCell(4).getStringCellValue());
					
					WebElement add_1=driver.findElement(By.name("address_2"));
					add_1.clear();
					add_1.sendKeys(sh1.getRow(j).getCell(5).getStringCellValue());
					
					WebElement city=driver.findElement(By.name("city"));
					city.clear();
					city.sendKeys(sh1.getRow(j).getCell(6).getStringCellValue());
					
					WebElement postcode=driver.findElement(By.name("postcode"));
					postcode.clear();
					postcode.sendKeys(formatter1.formatCellValue(sh1.getRow(j).getCell(7)));
					
					Select country=new Select(driver.findElement(By.name("country_id")));
					country.selectByVisibleText(sh1.getRow(j).getCell(8).getStringCellValue());
					
					Select state=new Select(driver.findElement(By.name("zone_id")));
					state.selectByVisibleText(sh1.getRow(j).getCell(9).getStringCellValue());
					
					List<WebElement> default_state=driver.findElements(By.name("default"));
					
					default_state.get(0).click();
					
				
					List<WebElement> continue_button_address=driver.findElements(By.className("button"));
					continue_button_address.get(1).click();
					
				
				}
				
			  
			  
		  }
	  
	  @Test(priority=5)
	  public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	  
	
		 
		
		  
		  
		  
	  }
			
			
			
			
		
 
	  
  
