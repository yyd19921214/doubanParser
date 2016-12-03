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
import dao.Focusinfo;
import dao.FocusinfoDAO;

/**
 * 
 * @author pc
 *该类用于对某用户被关注信息的抓取
 */
public class RevLinkParser extends AbstractParser {
//	String prefixUrl;
	String url;
	FocusinfoDAO fid;
	
	public RevLinkParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		fid=new FocusinfoDAO();
	}
	
	public RevLinkParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/people/";
		fid=new FocusinfoDAO();
		this.delayTime=delayTime;
	}
	
	public void parser(){
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list=(ArrayList<Basicinfo>) bid.findByRevLinkTag(0);
		for(Basicinfo basic:list){
			url=prefixUrl+basic.getUserName()+"/rev_contacts";
			try{
				int size=getRevLinkInfo(url,basic);
				basic.setRevLinkNum(size);
			}catch(Exception e){
				e.printStackTrace();
			}
			basic.setRevLinkTag(1);
			bid.merge(basic);
			HibernateSessionFactory.getSession().flush();
		}
	}
	
	private int getRevLinkInfo(String url,Basicinfo basic){
		driver.getWithDelay(url, delayTime);
		ArrayList<Focusinfo> res=new ArrayList<Focusinfo>();
		WebElement ele=driver.getElement(By.xpath("//div[@id='db-usr-profile']//div[@class='info']//h1"));
		int num=Integer.parseInt(ele.getText().substring(ele.getText().indexOf("(")+1, ele.getText().indexOf(")")));
		int page=num/70;
		for(int i=0;i<=page;i++){
			if(i==0){
				res.addAll(getRevLinkPerPage(basic.getUserName()));
			}
			else{
				driver.getWithDelay(url+"?start="+(70*i), delayTime);
				res.addAll(getRevLinkPerPage(basic.getUserName()));
			}
		}
		for(Focusinfo f:res){
			fid.save(f);
		}
		HibernateSessionFactory.getSession().flush();;
		return res.size();
	}
	
	private ArrayList<Focusinfo> getRevLinkPerPage(String userId){
		ArrayList<Focusinfo> list=new ArrayList<Focusinfo>();
		ArrayList<WebElement> eles=(ArrayList<WebElement>) driver.findElements(By.xpath("//dl[@class='obu']"));
		for(WebElement e:eles){
//			WebElement idEle=e.findElement(By.xpath("./dd//a"));
			String temp=e.findElement(By.xpath("./dd//a")).getAttribute("href");
			String srcId=temp.substring(temp.indexOf("/people")+8, temp.lastIndexOf("/"));
//			String desId=userId;
			Focusinfo focus=new Focusinfo();
			focus.setDesId(userId);
			focus.setSrcId(srcId);
			list.add(focus);
			
		}
		return list;
		
	}
	
//	
//	public void test(String url2){
////		xiaomanxiaoxi
//		LoginHelper.login(driver);
//		url=prefixUrl+url2+"/rev_contacts";
//		driver.getWithDelay(url, delayTime);
//		ArrayList<Focusinfo> res=new ArrayList<Focusinfo>();
//		WebElement ele=driver.getElement(By.xpath("//div[@id='db-usr-profile']//div[@class='info']//h1"));
//		int num=Integer.parseInt(ele.getText().substring(ele.getText().indexOf("(")+1, ele.getText().indexOf(")")));
//		int page=num/70;
//		for(int i=0;i<page;i++){
//			if(i==0){
//				res.addAll(getRevLinkPerPage(url2));
//			}
//			else{
//				driver.getWithDelay(url+"?start="+(70*i), delayTime);
//				res.addAll(getRevLinkPerPage(url2));
//			}
//		}
//		for(Focusinfo f:res){
////			fid.save(f);
//		}
//		HibernateSessionFactory.getSession().flush();;
////		return res.size();
//	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		RevLinkParser revParser=new RevLinkParser();
		revParser.parser();
//		revParser.test("xiaomanxiaoxi");
	}
	

}
