package myTestCases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Product_Comparision {
	
	
	String user_name="Faisal";
	String baseUrl="http://10.207.182.108:81/opencart/";
	FirefoxDriver driver1;
	//To open text file
	String line=null;
    BufferedReader breader;
    List<String> records = new ArrayList<String>();
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	
	
	public void launch() throws InterruptedException, IOException{
		
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
		
	        
	    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    capabilities.setCapability("moz:firefoxOptions", options);
	    
	    FirefoxDriver driver=new FirefoxDriver(capabilities);

		try{
			
			   driver.get(baseUrl);
			   
			   
			   System.out.println("enter");
				//WebDriverWait wait=new WebDriverWait(driver, 5);
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
			    
			    Assert.assertEquals("Not Matched",user_text, user_name);
			    
				
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
		
		   FileWriter fr=new FileWriter("D:/Automation Test//Search_Data.txt");
		   BufferedWriter br=new BufferedWriter(fr);
		  
		   WebElement search=driver.findElement(By.name("search"));
		   search.sendKeys("apple");
		   driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		   
		   
		//   WebDriverWait w=new WebDriverWait(driver, 10);
		  // WebElement find=w.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-list")));
		   //find.getSize();
		   
		  Thread.sleep(5000);
		  
		  //WebElement count =(WebElement) driver.findElement(By.className("product-list"));
		   //int size= count.findElements(By.tagName("button")).size();
		   //System.out.println(size);
		 
		   
		  List all_prod_1=driver.findElements(By.xpath("//*[@id='content']/div[6]/div"));
		  br.write("Search 1 :"+ all_prod_1.size());
		  br.newLine();
		   
		  WebDriverWait w=new WebDriverWait(driver, 10);
		  w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='menu']/ul/li[6]/a"))).click();
		   
		  List all_prod=driver.findElements(By.xpath("//*[@id='content']/div[4]/div"));
		  br.write("Search 2 :"+all_prod.size());
		  
		  br.close();
		  
		  
		  
		  Select dropdown = new Select(driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[3]/select")));
		  
		  dropdown.selectByVisibleText("Price (High > Low)");
		  
		 
		  String arr[]={"$279.99\nEx Tax: $279.99", "$101.00\nEx Tax: $101.00" ,"$100.00\nEx Tax: $100.00"};
		  //Sort By High to Low and Compare 
		  List<WebElement> list=driver.findElements(By.className("price"));
		  Iterator<WebElement> itr = list.iterator();
		  int i=0;
		  while(itr.hasNext())
		  {
			  //System.out.println(itr.next().getText());
			  Assert.assertEquals("Warning", itr.next().getText(),arr[i]);
			  i++;
		  }
		  
		  
		  //Add to compare and Close
		  List<WebElement> compare=driver.findElements(By.className("compare"));
		  Iterator<WebElement> itr2 = compare.iterator();
		  List<WebElement> closed=driver.findElements(By.className("close"));
		  Iterator<WebElement> itr3 = closed.iterator();
		  while(itr2.hasNext())
		  {
			  itr2.next().click();
			  while(itr3.hasNext())
			  {
				  itr3.next().click();
			  }
		  }
		  
		  //Save Product Name
		  
		  List<WebElement> add_name=driver.findElements(By.className("name"));
		  Iterator<WebElement> itr1 = add_name.iterator();
		  FileWriter product_name=new FileWriter("D:/Automation Test//Product Name.txt");
		  @SuppressWarnings("resource")
		  BufferedWriter bwriter=new BufferedWriter(product_name);
		  while(itr1.hasNext())
		  {
			  bwriter.write(itr1.next().getText());
			  bwriter.newLine();
		  }
		  bwriter.close();

		  //Open compare page and verify from external file
		  
		  driver.findElement(By.id("compare-total")).click(); 
		  breader = new BufferedReader(new FileReader("D:/Automation Test//Product Name.txt"));
		 
		  
		  List<WebElement> check=driver.findElements(By.className("name"));
		  Iterator<WebElement> itr4= check.iterator();
		  while(itr4.hasNext())
		  {
			  
			  while((line=breader.readLine())!=null)
			  {
				  Assert.assertEquals("Error in Comparing", itr4.next().getText(),line);
			  }
			  
		  }
		  breader.close();
		  
		  WebDriverWait ws=new WebDriverWait(driver, 5000);
		  List <WebElement> one_element=(List<WebElement>) ws.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));
		  one_element.get(0).click();
		  
		  
		  //driver.get("http://10.207.182.108:81/opencart/index.php?route=product/product&product_id=29");
		  
		  
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
	
	
	//Click on First Link
	
		/*
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
		
	        
	    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    capabilities.setCapability("moz:firefoxOptions", options);
	    
	    FirefoxDriver driver=new FirefoxDriver(capabilities);
	    
	    //Call login()
	    
	    CommonLib lib=new CommonLib();
	    System.out.println("login");
	    lib.login("jeffaiz72@gmail.com", "faiz_faiz54");
	    System.out.println("logout");
	    */

}
