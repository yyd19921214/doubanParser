package parser;

import dao.BasicinfoDAO;
import dao.ParserconfigDAO;
import hibernateSession.HibernateSessionFactory;
import utils.DriverFactory2;
import utils.MyWebDriver;

public abstract class AbstractParser implements ParserImp {
	
	String prefixUrl;
	MyWebDriver driver;
	BasicinfoDAO bid;
	int delayTime;
	volatile String preUrl; //该变量用来保存之前一次的url值，如果两次url相同则认为程序已经停止爬取数据了

	public AbstractParser() {
		driver=DriverFactory2.getDriverTextOnly(true);
		bid=new BasicinfoDAO();
		delayTime=3;
		preUrl=null;
	}
	
	
	
	
	public int getDelayTime() {
		return delayTime;
	}




	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}




	public boolean isStop(){
		if(preUrl==null){
			preUrl=driver.getCurrentUrl();
			return false;
		}
		else{
			if(!preUrl.equals(driver.getCurrentUrl())){
				preUrl=driver.getCurrentUrl();
//				System.out.println(preUrl);
				return false;
			}
			else{
				return true;
			}
		}
			
	}
	
	
//	public void restart(){
////		System.out.println("线程被重启了");
//		driver=DriverFactory2.getDriverTextOnly(true);
//		preUrl=null;
//		parser();
//	}
	
	
	public void stopParser(){
		HibernateSessionFactory.getSession().flush();
		System.out.println("爬虫被关闭了");
		driver.close();
		preUrl=null;
	}
	
	
	
	
	
	
	

}
