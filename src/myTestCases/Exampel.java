package myTestCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exampel {
	WebDriver driver=new FirefoxDriver();
	
	
	  @Test
	  public void startup() {
		  
		  driver.get("http://www.google.com");
		  driver.findElement(By.id("lst-ib")).sendKeys("Apple");
		  driver.findElement(By.name("btnK")).click();
	  }
		
		
	
	
	}
	


