package parser;

import hibernateSession.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverFactory2;
import utils.LoginHelper;
import utils.MyWebDriver;
import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Focusinfo;
import dao.FocusinfoDAO;
import dao.ParserconfigDAO;

public class FocusInfoParser extends AbstractParser {
	String url;
	FocusinfoDAO fid;
	
	
	public FocusInfoParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		fid=new FocusinfoDAO();
	}
	
	public FocusInfoParser(int delayTime) {
		super();
		prefixUrl = "http://www.douban.com/people/";
		fid = new FocusinfoDAO();
		this.delayTime = delayTime;
	}
	
	
	
	public void parser() {
//		driver.manage().deleteAllCookies();
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list = (ArrayList<Basicinfo>) bid
				.findByFriendsTag(0);
		

		for (int i = 0; i < list.size(); i++) {
			// 处理该用户
			url = prefixUrl + list.get(i).getUserName() + "/contacts";
			try{
				getFocusInfo(url, list.get(i));
			}catch(Exception e){
//				e.printStackTrace();
			}
			
			list.get(i).setFriendsTag(1);
			bid.merge(list.get(i));
			HibernateSessionFactory.getSession().flush();
		}

	}
	
	
	private void getFocusInfo(String url,Basicinfo basic){
		int focusNum;
		String temp1;
		driver.getWithDelay(url, delayTime);		
		WebElement numEle=driver.findElement(By.xpath("//div[@class='info']//h1"));
		temp1=numEle.getText();
		focusNum=Integer.parseInt(temp1.substring(temp1.indexOf("(")+1, temp1.indexOf(")")));
		basic.setFriendsNum(focusNum);
		ArrayList<Focusinfo> focusList = new ArrayList<Focusinfo>();
		if(focusNum!=0){
			List<WebElement> focusElement = driver.getElements(By
					.xpath("//dl[@class='obu']"));
			if (focusElement != null) {
				String temp;
				for (WebElement p : focusElement) {
					Focusinfo f = new Focusinfo();
					f.setSrcId(basic.getUserName());
					temp = p.findElement(By.xpath("./dt//a")).getAttribute("href");
					f.setDesId(temp.substring(temp.indexOf("people/") + 7,
							temp.lastIndexOf("/")));
					focusList.add(f);
				}
			}
		}
		for(Focusinfo f:focusList){
			fid.save(f);
		}
		
		HibernateSessionFactory.getSession().flush();
	}
	
//	private ArrayList<Focusinfo> getFocusInfoPerPage(String userName) {
//		ArrayList<Focusinfo> focusList = new ArrayList<Focusinfo>();
//		List<WebElement> focusElement = driver.getElements(By
//				.xpath("//dl[@class='obu']"));
//		if (focusElement != null) {
//			String temp;
//			for (WebElement p : focusElement) {
//				Focusinfo f = new Focusinfo();
//				f.setSrcId(userName);
//				temp = p.findElement(By.xpath("./dt//a")).getAttribute("href");
//				f.setDesId(temp.substring(temp.indexOf("people/") + 7,
//						temp.lastIndexOf("/")));
//				focusList.add(f);
//			}
//		}
//		return focusList;
//	}
	public static void main(String[] args) {
		FocusInfoParser fip=new FocusInfoParser();
		fip.parser();
	}

}
