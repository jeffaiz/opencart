package myTestCases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_02 {
	WebDriver driver=new FirefoxDriver();
	String line;

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
	  
	 //@Test(enabled=false)
	 @Test(priority=2)
	  public void search() throws IOException
	  {
		   
		  
		   WebElement search=driver.findElement(By.name("search"));
		   search.sendKeys("apple");
		   driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		   
		   WebDriverWait output = new WebDriverWait(driver, 10);
		   String result=  output.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))).getText();
		   
		   FileWriter fr=new FileWriter("D:/Automation Test//Search_Data_Result.txt");
		   BufferedWriter br=new BufferedWriter(fr);
		   br.write("Apple Search :"+result.charAt(18));
		   br.newLine();
		   
		   
		   Select dropdown = new Select(driver.findElement(By.name("category_id")));
		   dropdown.selectByIndex(9);
		   driver.findElement(By.name("sub_category")).click();
		   
		   driver.findElement(By.id("button-search")).click();
		   br.write("Drop Down Search :"+result.charAt(18));
		   
		   br.close();
		  
	  }
	  @Test(priority=3)
	  public void sort_compare_Products() throws IOException, InterruptedException
	  {
		   driver.findElement(By.id("menu")).findElement(By.xpath("//li[6]/a")).click();
		   WebDriverWait output = new WebDriverWait(driver, 10);
		 
		   //Verification [No. of items in the o/p file]
		   String result=  output.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))).getText();
		   FileWriter fr=new FileWriter("D:/Automation Test//Search_Data_Result.txt");
		   BufferedWriter br=new BufferedWriter(fr);
		   br.write("Phones & PDA :"+result.charAt(18));
		   br.newLine();
		   br.close();
		  //Ends
		   
		   driver.findElement(By.className("sort")).findElement(By.tagName("select")).click();
		   Select dropdown = new Select(driver.findElement(By.className("sort")).findElement(By.tagName("select")));
		   dropdown.selectByIndex(4);
		
		   //Verification[Validate the Price]
		   String price[]={"$279.99\nEx Tax: $279.99", "$101.00\nEx Tax: $101.00" ,"$100.00\nEx Tax: $100.00"};
		   List<WebElement> list=driver.findElements(By.className("price"));
		   Iterator<WebElement> itr = list.iterator();
		   int i=0;
		   while(itr.hasNext())
		   {
			   Assert.assertEquals(price[i], itr.next().getText(), "Price are not equal");
			   i++;
		   
		   }
		  // 
		   
		   
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
			  
	 	    //Verification[Saved product names for validation]
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
			  //Ends
			  
			  driver.findElement(By.id("compare-total")).click(); 
			  
			  //Validation[Compare from saved file]
			  
			  @SuppressWarnings("resource")
			  BufferedReader breader = new BufferedReader(new FileReader("D:/Automation Test//Product Name.txt"));
			  List<WebElement> check=driver.findElements(By.className("name"));
			  Iterator<WebElement> itr4= check.iterator();
			  while(itr4.hasNext())
			  {
				  
				  while((line=breader.readLine())!=null)
				  {
					   Assert.assertEquals(line,itr4.next().getText(),"Error in Comparing from external files");   
				  }
				  
				  
			  } 
			  
			  breader.close();
			  //Ends
			  
			  Thread.sleep(5000);
			  
			  List <WebElement> one_element=driver.findElements(By.className("name"));
			  one_element.get(1).click();
			  
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
			  
			  WebDriverWait wait=new WebDriverWait(driver, 5000);
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart"))).click();
			
			  WebElement shopping_cart=driver.findElement(By.className("success"));
			  shopping_cart.findElement(By.linkText("shopping cart")).click();	  
			  
		     }  
	 
		   
	   }
	  
	  @Test(priority=4)
	  public void checkout_subscribe() throws InterruptedException
	  {
		  CommonLib obj= new CommonLib(driver);
		  obj.checkout();
		  
		  //Press on Back Button
		  Thread.sleep(5000);
		  driver.navigate().back();
		  

		  List<WebElement> verify_shoppingcart=driver.findElements(By.className("content"));
		  
		
		  
		  Assert.assertEquals(verify_shoppingcart.get(1).getText(), "Your shopping cart is empty!","Error in matching");
		  
		  List<WebElement> checkout1=driver.findElements(By.className("column"));
		  checkout1.get(3).findElement(By.linkText("Order History")).click();
			
			
		  List<WebElement> my_account=driver.findElements(By.className("column"));
		  my_account.get(3).findElement(By.linkText("Newsletter")).click();
			
			
		  List<WebElement> extras=driver.findElements(By.className("column"));
		  extras.get(2).findElement(By.linkText("Specials")).click();
	  }
	  
	  @Test(priority=5)
	  public void finish() throws InterruptedException{
		 
		  Thread.sleep(5000);
		  CommonLib obj= new CommonLib(driver);
		  obj.logout();
	  }
	  
	  

	  
	  
}
