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
import dao.Doulist;
import dao.Likeinfo;
import dao.LikeinfoDAO;

public class LikeInfoParser extends AbstractParser {
	
	String url;
	LikeinfoDAO lid;
	
	public LikeInfoParser(){
		super();
		prefixUrl="http://www.douban.com/people/";
		lid=new LikeinfoDAO();
	}
	
	public LikeInfoParser(int delayTime){
		super();
		prefixUrl="http://www.douban.com/people/";
		lid=new LikeinfoDAO();
		this.delayTime=delayTime;
	}
	
	
	public void parser() {
//		LoginHelper.login(driver);
		ArrayList<Basicinfo> list = (ArrayList<Basicinfo>) bid.findByLikeTag(0);
		for (Basicinfo basic : list) {
			url = prefixUrl + basic.getUserName() + "/likes";
			try {
				driver.getWithDelay(url, delayTime);
				ArrayList<WebElement> eles = (ArrayList<WebElement>) driver
						.getElements(By.xpath("//div[@class='title']//a"));
				if (eles != null) {// 此时必定有喜欢的内容，但是否分页显示还需要研究
					ArrayList<Likeinfo> res = new ArrayList<Likeinfo>();
					WebElement paginator = driver.getElement(By
							.xpath("//div[@class='paginator']"));
					if (paginator != null) { // 此时可以判断，该用户喜欢的内容较多需要分页处理
						WebElement pagesEle = paginator.findElement(By.className("thispage"));
						int pageNum=Integer.parseInt(pagesEle.getAttribute("data-total-page"));
						for(int t=1;t<=pageNum;t++){
							if(t==1){
								res.addAll(getLikeListPage(basic.getUserName()));
							}
							else{
								url = prefixUrl + basic.getUserName() + "/likes/?start="+(t-1)*15;
								driver.getWithDelay(url, delayTime);
								res.addAll(getLikeListPage(basic.getUserName()));
							}
							
						}
					} else {
						res = getLikeListPage(basic.getUserName());
					}
					for (Likeinfo like : res) {
						lid.save(like);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				basic.setLikeTag(1);
				bid.merge(basic);
				HibernateSessionFactory.getSession().flush();
			}

			
		}
	}
	
	
	private ArrayList<Likeinfo> getLikeListPage(String userId){
		ArrayList<Likeinfo> likelist=new ArrayList<Likeinfo>();
//		ArrayList<WebElement> eles=(ArrayList<WebElement>) driver.findElements(By.xpath("//ul[@class='doulist-list']//li"));
		ArrayList<WebElement> eles = (ArrayList<WebElement>) driver
				.getElements(By.xpath("//div[@class='title']//a"));
		if(eles!=null){
			for (WebElement ele : eles) {
				// WebElement
				// e=ele.findElement(By.xpath(".//div[@class='title']//a"));
				// WebElement
				// e=ele.findElement(By.xpath("./div[@class='status-item']//div[@class='block']//div[@class='content']//div[@class='title']//a"));
				String title = ele.getText();
				String[] temp = ele.getAttribute("href").split("/");
				String likeId = temp[temp.length - 1];
				Likeinfo like = new Likeinfo();
				like.setTitle(title);
				like.setLikeId(likeId);
				like.setUserId(userId);
				likelist.add(like);
			}
		}
		
		return likelist;
	}
	
	
	public static void main(String[] args) {
		LikeInfoParser likeParser=new LikeInfoParser();
		likeParser.parser();
//		likeParser.test("xiaomanxiaoxi");
//		likeParser.parser();
//		String str="http://www.douban.com/group/topic/79426968/";
//		String[] temp=str.split("/");
//		for(int i=0;i<temp.length;i++){
//			System.out.println(i+"   :"+temp[i]);
//		}
	}
	

}
