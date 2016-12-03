package parser;

import hibernateSession.HibernateSessionFactory;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverFactory;
import utils.DriverFactory2;
import utils.LoginHelper;
import utils.MyWebDriver;
import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Groupinfo;
import dao.GroupinfoDAO;


public class GroupInfoParser extends AbstractParser{
	
//	String prefixUrl;
	String url;
//	MyWebDriver driver;
//	BasicinfoDAO bid;
	GroupinfoDAO gid;
//	int delayTime;
	
	public GroupInfoParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		gid=new GroupinfoDAO();
	}
	
	public GroupInfoParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/people/";
		gid=new GroupinfoDAO();
		this.delayTime=delayTime;
	}
	
	
	
	public void parser() {

//		driver.manage().deleteAllCookies();
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list = (ArrayList<Basicinfo>) bid
				.findByGroupTag(0);
		for (Basicinfo basic : list) {
			url = prefixUrl + basic.getUserName() + "/";
			driver.getWithDelay(url, delayTime);
			try{
				int size = getGroupInfo(basic);
				basic.setGroupNum(size);
			}catch(Exception e){
//				e.printStackTrace();
			}
			
			basic.setGroupTag(1);
			bid.merge(basic);
			HibernateSessionFactory.getSession().flush();
		}
		driver.close();

	}
	
	
	private int getGroupInfo(Basicinfo basic) {
		WebElement ele = driver.findElement(By.xpath("//div[@id='group']"));
		ArrayList<Groupinfo> res_groups = new ArrayList<Groupinfo>();
		// 此时用户加入的组较少，只在当前页面处理即可（没有“全部”链接）
		if (driver.getElement(By.xpath("./h2//span//a"), ele) == null) {
			ArrayList<WebElement> groups = (ArrayList<WebElement>) ele
					.findElements(By.xpath("./dl[@class='ob ']"));
			for (WebElement e : groups) {
				String groupName = e.findElement(By.xpath("./dd//a")).getText();
				Groupinfo group = new Groupinfo();
				group.setGroupName(groupName);
				group.setPersonId(basic.getUserName());
				String temp_Id = e.findElement(By.xpath("./dd//a"))
						.getAttribute("href");
				group.setGroupId(temp_Id.substring(
						temp_Id.indexOf("/group") + 7, temp_Id.lastIndexOf("/")));
				res_groups.add(group);
			}
		} else {
			String temp_url = driver.getElement(By.xpath("./h2//span//a"), ele)
					.getAttribute("href");
			driver.getWithDelay(temp_url, delayTime);
			// 该groups包括了管理的小组与加入的小组两类，我们只考虑用户加入的小组
			// driver.findElement(By.xpath("(//div[@class='bt_class'])[last()]"))
			WebElement groups_all = driver.findElement(By
					.xpath("//div[@class='mod '][last()]"));
			ArrayList<WebElement> groups_join = (ArrayList<WebElement>) groups_all
					.findElements(By
							.xpath("./div[@class='group-list group-cards']//ul//li"));
			for (WebElement g : groups_join) {
				WebElement tempEle = g.findElement(By
						.xpath("./div[@class='info']//div[@class='title']//a"));
				String temp = tempEle.getAttribute("href");
				Groupinfo group = new Groupinfo();
				group.setPersonId(basic.getUserName());
				group.setGroupName(tempEle.getText());
				group.setGroupId(temp.substring(temp.indexOf("/group") + 7,
						temp.lastIndexOf("/")));
				temp = g.findElement(
						By.xpath("./div[@class='info']//span[@class='num']"))
						.getText();
				group.setGroupSize(Integer.parseInt(temp.substring(1,
						temp.lastIndexOf(")"))));
				res_groups.add(group);
			}

		}
		for (Groupinfo g : res_groups) {
			gid.save(g);
		}
		HibernateSessionFactory.getSession().flush();
		return res_groups.size();
	}
	
//	public void test(String url2){
////		xiaomanxiaoxi
//		LoginHelper.login(driver);
//		url = prefixUrl + url2 + "/";
//		driver.getWithDelay(url, delayTime);
//		
//		WebElement ele=driver.findElement(By.xpath("//div[@id='group']"));
//		ArrayList<Groupinfo> res_groups=new ArrayList<Groupinfo>();
//		//此时用户加入的组较少，只在当前页面处理即可（没有“全部”链接）
//		if(driver.getElement(By.xpath("./h2//span//a"), ele)==null){
//			ArrayList<WebElement> groups=(ArrayList<WebElement>) ele.findElements(By.xpath("./dl[@class='ob ']"));
//			for(WebElement e:groups){
//				String groupName=e.findElement(By.xpath("./dd//a")).getText();
//				Groupinfo group=new Groupinfo();
//				group.setGroupName(groupName);
//				group.setPersonId(url2);
//				String temp_Id=e.findElement(By.xpath("./dd//a")).getAttribute("href");
//				group.setGroupId(temp_Id.substring(temp_Id.indexOf("/group")+7,temp_Id.lastIndexOf("/")));
//				res_groups.add(group);
//			}
//		}
//		else{
//			String temp_url=driver.getElement(By.xpath("./h2//span//a"), ele).getAttribute("href");
//			driver.getWithDelay(temp_url, delayTime);
//			//该groups包括了管理的小组与加入的小组两类，我们只考虑用户加入的小组
////			driver.findElement(By.xpath("(//div[@class='bt_class'])[last()]"))
//			WebElement groups_all=driver.findElement(By.xpath("//div[@class='mod '][last()]"));
////			for(WebElement e:groups_all){
////				if(e.findElement(By.xpath("./h2")).getText().equals("加入的小组")){
//					ArrayList<WebElement> groups_join=(ArrayList<WebElement>) groups_all.findElements(By.xpath("./div[@class='group-list group-cards']//ul//li"));
//					for(WebElement g:groups_join){
//						WebElement tempEle=g.findElement(By.xpath("./div[@class='info']//div[@class='title']//a"));
//						String temp=tempEle.getAttribute("href");
//						Groupinfo group=new Groupinfo();
//						group.setPersonId(url2);
//						group.setGroupName(tempEle.getText());
//						group.setGroupId(temp.substring(temp.indexOf("/group")+7,temp.lastIndexOf("/")));
//						temp=g.findElement(By.xpath("./div[@class='info']//span[@class='num']")).getText();
//						group.setGroupSize(Integer.parseInt(temp.substring(1, temp.lastIndexOf(")"))));
//						res_groups.add(group);
//					}
//
//		}
//	}
	
	
	
	public static void main(String[] args) {
		GroupInfoParser groupParser=new GroupInfoParser();
//		try{
			groupParser.parser();
//		}catch(Exception e){
			
//		}
//		groupParser.test("xiaomanxiaoxi");
		
	}

}
