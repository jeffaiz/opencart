package myTestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TC_04 {
	
	public WebDriver driver;
	
	
	
	@BeforeClass
	  public void startup() {
		  
		  driver = new FirefoxDriver();
		  
	      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	      driver.get("http://10.207.182.108:81/opencart/");
	      
	     // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
  @Test(priority=1)
  public void login() throws InterruptedException {
	  
	  CommonLib obj= new CommonLib(driver);
	  obj.login("jeffaiz72@gmail.com", "faiz_faiz54");
	  Thread.sleep(5000);
	  WebDriverWait w=new WebDriverWait(driver, 10);
	  String user_text= w.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome"))).findElement(By.xpath("//a[contains(@href,'account')]")).getText();
	//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='welcome']/a[1]"))).getText();
	  Assert.assertEquals(user_text, "Faisal");
	  
	   
  }
  
  @Test(priority=2)
  public void click_on_Homepage()
  {
	  driver.manage().window().maximize();
	  WebDriverWait wb=new WebDriverWait(driver,5);
	  wb.until(ExpectedConditions.visibilityOfElementLocated(By.className("breadcrumb"))).findElement(By.xpath("//a[contains(@href,'home')]")).click();
	  
  }
  //@Test(enabled = false)
  @Test(priority=3)
  public void click_on_fetaured() throws InterruptedException{
	 
	 Thread.sleep(5000);
	 driver.manage().window().maximize();
	 List<WebElement> list= driver.findElements(By.className("image"));
	 list.get(3).click();
	 
	 driver.findElement(By.xpath(".//*[@id='tabs']/a[contains(@href,'#tab-related')]")).click();
	 List<WebElement> buttons= driver.findElements(By.className("button"));
	 buttons.get(3).click();
	 
	 List<WebElement> links= driver.findElements(By.className("links"));
	 links.get(0).findElement(By.linkText("Shopping Cart")).click();
	 
	 driver.findElement(By.name("quantity[40::]")).clear();
	  
	 driver.findElement(By.name("quantity[40::]")).sendKeys("3");
	 
	 List<WebElement> checkout= driver.findElements(By.className("button"));
	 checkout.get(4).click();
	  
	  WebDriverWait continue2=new WebDriverWait(driver, 5000);
	  continue2.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-address"))).click();
	  
	  WebDriverWait continue3=new WebDriverWait(driver, 5000);
	  continue3.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address"))).click();
	  
	  
	  WebDriverWait continue4=new WebDriverWait(driver, 5000);
	  continue4.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-method"))).click();
	  
	  WebDriverWait checkbox=new WebDriverWait(driver, 5000);
	  checkbox.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree"))).click();
	  
	  WebDriverWait payment=new WebDriverWait(driver, 5000);
	  payment.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-method"))).click();
	 
	  WebDriverWait confirm=new WebDriverWait(driver, 5000);
	  confirm.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm"))).click();
	 
	  
  }
  @Test(enabled = false)
  //@Test(priority=4)
  public void add_cart() throws InterruptedException{
	  CommonLib obj= new CommonLib(driver);
	  obj.add_to_cart();
	  
  }
  @Test(enabled = false)
 //@Test(priority=4)
  public void shopping_cart_common_lib() throws InterruptedException
  {

	  CommonLib obj= new CommonLib(driver);
	  obj.checkout();
  }
  
  @Test(priority=5)
  public void footer_orderHistory() throws InterruptedException
  {
		
		//Customer service_Footer
		List<WebElement> contact_us=driver.findElements(By.className("column"));
		contact_us.get(3).findElement(By.linkText("Order History")).click();
		
		Thread.sleep(5000);
		
		
		WebDriverWait wb1=new WebDriverWait(driver,5);
		wb1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[2]/div[3]/div[3]/a[1]/img"))).click();
		
		List<WebElement> right=driver.findElements(By.className("right"));
		right.get(12).click();
		
		 WebDriverWait wb2=new WebDriverWait(driver,5);
		 wb2.until(ExpectedConditions.visibilityOfElementLocated(By.name("return_reason_id"))).click();
		 
		 String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
	     driver.findElement(By.name("captcha")).sendKeys(captchaVal);
	     
	     List<WebElement> button= driver.findElements(By.className("button"));
		 button.get(1).click();
		 
		 WebDriverWait wb4=new WebDriverWait(driver,5);
		 wb4.until(ExpectedConditions.visibilityOfElementLocated(By.className("button"))).click();
  
		 driver.findElement(By.id("welcome")).findElement(By.linkText("Logout")).click();
  }
  
/*
  @AfterClass
  public void end(){
	  
	  driver.quit();
  }
 */
  
  

}
