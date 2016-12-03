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
import dao.Movieinfo;
import dao.MovieinfoDAO;

public class MovieParser extends InterestParser implements Runnable {

	MovieinfoDAO moviedao;
	// String prefixUrl;
	String url;
	// MyWebDriver driver;
	// BasicinfoDAO bid;
	// int delayTime;

	public MovieParser() {
		super();
		moviedao = new MovieinfoDAO();
	}
	
	public MovieParser(int delayTime) {
		super();
		moviedao = new MovieinfoDAO();
		this.delayTime=delayTime;
	}

	public void parser() {
		// LoginHelper.login(driver);
		List<Basicinfo> list = bid.findByMovieTag(0);
		for (Basicinfo basic : list) {
			url = prefixUrl + basic.getUserName() + "/";
			try {
				driver.getWithDelay(url, delayTime);
				WebElement movieEle = driver.getElement(By.id("movie"));
				if (movieEle != null) {
					List<WebElement> eles = movieEle.findElements(By.xpath("./h2//span[@class='pl']//a"));
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

					ArrayList<Movieinfo> movies = new ArrayList<Movieinfo>();
					for (String key : res.keySet()) {
						Movieinfo movie = new Movieinfo();
						movie.setMovieCategory(key);
						// movie.setBookCategory(key);
						movie.setNum(res.get(key));
						movie.setUserId(basic.getUserName());
						movies.add(movie);
					}
					for (Movieinfo movie : movies) {
						moviedao.save(movie);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				basic.setMovieTag(1);
				bid.merge(basic);
				HibernateSessionFactory.getSession().flush();
			}

		}
		driver.close();
	}

	// public HashMap<String, Integer> parserMovieTag(){
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

	// public void test(String url){
	// LoginHelper.login(driver);
	//// List<Basicinfo> list=bid.findByBookTag(0);
	//// for(Basicinfo basic:list){
	// url = prefixUrl + url + "/";
	// driver.getWithDelay(url, delayTime);
	// WebElement movieEle=driver.getElement(By.id("movie"));
	// if(bookEle!=null){
	// List<WebElement>
	// eles=bookEle.findElements(By.xpath("./h2//span[@class='pl']//a"));
	// ArrayList<String> hrefs=new ArrayList<String>();
	// HashMap<String,Integer> res=new HashMap<String, Integer>();
	// for(WebElement ele:eles){
	// hrefs.add(ele.getAttribute("href"));
	// }
	// for(String href:hrefs){
	// driver.getWithDelay(href, delayTime);
	// HashMap<String,Integer> temp=parserBookTag();
	// for(String key:temp.keySet()){
	// if(res.containsKey(key)){
	// res.put(key, res.get(key)+temp.get(key));
	// }
	// else{
	// res.put(key, temp.get(key));
	// }
	// }
	// }
	//
	// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
	// for(String key:res.keySet()){
	// Bookinfo book=new Bookinfo();
	// book.setBookCategory(key);
	// book.setNum(res.get(key));
	//// book.setUserId(basic.getUserName());
	// books.add(book);
	// }
	//// for(Bookinfo book:books){
	//// bookdao.save(book);
	//// }
	//// basic.setBookTag(1);
	//// bid.merge(basic);
	//// HibernateSessionFactory.getSession().flush();
	// }
	// }

	public static void main(String[] args) {
		MovieParser mp = new MovieParser();
		mp.parser();
	}

	@Override
	public void run() {
		System.out.println("movie");
		parser();
	}

	// public boolean parserMovieTag(MyWebDriver driver, String userId, int
	// type) {
	// ArrayList<Movieinfo> movies = new ArrayList<Movieinfo>();
	// ArrayList<WebElement> eles = (ArrayList<WebElement>) driver
	// .findElements(By.xpath("//li[@class='clearfix']"));
	// for (WebElement ele : eles) {
	// String temp = ele.findElement(By.xpath("./a")).getAttribute("href");
	// Movieinfo movie = new Movieinfo();
	// movie.setMovieId(temp.substring(temp.indexOf("tag=") + 4,
	// temp.indexOf("mode")));
	// movie.setMovieName(ele.findElement(By.xpath("./a")).getText());
	// movie.setRemark(ele.findElement(By.xpath("./span")).getText());
	// movie.setUserId(userId);
	// movie.setType(type);
	// movies.add(movie);
	// }
	//
	// for (Movieinfo m : movies) {
	// moviedao.save(m);
	// }
	//
	// return true;
	//
	// }

}
