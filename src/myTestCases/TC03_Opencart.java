package myTestCases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class TC03_Opencart {
	
	String baseUrl="http://10.207.182.108:81/opencart/";
	@Test
	public void test3() throws IOException
	{
		
	FirefoxDriver driver=new FirefoxDriver();
	
	  driver.get(baseUrl);	
	
	   WebElement link=driver.findElement(By.xpath(".//*[@id='welcome']/a[contains(@href,'login')]"));
	   link.click();
		
		WebElement userName=driver.findElement(By.name("email"));
		userName.sendKeys("jeffaiz72@gmail.com");
		
		WebElement passWord=driver.findElement(By.name("password"));
		passWord.sendKeys("faiz_faiz54");

		WebElement sigNin=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]"));
		sigNin.click();
		
		driver.manage().window().maximize();
		
		WebDriverWait w=new WebDriverWait(driver, 10);
	    String user_text= w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='welcome']/a[1]"))).getText();
	    
	    Assert.assertEquals("Not Matched",user_text, "Faisal");

	
		//Click on Home Tab
		//driver.get("http://10.207.182.108:81/opencart/index.php?route=common/home");
		
		driver.findElement(By.className("breadcrumb")).findElement(By.xpath("//a[contains(@href,'home')]")).click();
       
		driver.findElement(By.className("links")).findElement(By.xpath("//a[contains(@href,'home')]")).click();
	       
		driver.findElement(By.className("nivo-imageLink")).click();
		
		WebDriverWait image1=new WebDriverWait(driver, 5000);
		image1.until(ExpectedConditions.visibilityOfElementLocated(By.id("image"))).click();
		  

		WebDriverWait parse=new WebDriverWait(driver, 5000);
	    String p_text=parse.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxCurrent"))).getText();
		  //Parse into file
		FileWriter fl=new FileWriter(new File("D:/Automation Test//Parse.txt"));
		BufferedWriter bw=new BufferedWriter(fl);
		
		bw.write(p_text);
		bw.close();
		
		for(int i=0;i<6;i++)
		{
			WebDriverWait image=new WebDriverWait(driver, 5000);
		    image.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxNext"))).click();
			  
			
		}
		
		WebDriverWait image=new WebDriverWait(driver, 5000);
	    image.until(ExpectedConditions.visibilityOfElementLocated(By.id("cboxClose"))).click();
		 
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
		
		List<WebElement> radio1=driver.findElements(By.className("highlight"));
		radio1.get(0).click();
		
		WebDriverWait coupon_code=new WebDriverWait(driver, 5000);
		coupon_code.until(ExpectedConditions.visibilityOfElementLocated(By.name("coupan"))).sendKeys("1234");
		
		String warning=driver.findElement(By.className("warning")).getText();
		//Warning message into File 
		FileWriter fl2=new FileWriter(new File("D:/Automation Test//Warning.txt"));
		BufferedWriter bw2=new BufferedWriter(fl2);
		
		bw2.write(warning);
		bw2.close();
		
//checkout
		WebElement checkout=driver.findElement(By.className("buttons"));
		checkout.findElement(By.className("right")).click();
		
		
		
		
		
		
		
		
		
		
		
		
 }
}



