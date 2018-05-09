package myTestCases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC03 {
	
	static WebDriver driver;

	
	
	
	
	 
	@BeforeClass

	    public static void setup(){
		
		        ProfilesIni prof = new ProfilesIni();				
				FirefoxProfile ffProfile= prof.getProfile ("NewProfile");
				ffProfile.setAcceptUntrustedCertificates(true) ;
				ffProfile.setAssumeUntrustedCertificateIssuer(false);

                  driver = new FirefoxDriver (ffProfile);


	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        driver.get("http://10.207.182.108:81/opencart");

	    }
	
	  @Test
	  public void Test_Case_03() throws IOException, InterruptedException
	  {
		  
		     
		    File file=new File("BillingDetails.xls");
			FileInputStream fs=new FileInputStream(file);
			@SuppressWarnings("resource")
			HSSFWorkbook wb=new HSSFWorkbook(fs);
			HSSFSheet sh= wb.getSheetAt(0);	  
		  
	  CommonLib obj= new CommonLib(driver);
	  obj.login("jeffaiz72@gmail.com", "faiz_faiz54");
	  
	  driver.findElement(By.className("breadcrumb")).findElement(By.xpath("//a[contains(@href,'home')]")).click();
      
	//  driver.findElement(By.className("links")).findElement(By.xpath("//a[contains(@href,'home')]")).click();
	       
	  driver.findElement(By.className("nivo-imageLink")).click();
	  

		WebDriverWait image1=new WebDriverWait(driver, 5000);
		image1.until(ExpectedConditions.visibilityOfElementLocated(By.id("image"))).click();
		  

		WebDriverWait parse=new WebDriverWait(driver, 5000);
	    String p_text=parse.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxCurrent"))).getText();
	    	//Parse into file
	    
		FileWriter fl=new FileWriter(new File("D:/Automation Test//Parse.txt"));
		BufferedWriter bw=new BufferedWriter(fl);
		
		for(int i=0;i<p_text.length();i++)
		{
			
		}
		bw.write(p_text.charAt(11));
		bw.close();
		
		for(int i=0;i<6;i++)
		{
		WebDriverWait next=new WebDriverWait(driver, 5000);
		next.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxNext"))).click(); 	
		}
		
		
		WebDriverWait close=new WebDriverWait(driver, 5000);
	    close.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxClose"))).click();
		
	    Thread.sleep(5000);
	    
	    WebDriverWait button_cart=new WebDriverWait(driver, 5000);
	    button_cart.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
		 
	    WebDriverWait ribbon=new WebDriverWait(driver, 5000);
	    ribbon.until(ExpectedConditions.visibilityOfElementLocated(By.className("success"))).findElement(By.linkText("shopping cart")).click();
		
		
		//3 Radio Buttons 
		List<WebElement> radio3=driver.findElements(By.className("highlight"));
		radio3.get(2).click();
		
		WebDriverWait get_quotes=new WebDriverWait(driver, 5000);
		get_quotes.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-quote"))).click();
		
		
		WebDriverWait radio_popup=new WebDriverWait(driver, 5000);
		radio_popup.until(ExpectedConditions.visibilityOfElementLocated(By.id("flat.flat"))).click();
		
		WebDriverWait submit=new WebDriverWait(driver, 5000);
		submit.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping"))).click();
		
		//Calculate total and write into file
		List<WebElement> total=driver.findElements(By.className("total"));
		String total_amnt=total.get(2).getText();
		
		FileWriter fl1=new FileWriter(new File("D:/Automation Test//Total.txt"));
		BufferedWriter bw1=new BufferedWriter(fl1);
		
		bw1.write(total_amnt);
		bw1.close();
		
		driver.findElement(By.className("close")).click();
		
		
		
		List<WebElement> radio1=driver.findElements(By.className("highlight"));
		radio1.get(0).click();
		
		Thread.sleep(5000);
		
		
		//coupon_code.until(ExpectedConditions.visibilityOfElementLocated(By.name("coupan"))).sendKeys("1234");
		driver.findElement(By.name("coupon")).sendKeys("1234");
		
		List<WebElement> apply=driver.findElements(By.className("button"));
		apply.get(0).click();
		
		String warning=driver.findElement(By.className("warning")).getText();
		//Warning message into File 
		FileWriter fl2=new FileWriter(new File("D:/Automation Test//Warning.txt"));
		BufferedWriter bw2=new BufferedWriter(fl2);
		
		bw2.write(warning);
		bw2.close();
		
//checkout
		WebElement checkout=driver.findElement(By.className("buttons"));
		checkout.findElement(By.className("right")).click();
		
		WebDriverWait radio_new=new WebDriverWait(driver, 5000);
		radio_new.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-address-new"))).click();
		
		//driver.findElement(By.id("payment-address-new")).click();
		System.out.println("Before");
		
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			DataFormatter formatter = new DataFormatter();
			System.out.println("run");
			WebElement f_name=driver.findElement(By.name("firstname"));
			f_name.sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
			
			WebElement l_name=driver.findElement(By.name("lastname"));
			l_name.sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
			
			
			WebElement comp=driver.findElement(By.name("company"));
			comp.sendKeys(sh.getRow(i).getCell(2).getStringCellValue());
			
			WebElement comp_id=driver.findElement(By.name("company_id"));
			comp_id.sendKeys(formatter.formatCellValue(sh.getRow(i).getCell(3)));
			
			WebElement add=driver.findElement(By.name("address_1"));
			add.sendKeys(sh.getRow(i).getCell(4).getStringCellValue());
			
			WebElement add_1=driver.findElement(By.name("address_2"));
			add_1.sendKeys(sh.getRow(i).getCell(5).getStringCellValue());
			
			WebElement city=driver.findElement(By.name("city"));
			city.sendKeys(sh.getRow(i).getCell(6).getStringCellValue());
			
			
			
			WebElement postcode=driver.findElement(By.name("postcode"));
			postcode.sendKeys(formatter.formatCellValue(sh.getRow(i).getCell(7)));
			
			Select country=new Select(driver.findElement(By.name("country_id")));
			country.selectByVisibleText(sh.getRow(i).getCell(8).getStringCellValue());
			
			Select state=new Select(driver.findElement(By.name("zone_id")));
			state.selectByVisibleText(sh.getRow(i).getCell(9).getStringCellValue());
		
		}
	
		
		WebDriverWait continue_address=new WebDriverWait(driver, 5000);
		continue_address.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-address"))).click();
		
		
		List<WebElement> delievry_add=(List<WebElement>) driver.findElement(By.name("address_id"));
		//delievry_add.iterator();
		List<WebElement> w=(List<WebElement>) delievry_add.get(1);
		w.get(11).click();
        
		 WebDriverWait ship_add=new WebDriverWait(driver, 5000);
		 ship_add.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address"))).click();
		  
		 driver.findElement(By.name("comment")).sendKeys("No satisfaction");
		 
		 WebDriverWait ship_method=new WebDriverWait(driver, 5000);
		 ship_method.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method"))).click();

		 
		 WebDriverWait checkbox=new WebDriverWait(driver, 5000);
		 checkbox.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree"))).click();
		 
		 
	     
	
	  
	  }
	 
	 

}
