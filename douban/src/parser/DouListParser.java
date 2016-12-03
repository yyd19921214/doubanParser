package parser;

import hibernateSession.HibernateSessionFactory;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverFactory2;
import utils.LoginHelper;
import utils.MyWebDriver;
import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Doulist;
import dao.DoulistDAO;

public class DouListParser extends AbstractParser{
	
	String url;
	DoulistDAO dld;
	
	public DouListParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		dld=new DoulistDAO();
	}
	
	public DouListParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/people/";
		dld=new DoulistDAO();
		this.delayTime=delayTime;
	}
	
	public void parser(){
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list=(ArrayList<Basicinfo>) bid.findByDouTag(0);
		for(Basicinfo basic:list){
			url=prefixUrl+basic.getUserName()+"/doulists/collect";
			try{
				int size=getDouListInfo(url,basic);
				basic.setDouListNum(size);
			}catch(Exception e){
//				e.printStackTrace();
			}

			basic.setDouTag(1);
			bid.merge(basic);
			HibernateSessionFactory.getSession().flush();
		}
		
	}
	
	private int getDouListInfo(String url,Basicinfo basic){
		driver.getWithDelay(url, delayTime);
		ArrayList<Doulist> res=new ArrayList<Doulist>();
		WebElement ele=driver.getElement(By.xpath("//div[@class='xbar']//div//span//span"));
		int num=Integer.parseInt(ele.getText().substring(ele.getText().indexOf("(")+1, ele.getText().indexOf(")")));
//		System.out.println(num);
		int page=num/20;
		for(int i=0;i<=page;i++){
			if(i==0){
				res.addAll(getDouListPage(basic.getUserName()));
			}
			else{
				driver.getWithDelay(url+"?start="+(20*i), delayTime);
				res.addAll(getDouListPage(basic.getUserName()));
			}
		}
		
		for(Doulist d:res){
			dld.save(d);
		}
//		HibernateSessionFactory.getSession().flush();
		return res.size();
		
	}
	
	private ArrayList<Doulist> getDouListPage(String userId){
		ArrayList<Doulist> list=new ArrayList<Doulist>();
		ArrayList<WebElement> eles=(ArrayList<WebElement>) driver.findElements(By.xpath("//ul[@class='doulist-list']//li"));
		for(WebElement ele:eles){
			WebElement e=ele.findElement(By.xpath("./h3//a"));
			String douName=e.getText();
			String[] temp=e.getAttribute("href").split("/");
			String douId=temp[temp.length-1];
			Doulist dou=new Doulist();
			dou.setDouListName(douName);
			dou.setDouListId(douId);
			dou.setUserId(userId);
			String str_size=ele.findElement(By.xpath("./div[@class='collect']//span[@class='collect-num']")).getText();
			int size=Integer.parseInt(str_size.split("人")[0]);
			dou.setDouListSize(size);
			list.add(dou);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		DouListParser dou=new DouListParser();
//		try{
			dou.parser();
//		}catch(Exception e){
			
//		}
//		dou.parser();
//		String str="http://www.douban.com/doulist/41059012/";
//		String[] temp=str.split("/");
//		for(int i=0;i<temp.length;i++){
//			System.out.println(i+"   :"+temp[i]);
//		}
	}

}
