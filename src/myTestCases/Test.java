package myTestCases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	@org.junit.Test
	public void check() throws IOException{
		FirefoxDriver driver=new FirefoxDriver();
		
		driver.get("http://10.207.182.108:81/opencart/index.php?route=account/login");
		
		WebElement userName=driver.findElement(By.name("email"));
		userName.sendKeys("jeffaiz72@gmail.com");
		
		WebElement passWord=driver.findElement(By.name("password"));
		passWord.sendKeys("faiz_faiz54");

		WebElement sigNin=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]"));
		sigNin.click();
		  
		  
		List<WebElement> checkout=driver.findElements(By.className("column"));
		checkout.get(3).findElement(By.linkText("Order History")).click();
		
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
