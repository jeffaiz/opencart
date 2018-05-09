package myTestCases;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_03 {
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
	 public void click_on_homepage()throws InterruptedException, IOException {
		driver.findElement(By.className("links")).findElement(By.linkText("Home")).click();
		driver.findElement(By.className("nivo-imageLink")).click();
		
		WebDriverWait image1=new WebDriverWait(driver, 5000);
		image1.until(ExpectedConditions.visibilityOfElementLocated(By.id("image"))).click();
		
		WebDriverWait parse=new WebDriverWait(driver, 5000);
		String p_text=parse.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxCurrent"))).getText();
		    
		 //Validation[Parse total Count]
		    FileWriter fl=new FileWriter(new File("D:/Automation Test//TC_03_Modal_Window_Count.txt"));
			BufferedWriter bw=new BufferedWriter(fl);
			char t=p_text.charAt(11);
			int x=Character.getNumericValue(t);
			bw.write(p_text.charAt(11));
			bw.close();
		//Ends	
			
		//Validation	
		for(int i=1;i<x;i++)
			{
			WebDriverWait next=new WebDriverWait(driver, 5000);
			next.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxNext"))).click(); 	
			}
		//Ends
		
		WebDriverWait close=new WebDriverWait(driver, 5000);
	    close.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxClose"))).click();
		
	    WebDriverWait button_cart=new WebDriverWait(driver, 5000);
	    button_cart.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
		 
	    WebDriverWait ribbon=new WebDriverWait(driver, 5000);
	    ribbon.until(ExpectedConditions.visibilityOfElementLocated(By.className("success"))).findElement(By.linkText("shopping cart")).click();
		
	    List<WebElement> estimatedPrice_radio=driver.findElements(By.className("highlight"));
	    estimatedPrice_radio.get(2).click();
	    
	    WebDriverWait get_quotes=new WebDriverWait(driver, 5000);
		get_quotes.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-quote"))).click();
		
		WebDriverWait radio_popup=new WebDriverWait(driver, 5000);
		radio_popup.until(ExpectedConditions.visibilityOfElementLocated(By.id("flat.flat"))).click();
		
		WebDriverWait submit=new WebDriverWait(driver, 5000);
		submit.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping"))).click();
		
		List<WebElement> total=driver.findElements(By.className("total"));
		String total_amnt=total.get(2).getText();
		
		//Validation
		FileWriter fl1=new FileWriter(new File("D:/Automation Test//TC_03_Total_Amount.txt"));
		BufferedWriter bw1=new BufferedWriter(fl1);
		bw1.write(total_amnt);
		bw1.close();
		//Ends
		
		List<WebElement> coupan_radio=driver.findElements(By.className("highlight"));
		coupan_radio.get(0).click();
		
        driver.findElement(By.name("coupon")).sendKeys("1234");
		
        List<WebElement> apply=driver.findElements(By.className("button"));
		apply.get(0).click();
		
		//Validation
		String warning=driver.findElement(By.className("warning")).getText();
		FileWriter fl2=new FileWriter(new File("D:/Automation Test//TC_03_Warning.txt"));
		BufferedWriter bw2=new BufferedWriter(fl2);
		bw2.write(warning);
		bw2.close();
		//Ends
		
		WebElement checkout=driver.findElement(By.className("buttons"));
		checkout.findElement(By.className("right")).click();
		
		WebDriverWait billing_new=new WebDriverWait(driver, 5000);
		billing_new.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-address-new"))).click();
		
		
		File file=new File("BillingDetails.xls");
		FileInputStream fs=new FileInputStream(file);
		@SuppressWarnings("resource")
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sh= wb.getSheetAt(0);	 
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
		
		
		WebDriverWait billing=new WebDriverWait(driver, 5000);
		billing.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-address"))).click();
		
		/*List<WebElement> delievry_add=driver.findElements(By.name("address_id"));
		//delievry_add.iterator();
		delievry_add.get(1).findElement(By.xpath("//option[8]")).click();
        */
		//SELECT NEW ADDRESS
		WebDriverWait delivery=new WebDriverWait(driver, 5000);
		delivery.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address"))).click();
		  
		
	//	WebDriverWait terms_cond=new WebDriverWait(driver, 5000);
	//	terms_cond.until(ExpectedConditions.visibilityOfElementLocated(By.className("colorbox cboxElement"))).click();
		 
		//driver.findElement(By.className("colorbox cboxElement")).findElement(By.tagName("a")).click();
	
		
	    //driver.findElement(By.id("cboxContent")).getSize();
	   // System.out.println(driver.findElement(By.id("cboxContent")).getSize());
		 
	    driver.findElements(By.name("comment")).get(0).sendKeys("MAKEMYTRIP");
	    
		WebDriverWait delivery1=new WebDriverWait(driver, 5000);
	    delivery1.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method"))).click();
	   
	    Thread.sleep(5000);

		driver.findElement(By.linkText("Terms & Conditions")).click();
		
		WebDriverWait close1=new WebDriverWait(driver, 5000);
	    close1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxClose"))).click();
	    
	    WebDriverWait agree=new WebDriverWait(driver, 5000);
	    agree.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree"))).click();
	    
	    WebDriverWait payment=new WebDriverWait(driver, 5000);
	    payment.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-method"))).click();
	    
	    FileWriter fl3=new FileWriter(new File("D:/Automation Test//TC_03_Price&Total"));
		BufferedWriter bw3=new BufferedWriter(fl3);
		
		
	//bw3.write("Price : " + driver.findElements(By.className("price")).get(1).getText());
	//bw3.write("Total : " + driver.findElements(By.className("total")).get(5).getText());
	//bw3.close();
		driver.findElements(By.tagName("a")).get(54).click();	
		driver.findElements(By.name("address_id")).get(0).click();
		
		WebDriverWait billing1=new WebDriverWait(driver, 5000);
		billing1.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-address"))).click();
	
		WebDriverWait delivery2=new WebDriverWait(driver, 5000);
		delivery2.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address"))).click();
		
		WebDriverWait delivery3=new WebDriverWait(driver, 5000);
	    delivery3.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method"))).click();
	    
	    WebDriverWait agree1=new WebDriverWait(driver, 5000);
	    agree1.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree"))).click();
	   
	    WebDriverWait payment1=new WebDriverWait(driver, 5000);
	    payment1.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-method"))).click();

	    WebDriverWait confirm=new WebDriverWait(driver, 5000);
	    confirm.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm"))).click();
	    
	    
		}
	 
	@Test(priority=3)
	public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	  
	 
	 
	 
	 
}
