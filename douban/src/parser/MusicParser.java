package parser;

import hibernateSession.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.LoginHelper;
import utils.MyWebDriver;
import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Bookinfo;
import dao.BookinfoDAO;
import dao.Movieinfo;
import dao.Musicinfo;
import dao.MusicinfoDAO;

public class MusicParser extends InterestParser implements Runnable {
	MusicinfoDAO musicdao;
	// String prefixUrl;
	String url;
	// MyWebDriver driver;
	// BasicinfoDAO bid;
	// int delayTime;

	public MusicParser(int delayTime) {
		super();
		musicdao = new MusicinfoDAO();
		this.delayTime=delayTime;
	}
	
	public MusicParser() {
		super();
		musicdao = new MusicinfoDAO();
	}

	public void parser() {
		// LoginHelper.login(driver);
		List<Basicinfo> list = bid.findByMusicTag(0);
		for (Basicinfo basic : list) {
			url = prefixUrl + basic.getUserName() + "/";
			driver.getWithDelay(url, delayTime);
			WebElement musicEle = driver.getElement(By.id("music"));
			if (musicEle != null) {
				List<WebElement> eles = musicEle.findElements(By.xpath("./h2//span[@class='pl']//a"));
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

				ArrayList<Musicinfo> musics = new ArrayList<Musicinfo>();
				for (String key : res.keySet()) {
					Musicinfo music = new Musicinfo();
					// movie.setMovieCategory(key);
					// book.setBookCategory(key);
					music.setNum(res.get(key));
					music.setUserId(basic.getUserName());
					music.setMusicCategory(key);
					musics.add(music);
				}
				for (Musicinfo music : musics) {
					musicdao.save(music);
				}
			}
			basic.setMusicTag(1);
			bid.merge(basic);
			HibernateSessionFactory.getSession().flush();
		}
		driver.close();
	}

	// public HashMap<String, Integer> parserMusicTag(){
	// HashMap<String, Integer> map=new HashMap<String, Integer>();
	// ArrayList<WebElement> eles=(ArrayList<WebElement>)
	// driver.findElements(By.xpath("//li[@class=' clearfix']"));
	// for(WebElement ele:eles){
	// String category=ele.findElement(By.xpath("./a")).getText();
	// int num=Integer.parseInt(ele.findElement(By.xpath("./span")).getText());
	// map.put(category, num);
	// }
	//
	// return map;
	//
	// }

//	public static void main(String[] args) {
//		MusicParser mp = new MusicParser();
//		mp.parser();
//	}

	@Override
	public void run() {
		System.out.println("music");
		parser();
	}

}
