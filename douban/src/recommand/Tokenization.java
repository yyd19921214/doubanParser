package recommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 * 该类用来对语料进行分词处理
 * @author yangyudong
 *
 */
public class Tokenization {

	
	public static final String stopWordTable = "cn_stopword.txt"; 
	public static Set<String> stopWordSet = new HashSet<String>();
	 
	public Tokenization() {
		// TODO Auto-generated constructor stub
	}
	
	static{
		
		BufferedReader StopWordFileBr;
		try{
			StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopWordTable))));  
	        
	        String stopWord = null;  
	        for(; (stopWord = StopWordFileBr.readLine()) != null;){  
	            stopWordSet.add(stopWord);  
	        }
	        StopWordFileBr.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//将文本进行切分
	public static ArrayList<String> tokenText(String text){
		ArrayList<String> res=new ArrayList<>();
		Analyzer anal=new IKAnalyzer(true);       
        StringReader reader=new StringReader(text);  
        //分词  
        TokenStream ts;
		try {
			ts = anal.tokenStream("", reader);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			// 遍历分词数据
			while (ts.incrementToken()) {
				if (!stopWordSet.contains(term.toString())) {
					res.add(term.toString());
				}
			}
//			String[] arrres=new String[res.size()];
//			for(int i=0;i<res.size();i++){
//				arrres[i]=res.get(i);
//			}
			
			
			reader.close();
			return res;
//			return arrres;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
//	public static void main(String[] args) {
//		Set<String> stopWordSet = new HashSet<String>();  
//		try{
//			BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopWordTable))));  
//	        
//	        String stopWord = null;  
//	        for(; (stopWord = StopWordFileBr.readLine()) != null;){  
//	            stopWordSet.add(stopWord);  
//	        }
//	        
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//        
//		
////		 String text="基于java语言开发的轻量级的中文分词工具包";  
//		 String text="1. 计算机或相关专业专科以上学历 2. 1年以上的J2EE开发工作经验，精通面向对象设计思想，具有扎实的Java编程功底和良好的编码规范 3. 精通项目开发过程中的各个阶段的任务及其输出物 4. 熟悉Spring、SpringMVC、JSF、Hibernate、MyBatic等框架 4. 熟悉Tomcat、nginx、apache等多种应用和WEB服务器的配置 有丰富的数据库设计经验，精通sql语言，并熟悉Mysql数据库系统 熟悉HTML语言、JavaScript脚本语言、XML语言 ，了解当前流行的javascript开源框架。 工作主动性强，耐心细致，有责任心；能承受一定压力，有良好的沟通能力与团队合作精神。 有互联网金融（p2p）行业工作经验这优先考虑。";  
//	        //创建分词对象  
//	        Analyzer anal=new IKAnalyzer(true);       
//	        StringReader reader=new StringReader(text);  
//	        //分词  
//	        TokenStream ts;
//			try {
//				ts = anal.tokenStream("", reader);
//				 CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
//			        //遍历分词数据  
//			        while(ts.incrementToken()){  
//			        	if(!stopWordSet.contains(term.toString())){
//			        		System.out.print(term.toString()+"|");  
//			        		
//			        	}
//			            
//			        }  
//			        reader.close();  
//			        System.out.println(); 
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  
////	        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
////	        //遍历分词数据  
////	        while(ts.incrementToken()){  
////	            System.out.print(term.toString()+"|");  
////	        }  
////	        reader.close();  
////	        System.out.println(); 
//	}

}
