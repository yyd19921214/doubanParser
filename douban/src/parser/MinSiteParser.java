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
import dao.Minsiteinfo;
import dao.MinsiteinfoDAO;

/**
 * 用来抓取关注的小站的信息
 * @author pc
 *
 */
public class MinSiteParser extends AbstractParser {
	
//	String prefixUrl;
	String url;
//	MyWebDriver driver;
//	BasicinfoDAO bid;
	MinsiteinfoDAO msd;
//	int delayTime;
	
	public MinSiteParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		msd=new MinsiteinfoDAO();
	}
	
	public MinSiteParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/people/";
		msd=new MinsiteinfoDAO();
		this.delayTime=delayTime;
	}
	
	public void parser(){
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list=(ArrayList<Basicinfo>) bid.findByMinsiteTag(0);
		for(Basicinfo basic:list){
			url=prefixUrl+basic.getUserName()+"/minisite";
			try{
				getMinSiteInfo(url,basic);
			}catch(Exception e){
//				e.printStackTrace();
			}
//			getMinSiteInfo(url,basic);
			basic.setMinsiteTag(1);
			bid.save(basic);
			HibernateSessionFactory.getSession().flush();
		}
	}
	
	private void getMinSiteInfo(String url,Basicinfo basic){
		driver.getWithDelay(url, delayTime);
		ArrayList<Minsiteinfo> res=new ArrayList<Minsiteinfo>();
		WebElement ele=driver.findElement(By.xpath("//div[@id='content']//h1"));
		String temp=ele.getText();
		int num=Integer.parseInt(temp.substring(temp.indexOf("(")+1, temp.indexOf(")")));
		for(int i=0;i<=num/20;i++){
			if(i==0){
				res.addAll(getMinSitePerPage(basic.getUserName()));
			}
			else{
				driver.getWithDelay(url+"?start="+(20*i), delayTime);
				res.addAll(getMinSitePerPage(basic.getUserName()));
			}
		}
		for(Minsiteinfo min:res){
			msd.save(min);
		}
		HibernateSessionFactory.getSession().flush();
		
	}
	
	private ArrayList<Minsiteinfo> getMinSitePerPage(String userId){
		ArrayList<Minsiteinfo> list=new ArrayList<Minsiteinfo>();
		ArrayList<WebElement> eles=(ArrayList<WebElement>) driver.findElements(By.xpath("//li[@class='clearfix']"));
		for(WebElement e:eles){
			String siteId=e.getAttribute("id").substring(1);
			String title=e.findElement(By.xpath("./a")).getAttribute("title");
			Minsiteinfo site=new Minsiteinfo();
			site.setSiteId(siteId);
			site.setSiteTitle(title);
			site.setUserId(userId);
			list.add(site);
		}
		return list;
		
	}
	
	public static void main(String[] args) {
		MinSiteParser msp=new MinSiteParser();
		msp.parser();
	}
	
	

}
