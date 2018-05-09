package myTestCases;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class TC_06 {
	WebDriver driver=new FirefoxDriver();
	
	
	@BeforeClass
	  public void startup() {
		  
		  driver = new FirefoxDriver();
		  
	      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	      driver.get("http://10.207.182.108:81/opencart/");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	
	 @Test(priority=1)
	  public void login() throws InterruptedException, IOException {
		
		 CommonLib obj= new CommonLib(driver);
		obj.login("jeffaiz72@gmail.com", "faiz_faiz54");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		   
	  }
	 
	 @Test(priority=2)
	 public void search_and_edit() throws InterruptedException, IOException{
	
		 WebElement search=driver.findElement(By.name("search"));
		 search.sendKeys("canon");
		 search.sendKeys(Keys.ENTER);

		 Thread.sleep(3000);
		 /*
		 //WebDriverWait can=new WebDriverWait(driver, 5000);
		 List<WebElement> l=driver.findElements(By.className("image"));
		 l.get(0).findElement(By.tagName("a"));
		 //can.until(ExpectedConditions.visibilityOfElementLocated(By.className("image"))).findElement(By.tagName("a")).click();
		)*/
		 
		 WebElement link=driver.findElement(By.xpath(".//*[@class='name']/a[contains(@href,'search=canon')]"));
		 link.click();
		 
		    File file1=new File("Quantity.xls");
			FileInputStream fs1=new FileInputStream(file1);
			@SuppressWarnings("resource")
			HSSFWorkbook wb1=new HSSFWorkbook(fs1);
			HSSFSheet sh1= wb1.getSheetAt(0);	   
		  
			for(int j=1;j<=sh1.getLastRowNum();j++)
			{
				DataFormatter formatter1 = new DataFormatter();
				WebElement comp_id=driver.findElement(By.name("quantity"));
				comp_id.clear();
				comp_id.sendKeys(formatter1.formatCellValue(sh1.getRow(j).getCell(0)));
			}
		 
		 WebDriverWait cart=new WebDriverWait(driver, 5000);
		 cart.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
		 
		 WebElement shopping_cart=driver.findElement(By.className("success"));
		  shopping_cart.findElement(By.linkText("shopping cart")).click();
		  
		  List<WebElement> total=driver.findElements(By.className("total"));
		  
		  String x=total.get(2).getText();
		  String y=x.replaceAll("[^\\d.]+", "");
		  List <WebElement> continue_shop=driver.findElements(By.className("button"));
		  double check=Double.parseDouble(y);
		  double sum=0;
		  sum=sum+check;
		  if(sum>200)
		  {
			  FileWriter fl=new FileWriter(new File("D:/Automation Test//Total.txt"));
			  BufferedWriter bw=new BufferedWriter(fl);
			
			  bw.write(total.get(2).getText());
			  bw.close();
			  CommonLib obj1= new CommonLib(driver);
			  obj1.logout();
	
		  }
		  else{
			  
			  continue_shop.get(5).click();
			  
		  }
		  
		 
	 }
}
