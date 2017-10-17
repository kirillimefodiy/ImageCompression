package huffmun;

import java.util.ArrayList;

public class ForFile {
	public static ArrayList<Integer> splitIntoBytes(int a){
		ArrayList<Integer> b=new ArrayList<Integer>();
		b.add(a>>>24);
		b.add((a<<8)>>>24);
		b.add((a<<16)>>>24);
		b.add((a<<24)>>>24);

		return b;
	}
	
	public static ArrayList<Integer> splitForHuffmun(ArrayList<Integer> a){
		ArrayList<Integer> b=new ArrayList<Integer>();
		for(int c : a){
			b.addAll(splitIntoBytes(c));
		}
		return b;
	}
	
	public static int assembleIntoAnInteger(ArrayList<Integer> a){
		int b=0;
		b|=(a.get(0)<<24);
		b|=(a.get(1)<<16);
		b|=(a.get(2)<<8);
		b|=(a.get(3));

		return b;
	}
	
	public static ArrayList<Integer> assembleForHuffmun(ArrayList<Integer> a){
		ArrayList<Integer> b=new ArrayList<Integer>();
		
		ArrayList<Integer> c=new ArrayList<>();
		int i=0;
		for(int e : a){
			i++;
			c.add(e);
			if(i==4){
				b.add(assembleIntoAnInteger(c));
				i=0;
				c=new ArrayList<>();
			}
		}
		
		return b;
	}
}
