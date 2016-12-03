package parser;
/**
 * 代码的不足之处：
 * 没有总控装置 //已解决
 * 对cookie的传递没有实现                //通过读取本地默认配置的问题部分解决了
 * 有时候浏览器会卡住，是否能通过定时判断浏览器的URL方式来达到关闭卡住浏览器然后重启的功能 //已解决
 * 对喜欢列表的抓取没有做到每页抓取时//已解决
 * 对类似读书这样需要点击才会出现的信息没有做到全部抓取//已解决
 * 
 * 代码的不足之处：
 * 尚未完整的测试
 */

import hibernateSession.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverFactory2;
import utils.MyWebDriver;
import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Parserconfig;
import dao.ParserconfigDAO;

public class BasicMemInfoParser extends AbstractParser {
	String url;
	ParserconfigDAO pcd;
	
	public BasicMemInfoParser(){
		super();
		prefixUrl="http://www.douban.com/group/pku/members";
		pcd=new ParserconfigDAO();
	}
	
	public BasicMemInfoParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/group/pku/members";
		pcd=new ParserconfigDAO();
		this.delayTime=delayTime;
	}
	
	public void parser(){
		ArrayList<Basicinfo> mems;
		Parserconfig pc=(Parserconfig) pcd.findAll().get(0);
		int page=pc.getFinishedPage();
		for(int p=page+1;p<=pc.getMaxPage();p++){
			url=getUrl(prefixUrl,p);
			driver.getWithDelay(url, delayTime);
			mems=getMemBasicInfo();
			saveMemList(mems);
			System.out.println(p+"finished");
			pc.setFinishedPage(p);
			pcd.merge(pc);
			HibernateSessionFactory.getSession().flush();
		}
		driver.close();
	}
	
	public String getUrl(String prefixUrl,int p){
		int personPerPage=35;//该变量表示每一页的人数;
		String ending=String.valueOf((p-1)*personPerPage);
		return prefixUrl+"?start="+ending;
		
		
	}
	
	private ArrayList<Basicinfo> getMemBasicInfo(){
		ArrayList<Basicinfo> members=new ArrayList<Basicinfo>();
		List<WebElement> memberList = driver.findElements(By.xpath("//div[@class='member-list']//ul//li"));
		for(WebElement ele:memberList){
			WebElement idElement = ele.findElement(By.xpath("./div[@class='name']//a"));
			Basicinfo mem=new Basicinfo();
			mem.setNickName(idElement.getText());
			
			String temp=idElement.getAttribute("href");
			int index=temp.indexOf("/people");
			String str_userId=temp.substring(index+8,temp.lastIndexOf("/"));
			mem.setUserName(str_userId);
			
			WebElement idElement2=driver.getElement(By.xpath("./div[@class='name']//span[@class='pl']"), ele);
			if(idElement2!=null){
				String address=idElement2.getText();
				mem.setAddress(address);
			}
			mem.setBookTag(0);
//			mem.setDouListNum(0);
			mem.setDouTag(0);
			mem.setFriendsTag(0);
			mem.setGroupTag(0);
			mem.setLikeTag(0);
			mem.setMinsiteTag(0);
			mem.setMovieTag(0);
			mem.setMusicTag(0);
			mem.setNotesTag(0);
			mem.setRevLinkTag(0);
			mem.setStatusTag(0);
			members.add(mem);
		}
		return members;
	}
	
	private void saveMemList(ArrayList<Basicinfo> mems){
		
		for(int t=0;t<mems.size();t++){
			bid.save(mems.get(t));
		}
		HibernateSessionFactory.getSession().flush();
		
	}

	
	public static void main(String[] args) {
		BasicMemInfoParser basicpar=new BasicMemInfoParser();
		basicpar.parser();
	}

}
