package parser;

import hibernateSession.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Bookinfo;
import dao.BookinfoDAO;
import dao.FocusinfoDAO;
import utils.LoginHelper;
import utils.MyWebDriver;

public class BookParser extends InterestParser implements Runnable {

	BookinfoDAO bookdao;
	String url;

	public BookParser() {
		super();
		bookdao = new BookinfoDAO();
	}

	public BookParser(int delayTime) {
		super();
		bookdao = new BookinfoDAO();
		this.delayTime=delayTime;
	}

	public void parser() {
		// LoginHelper.login(driver);
		List<Basicinfo> list = bid.findByBookTag(0);
		for (Basicinfo basic : list) {
			url = prefixUrl + basic.getUserName() + "/";
			driver.getWithDelay(url, delayTime);
			WebElement bookEle = driver.getElement(By.id("book"));
			if (bookEle != null) {
				List<WebElement> eles = bookEle.findElements(By.xpath("./h2//span[@class='pl']//a"));
				ArrayList<String> hrefs = new ArrayList<String>();
				HashMap<String, Integer> res = new HashMap<String, Integer>();
				for (WebElement ele : eles) {
					hrefs.add(ele.getAttribute("href"));
				}
				for (String href : hrefs) {
					driver.getWithDelay(href, delayTime);
					HashMap<String, Integer> temp = parserTag();
					for (String key : temp.keySet()) {
						if (res.containsKey(key)) {
							res.put(key, res.get(key) + temp.get(key));
						} else {
							res.put(key, temp.get(key));
						}
					}
				}

				ArrayList<Bookinfo> books = new ArrayList<Bookinfo>();
				for (String key : res.keySet()) {
					Bookinfo book = new Bookinfo();
					book.setBookCategory(key);
					book.setNum(res.get(key));
					book.setUserId(basic.getUserName());
					books.add(book);
				}
				for (Bookinfo book : books) {
					bookdao.save(book);
				}
			}
			basic.setBookTag(1);
			bid.merge(basic);
			HibernateSessionFactory.getSession().flush();

		}
		driver.close();
	}


	public static void main(String[] args) {
		BookParser bp = new BookParser();
		bp.parser();
//		try {
//			com.mysql.jdbc.Driver driver=new com.mysql.jdbc.Driver();
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

//	@Override
	public void run() {
		System.out.println("book");
		parser();
	}
	
	
	

}
