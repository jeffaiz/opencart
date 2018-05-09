package myTestCases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_07 {
  
	
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
	 public void menu(){
		 
		 WebElement menu=driver.findElement(By.id("menu"));
		 menu.findElement(By.linkText("Phones & PDAs")).click();
		 
		 List <WebElement> list=driver.findElements(By.className("button"));
		 list.get(0).click();
		 list.get(1).click();
		 list.get(2).click();
		 
		 WebElement shopping_cart=driver.findElement(By.className("success"));
		 shopping_cart.findElement(By.linkText("shopping cart")).click();
		 
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
	  
	  @Test(priority=5)
	  public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	 
}
