package parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverFactory2;
import utils.MyWebDriver;
import dao.BasicinfoDAO;

/**
 * 
 * @author pc
 * 抽象类，给出了对电影，书籍，音乐这三类信息抓取的公共方法
 *
 */
public abstract class InterestParser extends AbstractParser{
//	String prefixUrl;
//	MyWebDriver driver;
//	BasicinfoDAO bid;
//	int delayTime;
	
	public  InterestParser() {
		super();
		prefixUrl="http://www.douban.com/people/";
	}
	
	/*
	 * 该方法对电影，书籍，音乐的标签进行抓取
	 */
	public HashMap<String, Integer> parserTag(){
		//对点击后才会展开的列表进行处理，先进行模拟点击
		 
		WebElement open=driver.getElement(By.id("open_tags"));
		if(open==null){
//			System.out.println("null");
		}
		else{
			open.click();
		}
		
		
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		ArrayList<WebElement> eles=(ArrayList<WebElement>) driver.findElements(By.xpath("//li[@class=' clearfix']"));
		for(WebElement ele:eles){
			String category=ele.findElement(By.xpath("./a")).getText();
			int num=Integer.parseInt(ele.findElement(By.xpath("./span")).getText());
			map.put(category, num);
		}
		return map;
	}
	
//	public abstract void parser();
	
	
	

}
