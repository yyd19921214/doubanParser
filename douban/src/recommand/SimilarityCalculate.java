package recommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimilarityCalculate {

	public SimilarityCalculate() {
		// TODO Auto-generated constructor stub
	}
	
	private static double YUZHI = 0.2 ;

	public static double getSimilarity(ArrayList<String> T1, ArrayList<String> T2){
	    int size = 0 , size2 = 0 ;
	    if ( T1 != null && ( size = T1.size() ) > 0 && T2 != null && ( size2 = T2.size() ) > 0 ) {
	         
	        Map<String, double[]> T = new HashMap<String, double[]>();
	         
	        //T1和T2的并集T
	        String index = null ;
	        for ( int i = 0 ; i < size ; i++ ) {
	            index = T1.get(i) ;
	            if( index != null){
	                double[] c = T.get(index);
	                c = new double[2];
	                c[0] = 1; //T1的语义分数Ci
	                c[1] = YUZHI;//T2的语义分数Ci
	                T.put( index, c );
	            }
	        }
	  
	        for ( int i = 0; i < size2 ; i++ ) {
	            index = T2.get(i) ;
	            if( index != null ){
	                double[] c = T.get( index );
	                if( c != null && c.length == 2 ){
	                    c[1] = 1; //T2中也存在，T2的语义分数=1
	                }else {
	                    c = new double[2];
	                    c[0] = YUZHI; //T1的语义分数Ci
	                    c[1] = 1; //T2的语义分数Ci
	                    T.put( index , c );
	                }
	            }
	        }
	             
	        //开始计算，百分比
	        Iterator<String> it = T.keySet().iterator();
	        double s1 = 0 , s2 = 0, Ssum = 0;  //S1、S2
	        while( it.hasNext() ){
	            double[] c = T.get( it.next() );
	            Ssum += c[0]*c[1];
	            s1 += c[0]*c[0];
	            s2 += c[1]*c[1];
	        }
	        //百分比
	        return Ssum / Math.sqrt( s1*s2 );
	    } else {
	    	
	    	System.out.println("参数有问题");
	    	return 0.0;
//	        throw new Exception("传入参数有问题！");
	    }
	}
}
