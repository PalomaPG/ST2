package test;
import suffixtree.*;

public class STTesting {

	public static void test1(){
		String t = "xyzxyaxy";
		SuffixTree st = new SuffixTree(t);
		st.ukkonen();
		st.printST(st.getRoot());
	}
	public static void main(String [] args){
		test1();
	}
}
