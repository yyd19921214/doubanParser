package test;

import java.util.HashSet;

public class setTest {
	public static void main(String[] args) {
		HashSet<String> set=new HashSet<String>();
		String a="hello".substring(0,2);
		String b="helloooo".substring(0,2);
		set.add(a);
		set.add(b);
		System.out.println(set.size());
	}

}
