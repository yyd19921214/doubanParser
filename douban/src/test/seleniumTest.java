package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.DriverFactory;
import utils.MyWebDriver;

public class seleniumTest {
	public static void main(String[] args) {
//		WebDriver driver=new FirefoxDriver();
//		WebDriver driver=DriverFactory.getFirefoxDriverTextOnly();
//		WebDriver driver=DriverFactory.getdefaultFirefoxDriver();
//		driver.get("http://www.baidu.com/");
//		driver.manage().window().maximize();
//		WebElement txtbox = driver.findElement(By.name("wd"));
//		txtbox.sendKeys("Glen");
//
//		WebElement btn = driver.findElement(By.id("su"));
//		btn.click();
//
//		driver.close();
		
		MyWebDriver driver=new MyWebDriver();
//		driver.get("http://www.sina.com/");
		driver.getWithDelay("http://hr.tencent.com/position.php?&start=30#a", 5);
//		driver.getElement(By.xpath("//div[@class='helloevery']//h1"));
//		WebElement txtbox = driver.findElement(By.name("wd"));
//		txtbox.sendKeys("Glen");
//
//		WebElement btn = driver.findElement(By.id("su"));
//		btn.click();

//		driver.close();
		
		
		

	}
}
