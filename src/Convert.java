import java.util.ArrayList;

public class Convert{
	public int toIntegers(int a){
		int b=(a<0) ? (256+a) : (a);
		
		return b;
	}
	
	public ArrayList<Integer> toIntegers(ArrayList<Integer> a){
		int b=a.size();
		for(int c=0; c<b; c++){
			int d=a.get(c);
			int e=(d<0) ? (256+d) : (d);
			a.set(c,e);
		}
		
		return a;
	}
	
	public byte toBytes(int a){
		byte b=(byte)a;
		
		return b;
	}
	
	public ArrayList<Integer> toBytes(ArrayList<Integer> a){
		int b=a.size();
		for(int c=0; c<b; c++){
			int d=a.get(c);
			int e=(byte)d;
			a.set(c,e);
		}
		
		return a;
	}
}
