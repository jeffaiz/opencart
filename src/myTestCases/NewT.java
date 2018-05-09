package myTestCases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class NewT {
	
	
	FirefoxDriver driver=new FirefoxDriver();
	private String baseUrl="http://10.207.182.108:81/opencart/";
	
	@Before
	public void setup(){
		
	System.setProperty("webdriver.gecko.driver", "C:Users/fa338116/Downloads/geckodriver-v0.17.0-win64/geckodriver.exe");	
	driver=new FirefoxDriver();
	
	
	}
	@SuppressWarnings("deprecation")
	public void logout() throws InterruptedException{
		WebElement logout=driver.findElement(By.xpath(".//*[@id='welcome']/a[contains(@href,'logout')]"));
		logout.click();
		Thread.sleep(5000);
		//WebElement wb=driver.findElement(By.className("breadcrumb"));
	
		
		Assert.assertEquals("Validation Failed", driver.getTitle(), "Account Logout");
				
		
		
	}
	
	@Test
	public void add_to_wishlist() throws IOException, InterruptedException
	{
		
				driver.get(baseUrl+"index.php?route=account/login/");
				WebDriverWait wait=new WebDriverWait(driver, 5);
				driver.findElementByName("email").sendKeys("jeffaiz72@gmail.com");
				driver.findElementByName("password").sendKeys("faiz_faiz54");
				driver.findElementByXPath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]").click();
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//CLICK ON WISH LIST
			 
				//Thread.sleep(5000);
				
				driver.findElement(By.id("wishlist-total")).click();
				//WebDriverWait wait1 = new WebDriverWait(driver, 10);
				//wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("wishlist-total"))).click();
				//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				//Thread.sleep(5000);
				/*
				FileWriter fr=new FileWriter("D:/Automation Test//data.txt");
			    BufferedWriter br=new BufferedWriter(fr);
				driver.findElement(By.xpath(".//*[@id='currency']/a[1]")).click();
				
		        WebDriverWait ww1 = new WebDriverWait(driver, 10); 
				String euro=ww1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div"))).getText();
				br.write(euro);
				br.newLine();
				
				Thread.sleep(5000);
				driver.findElement(By.xpath(".//*[@id='currency']/a[2]")).click();
				WebDriverWait ww2 = new WebDriverWait(driver, 10); 
				String pound=ww2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div"))).getText();
				br.write(pound);
				br.newLine();
			
				Thread.sleep(5000);
				driver.findElement(By.xpath(".//*[@id='currency']/a[3]")).click();
				WebDriverWait ww3 = new WebDriverWait(driver, 10); 
				String usd=ww3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div"))).getText();
				br.write(usd);
				br.newLine();

				br.close();
		    	*/
				//Add to cart
				//Thread.sleep(5000);
				WebDriverWait w1=new WebDriverWait(driver, 5);
				w1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wishlist-row49']/tr/td[2]/a"))).click();
				
			  //  driver.findElement(By.xpath("//*[@id='wishlist-row49']/tr/td[2]/a")).click();
			    //Thread.sleep(5000);
			    
			    WebDriverWait w2=new WebDriverWait(driver, 5);
			    w2.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
			   //driver.findElement(By.id("button-cart")).click();
				//Thread.sleep(5000);
			
			  // WebDriverWait w3=new WebDriverWait(driver, 5);
			   //w3.until(ExpectedConditions.visibilityOfElementLocated(By.className("close"))).click();
			
			    driver.findElement(By.className("close")).click();
				
			  //  WebDriverWait w4=new WebDriverWait(driver, 5);
			   // w4.until(ExpectedConditions.visibilityOfElementLocated(By.id("wishlist-total"))).click();
				
			    driver.findElement(By.id("wishlist-total")).click();
			    
			    WebDriverWait w5=new WebDriverWait(driver, 5);
			    w5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='wishlist-row49']/tr/td[6]/a/img")));
				//driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[6]/a/img")).click();
				//Thread.sleep(5000);
			    
				driver.findElement(By.className("button")).click();
				//Thread.sleep(5000);
				
				logout();
				
				
			}
			

}
