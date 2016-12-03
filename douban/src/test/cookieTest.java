package test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import utils.MyWebDriver;

public class cookieTest {
	MyWebDriver driver;
	String userName;
	String pwd;
	
	public cookieTest(){
		 driver=new MyWebDriver();
		 userName="547428014@qq.com";
		 pwd="yangshao121";
//		 driver.getRemoteStatus().
//		 driver.getCommandExecutor()
	}
	

	public  boolean test(){
		
			driver.get("https://www.douban.com/accounts/login?source=main");
			
			WebElement nameBox = driver.findElement(By.id("email"));
			WebElement pwdBox = driver.findElement(By.id("password"));
			nameBox.sendKeys(userName);
			pwdBox.sendKeys(pwd);
			WebElement btn = driver.findElement(By.name("login"));
			WebElement remember=driver.findElement(By.id("remember"));
			remember.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn.click();
//			Set<Cookie> cookieSet=driver.manage().getCookies();
			MyWebDriver driver2=new MyWebDriver();
//			for(Cookie c:cookieSet){
//				driver2.manage().addCookie(c);
////				System.out.println(c.getName()+":     "+c.getValue());
//			}
			driver2.get("http://www.douban.com/");
			
			
//			MyWebDriver driver2=new MyWebDriver();
//			driver2.manage().addCookie(arg0);
			
			return true;
//		}catch(Exception e){
//			return false;
//		}
	}
	
	public static void main(String[] args) {
		
		ProfilesIni pi = new ProfilesIni();
		FirefoxProfile profile = pi.getProfile("default");
		WebDriver driver = new FirefoxDriver(profile);
		driver.get("http://www.douban.com/");
	}
	
	
	

}
