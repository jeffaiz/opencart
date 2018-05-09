package myTestCases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Run {
	
	static FirefoxDriver driver=new FirefoxDriver();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverWait ws=new WebDriverWait(driver, 5000);
		  List <WebElement> one_element=(List<WebElement>) ws.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));
		  one_element.get(0).click();
		  
		  
		  driver.get("http://10.207.182.108:81/opencart/index.php?route=product/compare");
		  
		  
		  WebElement para=driver.findElement(By.id("tab-description"));
		  String text=para.findElement(By.tagName("p")).getText();
		  
		  FileWriter desc=new FileWriter("D:/Automation Test//Desc.txt");
		  BufferedWriter br_desc=new BufferedWriter(desc);
		  if(text==null)
		  {
			  br_desc.write("<NO DESCRIPTION>");
			  br_desc.close();
			  
		  }
		  else
		  {
		  List<WebElement> short_desc=driver.findElements(By.xpath(".//*[@id='tab-description']/ul/li"));
		  br_desc.write(short_desc.get(4).getText());
		  br_desc.close();
		  }
		  
		  WebDriverWait wait=new WebDriverWait(driver, 5000);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
		
		  WebElement shopping_cart=driver.findElement(By.className("success"));
		  shopping_cart.findElement(By.linkText("shopping cart")).click();
		  
		  List<WebElement> checkout=driver.findElements(By.className("button"));
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
		  
		  driver.navigate().back();
		  
		  
		  List<WebElement> verify_shoppingcart=driver.findElements(By.className("content"));
		  
		  Assert.assertEquals("Not Empty", verify_shoppingcart.get(1).getText(), "Your shopping cart is empty!");
		  
		  List<WebElement> checkout1=driver.findElements(By.className("column"));
			checkout1.get(3).findElement(By.linkText("Order History")).click();
			
			/*String order_num=driver.findElement(By.className("order-id")).getText();
			System.out.println(order_num);
			
			WebElement order_price=driver.findElement(By.className("order-content"));
			System.out.println(order_price.findElement(By.tagName("div")).getText());
			*/
			
			List<WebElement> my_account=driver.findElements(By.className("column"));
			my_account.get(3).findElement(By.linkText("Newsletter")).click();
			
			
			List<WebElement> extras=driver.findElements(By.className("column"));
			
			extras.get(2).findElement(By.linkText("Specials")).click();
		  
		
		
	}
}