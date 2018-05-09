package myTestCases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_01 {
 
	
	WebDriver driver=new FirefoxDriver();

	  @BeforeClass
	  public void startup() {
		  
		  driver = new FirefoxDriver();
		  
	      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	      driver.get("http://10.207.182.108:81/opencart/");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	
	@Test(enabled=false)
	public void create_account()
	
	{	
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException, IOException {
	    CommonLib obj= new CommonLib(driver);
		obj.login("jeffaiz72@gmail.com", "faiz_faiz54");
		driver.manage().window().maximize();
		Thread.sleep(5000);		
		}
	//@Test(enabled=false)
	 @Test(priority=2)
	 public void click_on_Homepage() throws IOException, InterruptedException{
		 
		 //should be replaced
		 driver.findElement(By.className("breadcrumb")).findElement(By.xpath(".//a[contains(@href,'home')]")).click();
		 
		 
		  WebDriverWait homeImage=new WebDriverWait(driver, 5000);
		  homeImage.until(ExpectedConditions.visibilityOfElementLocated(By.className("nivo-imageLink"))).click();
		  
		
		  driver.findElement(By.className("htabs")).findElement(By.xpath(".//a[contains(@href,'review')]")).click();
		  
		  //Data Driven File
		  File f=new File("ReviewProduct.xls");
		  FileInputStream fs=new FileInputStream(f);
		  HSSFWorkbook wb=new HSSFWorkbook(fs);
		  HSSFSheet sh= wb.getSheetAt(0);
			
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
					WebElement name=driver.findElement(By.name("name"));
					name.sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
					WebElement review=driver.findElement(By.name("text"));
					review.sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
					//driver.findElementByName("text").sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
					DataFormatter formatter = new DataFormatter();
					String x=formatter.formatCellValue(sh.getRow(i).getCell(2));
					//sh.getRow(i).getCell(2).getStringCellValue();
					int num=Integer.parseInt(x);
					System.out.println(num);
					
					switch(num)
					{
					case 4:
						String path=".//*[@id='tab-review']/input[4]";
						driver.findElement(By.xpath(path)).click();		
					case 5:
						String path1=".//*[@id='tab-review']/input[5]";
						driver.findElement(By.xpath(path1)).click();
					case 6:
						String path3=".//*[@id='tab-review']/input[6]";
						driver.findElement(By.xpath(path3)).click();
					
					}
					String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
					WebElement c=driver.findElement(By.name("captcha"));
					c.sendKeys(captchaVal);
					driver.findElement(By.id("button-review")).click();
					
										//Screenshot of Error
					
					TakesScreenshot ts=(TakesScreenshot)driver;
					File source=ts.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(source, new File("./Screenshots/"+"screenshot"+".png"));
					System.out.println("Screenshot taken");
					name.clear();
					review.clear();
					c.clear();
					
				List<WebElement> links=driver.findElements(By.className("links"));
				links.get(1).findElement(By.linkText("Add to Wish List")).click();
				
		        driver.findElement(By.className("close")).click();		   
		        Thread.sleep(5000);
	 }
			
						
 }
	
	 @Test(priority=3)
	 public void click_on_Wishlist() throws InterruptedException, IOException{
		 
		
		 // Add price to external file
		    WebDriverWait wishlink = new WebDriverWait(driver, 10);
		    wishlink.until(ExpectedConditions.visibilityOfElementLocated(By.id("wishlist-total"))).click();
		    
		    
			FileWriter fr=new FileWriter("D:/Automation Test//Currency.txt");
		    BufferedWriter br=new BufferedWriter(fr);
		    
		    WebDriverWait Euro= new WebDriverWait(driver, 10);
		    Euro.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='currency']/a[1]"))).click();
			
			List<WebElement> currency=driver.findElements(By.className("price"));
	        String euro=currency.get(2).getText();
			br.write(euro);
			br.newLine();
			
			WebDriverWait Pound= new WebDriverWait(driver, 10);
			Pound.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='currency']/a[2]"))).click();
			
			List<WebElement> currency1=driver.findElements(By.className("price"));
			String pound=currency1.get(2).getText();
			
			br.write(pound);
			br.newLine();
		
			WebDriverWait Usd= new WebDriverWait(driver, 10);
			Usd.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='currency']/a[3]"))).click();
			
			List<WebElement> currency2=driver.findElements(By.className("price"));
			String usd=currency2.get(2).getText();
			br.write(usd);
			br.newLine();
			br.close();
			
			
			driver.findElement(By.className("action")).findElement(By.xpath("//a[1])")).click();
			
	 }
	 
	 @Test(priority=4)
	  public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	 
}
