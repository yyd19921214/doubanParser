package control;

import java.util.ArrayList;
import java.util.List;

import dao.Musicinfo;
import parser.BookParser;
import parser.DouListParser;
import parser.FocusInfoParser;
import parser.GroupInfoParser;
import parser.LikeInfoParser;
import parser.MinSiteParser;
import parser.MovieParser;
import parser.MusicParser;
import parser.RevLinkParser;
import utils.MonitorHelper;
import utils.ParserThread;

public class ParserControl {
	
	List<ParserThread>  parserThreads;
	MonitorHelper mh;

	public ParserControl() {
		parserThreads=new ArrayList<>();
//		parserThreads.add(new ParserThread(new MovieParser(6)));
		parserThreads.add(new ParserThread(new BookParser(7)));
		parserThreads.add(new ParserThread(new LikeInfoParser(5)));
		parserThreads.add(new ParserThread(new RevLinkParser(3)));
		mh=new MonitorHelper(parserThreads);
		// TODO Auto-generated constructor stub
	}
	
	
	public void startParser(){
		for(ParserThread t:parserThreads){
			t.start();
//			try{
//				Thread.sleep(3000);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
		}
		mh.init();
	}
	
	public static void main(String[] args) {
//		ParserThread pt=new ParserThread(new MovieParser());
//		System.out.println(pt.getParser().getClass().getName());
		ParserControl pc=new ParserControl();
		pc.startParser();
	}

}
