package myTestCases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_08 {

	
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
	  public void find_all() 
	 {

		 List<WebElement> links=driver.findElements(By.tagName("a"));
		 System.out.println(links.size());	
	
		 for (int i = 0; i<links.size(); i++)	
			{
				System.out.println(links.get(i).getText());	 
			}	 
	 }
	 
	 @Test(priority=5)
	  public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	
}

