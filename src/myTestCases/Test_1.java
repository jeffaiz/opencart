package myTestCases;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Test_1 {
	
	private FirefoxDriver driver;
	private String baseUrl;
	
	
	@Test
	public void setup() throws Exception{
		
		String text="Result 1 to 2 of 1";
		System.out.println(text.length());
		System.out.println(text.charAt(17));
		
		
	}
	
	@Ignore
	public void login() throws InterruptedException{
		baseUrl = "http://10.207.182.108:81/opencart/index.php?route=account/login/";
		driver.get(baseUrl);
		//fd.findElementByXPath(".//*[@id='welcome']/a[1]").click();
		driver.findElementByName("email").sendKeys("jeffaiz72@gmail.com");
		driver.findElementByName("password").sendKeys("faiz_faiz54");
		driver.findElementByXPath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]").click();
		//driver.findElementByClassName("button").click();
		Thread.sleep(5000);
	}
	
	@Ignore
	@Test
	public void check_contatc(){
		
		try {
			login();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get(baseUrl);
		//fd.findElementByXPath(".//*[@id='welcome']/a[1]").click();
		WebDriverWait wait=new WebDriverWait(driver, 5);
		
		driver.findElementByName("email").sendKeys("jeffaiz72@gmail.com");
		driver.findElementByName("password").sendKeys("faiz_faiz54");
		driver.findElementByXPath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]").click();
		//driver.findElementByClassName("button").click();
		
		WebDriverWait wait1=new WebDriverWait(driver, 10);
		
		//driver.findElementByCssSelector(".button").click();
	
		driver.findElement(By.xpath(".//*[@id='footer']/div[2]/ul/li/a[contains(@href,'contact')]")).click();
		
		
		
		//driver.findElementByXPath("//textarea[@name='enquiry']").sendKeys("This is to Change of Address/Phone number");
		String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
		driver.findElementByName("captcha").sendKeys(captchaVal);
		
		
		//driver.findElementByClassName("button").click();
		
		By xpath1=By.className("button");
		WebElement myDynamicElement1 = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(xpath1));
		myDynamicElement1.click();
		
		Assert.assertEquals("Title Mismatch", driver.getTitle(), "Contact Us");
		
		By xpath=By.xpath(".//*[@id='content']/div[2]/div/a");
		WebElement myDynamicElement2 = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(xpath));
		myDynamicElement2.click();
		
		String web=driver.getCurrentUrl();
		System.out.println(web);
		
		Assert.assertEquals("Title Mismatch", driver.getTitle(), "Your Store");

  }
	
	@Ignore
	@Test
	public void check_homepage() throws IOException
	{
		
		driver.get("http://10.207.182.108:81/opencart/index.php?route=common/home");
		driver.findElementByXPath(".//*[@id='slideshow0']/a").click();
		driver.manage().window().maximize();
		By xpath=By.xpath(".//*[@id='tabs']/a[2]");
		
		WebElement myDynamicElement2 = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(xpath));
		myDynamicElement2.click();
		//driver.findElementByXPath(".//*[@id='tabs']/a[2]").click();
		
		File f=new File("ReviewProduct.xls");
		FileInputStream fs=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sh= wb.getSheetAt(0);
		
		
		
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
				WebElement a=driver.findElementByName("name");
				
				a.sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
				WebElement b=driver.findElementByName("text");
				b.sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
				//driver.findElementByName("text").sendKeys(sh.getRow(i).getCell(1).getStringCellValue());
				DataFormatter formatter = new DataFormatter();
				String x=formatter.formatCellValue(sh.getRow(i).getCell(2));
				
				System.out.println(x);
				
				//sh.getRow(i).getCell(2).getStringCellValue();
				int num=Integer.parseInt(x);
				System.out.println(num);
				
				switch(num)
				{
				case 4:
					String path=".//*[@id='tab-review']/input[4]";
					driver.findElement(By.xpath(path)).click();		
				case 5:
					String path1=".//*[@id='tab-review']/input[5]";
					driver.findElement(By.xpath(path1)).click();
				case 6:
					String path3=".//*[@id='tab-review']/input[6]";
					driver.findElement(By.xpath(path3)).click();
				
				}
				String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
				WebElement c=driver.findElementByName("captcha");
				c.sendKeys(captchaVal);
				
				driver.findElementById("button-review").click();
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./Screenshots/"+"screenshot"+".png"));
				System.out.println("Screenshot taken");
				
				a.clear();
				b.clear();
				c.clear();
		
	    	
			}
		
		WebElement s=driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div[3]/div/span[2]/a[1]"));
	    s.click();
	    //CLICK ON WISHLISt
	}
	
	
	@Ignore
	public void add_to_wishlist() throws IOException, InterruptedException
	{
//LOGIN
		//login();
		//baseUrl = "http://10.207.182.108:81/opencart/index.php?route=account/login/";
		driver.get("http://10.207.182.108:81/opencart/index.php?route=account/login/");
		WebDriverWait wait=new WebDriverWait(driver, 5);
		driver.findElementByName("email").sendKeys("jeffaiz72@gmail.com");
		driver.findElementByName("password").sendKeys("faiz_faiz54");
		driver.findElementByXPath(".//*[@id='content']/div[2]/div[2]/form/div/input[3]").click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//CLICK ON WISH LIST
	 
		
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("wishlist-total"))).click();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);
		FileWriter fr=new FileWriter("E://data1.txt");
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
		
	}
	
	
	
	
}


