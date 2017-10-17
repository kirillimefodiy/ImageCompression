import java.util.ArrayList;

public class WidthHeight {
	public int getWidth(ArrayList<Integer> image){
		//The width of the image
		int w1=new Convert().toIntegers(image.get(18));
		int w2=new Convert().toIntegers(image.get(19));
		int w=w2*256+w1; 
		
		return w;
	}
	
	public int getHeight(ArrayList<Integer> image){
		//The height of the image
		int h1=new Convert().toIntegers(image.get(22));
		int h2=new Convert().toIntegers(image.get(23));
		int h=h2*256+h1; 
		
		return h;
	}
	
	public int getExtra(ArrayList<Integer> image){
		//Extra zeros in a row
		int w1=new Convert().toIntegers(image.get(18));
		int w2=new Convert().toIntegers(image.get(19));
		int w=w2*256+w1; 
		int wrgb=w*3;
		int wz=(wrgb%4==0) ? (0) : (4-wrgb%4);
		
		return wz;
	}
}
