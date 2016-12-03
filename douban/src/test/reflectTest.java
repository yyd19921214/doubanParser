package test;

import java.lang.reflect.Field;

import parser.AbstractParser;
import parser.MovieParser;
import utils.ParserThread;

public class reflectTest {

	public reflectTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ParserThread pt=new ParserThread(new MovieParser(6));
		try {
			Class apc=pt.getParser().getClass().getSuperclass();
			Field[] fs = apc.getDeclaredFields(); 
			for(int i=0;i<fs.length;i++){
				Field f = fs[i];  
		           f.setAccessible(true); //设置些属性是可以访问的  
		           Object val = f.get(pt.getParser().getClass().getSuperclass());//得到此属性的值     
		           System.out.println("name:"+f.getName()+"\t value = "+val);  
			}
//			AbstractParser ap=(AbstractParser) Class.forName(pt.getParser().getClass().getName()).newInstance();
//			System.out.println(pt.getParser().getClass().getField("delayTime"));;
//			System.out.println(Class.forName(pt.getParser().getClass().getName()).newInstance().getClass().getName());
//			System.out.println("here");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(pt.getParser().getClass().getName());
	}
}
