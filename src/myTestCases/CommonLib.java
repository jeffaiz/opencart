package myTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import junit.framework.Assert;

public class CommonLib {
	
	
	WebDriver driver;
	String baseUrl="http://10.207.182.108:81/opencart/";
	
	CommonLib(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	@SuppressWarnings("deprecation")
	public void login(String username, String password)
	{
		try{
			
	   //driver.get(baseUrl);
	   
	    WebElement link=driver.findElement(By.xpath(".//*[@id='welcome']/a[contains(@href,'login')]"));
	    link.click();
		
		WebElement userName=driver.findElement(By.name("email"));
		userName.sendKeys(username);
		
		WebElement passWord=driver.findElement(By.name("password"));
		passWord.sendKeys(password);

		List<WebElement> button=driver.findElements(By.className("button"));
		button.get(1).sendKeys(Keys.ENTER);
		

		driver.manage().window().maximize();
		
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
/*		WebDriverWait w=new WebDriverWait(driver, 10);
	    String user_text= w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='welcome']/a[1]"))).getText();
	    //Verify Login
	    Assert.assertEquals("Not Matched",user_text, "Faisal");
*/
	}
	
	public void add_to_cart() throws InterruptedException{
		Thread.sleep(5000);
		WebElement cart=driver.findElement(By.id("button-cart"));
		cart.click();
	}


	public void checkout() throws InterruptedException{

		  List<WebElement> buttons= driver.findElements(By.className("button"));
		  buttons.get(4).click();
		  
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
	
	public void logout(){
		
		  WebDriverWait logout=new WebDriverWait(driver, 5000);
		  logout.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();
		  
		  AssertJUnit.assertEquals("Logout Failed",driver.getTitle(), "Account Logout");
			
		  
	}

	
	

}
