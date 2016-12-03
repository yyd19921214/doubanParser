package recommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IterableMap;
import org.apache.james.mime4j.io.MimeBoundaryInputStream;

import dao.Basicinfo;
import dao.BasicinfoDAO;
import dao.Bookinfo;
import dao.BookinfoDAO;
import dao.Focusinfo;
import dao.FocusinfoDAO;
import dao.Groupinfo;
import dao.GroupinfoDAO;
import dao.Likeinfo;
import dao.LikeinfoDAO;
import dao.Minsiteinfo;
import dao.MinsiteinfoDAO;
import dao.MovieinfoDAO;
import dao.Musicinfo;
import dao.Movieinfo;
import dao.MusicinfoDAO;
import hibernateSession.HibernateSessionFactory;
import utils.MapComparator;

/**
 * 根据用户的所加入的小组来推荐可能的好友
 * @author yangyudong
 *
 */
public class RecommandHelper {
	
	BasicinfoDAO bid;
	GroupinfoDAO gid;
	MinsiteinfoDAO mid;
	FocusinfoDAO fid;
	BookinfoDAO bookdao;
	MusicinfoDAO musicdao;
	MovieinfoDAO moviedao;
	LikeinfoDAO likedao;
	

	public RecommandHelper() {
		bid=new BasicinfoDAO();
		gid=new GroupinfoDAO();
		mid=new MinsiteinfoDAO();
		fid=new FocusinfoDAO();
		bookdao=new BookinfoDAO();
		musicdao=new MusicinfoDAO();
		moviedao=new MovieinfoDAO();
		likedao=new LikeinfoDAO();
	}
	
	
	public double getRecommandByGroup(String userName1,String userName2){
		List<Groupinfo> gList=gid.findByPersonId(userName1);
		List<Groupinfo> gList2=gid.findByPersonId(userName2);
//		String sql1="SELECT groupName from groupinfo where personId="+userName1;
		List<String> gNameList=new ArrayList<>(gList.size());
		List<String> gNameList2=new ArrayList<>(gList2.size());
		for(Groupinfo g:gList){
			gNameList.add(g.getGroupName());
		}
		for(Groupinfo g:gList2){
			gNameList2.add(g.getGroupName());
		}
		double div=Math.sqrt(gNameList.size()*gNameList2.size());
		if((div==0.0)){
			return 0.0;
		}
		else{
			gNameList.retainAll(gNameList2);
			return ((gNameList.size()+0.0)/div);
		}
		
		
		
	}
	
	
	public double getRecommandByFocusInfo(String userName1,String userName2){
		List<Focusinfo> fList=fid.findBySrcId(userName1);
		List<Focusinfo> fList2=fid.findBySrcId(userName2);
		List<String> mNameList=new ArrayList<>(fList.size());
		List<String> mNameList2=new ArrayList<>(fList2.size());
		
		for(Focusinfo f:fList){
			mNameList.add(f.getSrcId());
		}
		for(Focusinfo f:fList2){
			mNameList2.add(f.getSrcId());
		}
		double div=Math.sqrt(mNameList.size()*mNameList2.size());
		if((div==0.0)){
			return 0.0;
		}
		else{
			mNameList.retainAll(mNameList2);
			return ((mNameList.size()+0.0)/div);
		}
	}
	
	
	public double getRecommandByInterestInfo(String userName1,String userName2){
		return getBookScore(userName1, userName2)+getMovieScore(userName1, userName2)+getMusicScore(userName1, userName2);
	}
	
	/**
	 * 根据喜欢的信息做推荐
	 * @param userName1
	 * @param userName2
	 * @return
	 */
	public double getRecommandTFIDF(String userName1,String userName2){
		List<Likeinfo> likeList=likedao.findByUserId(userName1);
		List<Likeinfo> likeList2=likedao.findByUserId(userName2);
		ArrayList<String> title1=new ArrayList<>(likeList.size());
		ArrayList<String> title2=new ArrayList<>(likeList2.size());
		for(Likeinfo like:likeList){
			title1.add(like.getTitle());
		}
		for(Likeinfo like:likeList2){
			title2.add(like.getTitle());
		}
		ArrayList<ArrayList<String>> token1=new ArrayList<>(title1.size());
		ArrayList<ArrayList<String>> token2=new ArrayList<>(title2.size());
		for(String s:title1){
			ArrayList<String> temp1=Tokenization.tokenText(s);
			token1.add(temp1);
		}
		for(String s:title2){
			ArrayList<String> temp2=Tokenization.tokenText(s);
			token2.add(temp2);
		}
		double totalScore=0.0;
		for(ArrayList<String> list:token1){
			double score=0.0;
			double temp;
			for(ArrayList<String> list2:token2){
				temp=SimilarityCalculate.getSimilarity(list, list2);
				if(temp>score){
					score=temp;
				}
			}
			totalScore+=score;
		}
		
		
		if(totalScore!=0.0){
			return Math.log10(totalScore)/20;
		}
		else{
			return 0.0;
		}
	}
	
	
	private double getBookScore(String userName1,String userName2){
		List<Bookinfo> bookList1=bookdao.findByUserId(userName1);
		List<Bookinfo> bookList2=bookdao.findByUserId(userName2);
		if(bookList1.size()!=0&&bookList2.size()!=0){
			HashMap<String,Integer> map1=new HashMap<>();
			HashMap<String,Integer> map2=new HashMap<>();
			for(int i=0;i<bookList1.size();i++){
				map1.put(bookList1.get(i).getBookCategory(),bookList1.get(i).getNum());
			}
			for(int i=0;i<bookList2.size();i++){
				map2.put(bookList2.get(i).getBookCategory(),bookList2.get(i).getNum());
			}
			return processInterestMap(map1, map2);
		}
		return 0.0;
	}
	
	
	private double getMusicScore(String userName1,String userName2){
		List<Musicinfo> musicList1=musicdao.findByUserId(userName1);
		List<Musicinfo> musicList2=musicdao.findByUserId(userName2);
		if(musicList1.size()!=0&&musicList2.size()!=0){
			HashMap<String,Integer> map1=new HashMap<>();
			HashMap<String,Integer> map2=new HashMap<>();
			for(int i=0;i<musicList1.size();i++){
				map1.put(musicList1.get(i).getMusicCategory(),musicList1.get(i).getNum());
			}
			for(int i=0;i<musicList2.size();i++){
				map2.put(musicList2.get(i).getMusicCategory(),musicList2.get(i).getNum());
			}
			return processInterestMap(map1, map2);
			
		}
		return 0.0;
	}
	
	private double getMovieScore(String userName1,String userName2){
		List<Movieinfo> movieList1=moviedao.findByUserId(userName1);
		List<Movieinfo> movieList2=moviedao.findByUserId(userName2);
		if(movieList1.size()!=0&&movieList2.size()!=0){
			HashMap<String,Integer> map1=new HashMap<>();
			HashMap<String,Integer> map2=new HashMap<>();
			for(int i=0;i<movieList1.size();i++){
				map1.put(movieList1.get(i).getMovieCategory(),movieList1.get(i).getNum());
			}
			for(int i=0;i<movieList2.size();i++){
				map2.put(movieList2.get(i).getMovieCategory(),movieList2.get(i).getNum());
			}
			return processInterestMap(map1, map2);
			
		}
		return 0.0;
	}
	
	
	
	private double processInterestMap(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
		double score = 0.0;
		ArrayList<String> res;// 用来暂时保存分词结果
		int count1 = 0;
		int count2 = 0;
		String key;
		int num;
		Iterator<HashMap.Entry<String, Integer>> it = map1.entrySet().iterator();
		HashMap<String, Integer> temp = new HashMap<>();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			key = entry.getKey();
			num = entry.getValue();
			res = Tokenization.tokenText(key);
			for (String s : res) {
				temp.put(s, num);
				count1 += num;
			}
			it.remove();
		}
		map1.putAll(temp);
		temp.clear();

		it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			key = entry.getKey();
			num = entry.getValue();
			res = Tokenization.tokenText(key);
			for (String s : res) {
				if (temp.containsKey(s)) {
					temp.put(s, temp.get(s) + num);
				} else {
					temp.put(s, num);
				}
				count2 += num;
			}
			it.remove();
		}
		map2.putAll(temp);
		temp.clear();

		Set<String> keys1 = map1.keySet();
		Set<String> keys2 = map2.keySet();
		keys1.retainAll(keys2);
		int div = 0;// 分子
		for (String k : keys1) {
			div += map1.get(k) <= map2.get(k) ? map1.get(k) : map2.get(k);
		}
		score = (div + 0.0) / Math.sqrt(count1 * count2);
		return score;

	}
	
	public HashSet<String> getRecommandFocusPerson(String userName){
		List<Focusinfo> list=fid.findBySrcId(userName);
		if(list.size()==0){
			System.out.println("他没有朋友");
			return null;
		}
		else{
			int size=list.size();
			HashMap<String,Integer> map=new HashMap<>();
			HashSet<String> set=new HashSet<>();
			for(Focusinfo f:list){
				set.add(f.getDesId());
			}
			ArrayList<List<Focusinfo>> setList=new ArrayList<>();
			
			for(String srcId:set){
				List<Focusinfo> temp=fid.findBySrcId(srcId);
				if(temp.size()!=0){
					setList.add(temp);
				}
			}
			for(int t=0;t<setList.size();t++){
				for(int j=0;j<setList.get(t).size();j++){
					if(map.containsKey(setList.get(t).get(j).getDesId())){
						map.put(setList.get(t).get(j).getDesId(), map.get(setList.get(t).get(j).getDesId())+1);
					}
					else{
						map.put(setList.get(t).get(j).getDesId(),1);
					}
				}
			}
			
			HashSet<String> res=new HashSet<>();
			for(String key:map.keySet()){
				if(map.get(key)>4&&!key.equals(userName)){
					res.add(key);
				}
			}
			return res;
		}
		
	}
	
//	public ArrayList<String> getRecommandRevLinkPerson(String userName){
//		List<Focusinfo> temp1=fid.findBySrcId(userName);
//		List<Focusinfo> temp2=fid.findByDesId(userName);
//		temp2.retainAll(temp1);
//		if(temp2.isEmpty()){
//			System.out.println("该用户交际圈太过狭窄，暂时无法为他推荐好友");
//			return null;
//		}
//		else{
//			if(temp2.size()<10){
//				ArrayList<String> t1=new ArrayList<>()
//				for(Focusinfo f:temp2){
//					
//				}
////				return temp2;
//			}
//		}
//		
//	}
	
	public HashSet<String> getPerson(String userName){
		Basicinfo basic=(Basicinfo) bid.findByUserName(userName).get(0);
		int id=basic.getId();
		int low=1<(id-50)?id-50:1;
		int high=id+50;
		String hql=String.format("FROM Basicinfo  WHERE id<%d AND id>%d ",high,low);
		
		HibernateSessionFactory.getSession().getTransaction().begin();
		List<Basicinfo> basics=HibernateSessionFactory.getSession().createQuery(hql).list();
		HibernateSessionFactory.getSession().getTransaction().commit();
		
		ArrayList<HashMap<String,Double>> res=new ArrayList<>(basics.size());
		for(Basicinfo b:basics){
			HashMap<String,Double> map=new HashMap<>();
			String uName=b.getUserName();
			
			double weight=getRecommandByFocusInfo(userName, uName)+getRecommandByGroup(userName, uName)+getRecommandByInterestInfo(userName, uName)+getRecommandTFIDF(userName, uName);
			map.put(uName, weight);
			res.add(map);
		}
		
		Collections.sort(res, new MapComparator());
		
		HashSet<String> finalSet=new HashSet<>();
		for (int i=0;i<10;i++) {
			HashMap<String,Double> m=res.get(i);
            for (Map.Entry<String, Double> en : m.entrySet()) {
            	finalSet.add(en.getKey());
//                System.out.println(en.getKey() + " , " + en.getValue());
            }
        }
//		System.out.println(finalSet.size());
//		sortByWeight(res);
		return finalSet;

	}
	
	
	private void sortByWeight(){
		
	}
	
	
	
	public static void main(String[] args) {
		RecommandHelper rg=new RecommandHelper();
//		System.out.println(rg.getRecommandByInterestInfo("85108403", "gujiatang"));
//		System.out.println(rg.getBookScore("85108403", "gujiatang"));
//		System.out.println(rg.getMovieScore("127916851", "6905933"));
//		System.out.println(rg.getRecommandTFIDF("Heyches", "swanfirst"));
//		rg.getRecommandFocusPerson("62026140");
		Date d1=new Date();
		System.out.println(d1.toString());
		HashSet<String> res=rg.getPerson("62026140");
		Date d2=new Date();
		System.out.println(d2.toString());
//		System.out.println();
//		for(String str:res){
//			System.out.println(str);
//		}
//		ArrayList<HashMap<String,Double>> res=new ArrayList<>();
//		HashMap<String, Double> map1=new HashMap<>();
//		map1.put("hello", 1.0);
//		HashMap<String, Double> map2=new HashMap<>();
//		map2.put("world", 2.0);
//		HashMap<String, Double> map3=new HashMap<>();
//		map3.put("everyone", 1.5);
//		
//		res.add(map1);
//		res.add(map2);
//		res.add(map3);
//		Collections.sort(res, new MapComparator());
////		Collections.sort(list, new TestComparator());
//        for (HashMap<String, Double> m : res) {
//            for (Map.Entry<String, Double> en : m.entrySet()) {
//                System.out.println(en.getKey() + " , " + en.getValue());
//            }
//        }
		
		
		
	}
	
	
	
	

}
