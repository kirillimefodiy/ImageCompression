import java.util.ArrayList;

public class SplittingFileIntoSegments {
	public ArrayList<Integer> getBITMAPFILEHEADER(ArrayList<Integer> image){
		//Create an array header
		ArrayList<Integer> bitmapfileheader=new ArrayList<Integer>();
		for(int i=0; i<54; i++){
			bitmapfileheader.add(image.get(i));
		}
		return bitmapfileheader;
	}
	
	public ArrayList<Integer> getColorPixels(ArrayList<Integer> image, 
												   int w, int h, int z){
		//Create an array of color pixels
		ArrayList<Integer> rgb=new ArrayList<Integer>();
		ArrayList<Integer> r=new ArrayList<Integer>();
		ArrayList<Integer> g=new ArrayList<Integer>();
		ArrayList<Integer> b=new ArrayList<Integer>();
		
		int i=54;
		for(int j=1; j<=h; j++){
			for(int k=1; k<=w; k++){
				r.add(image.get(i++));
				g.add(image.get(i++));
				b.add(image.get(i++));
			}
			i+=z;
		}
		
		for(int l : r){
			rgb.add(l);
		}
		for(int l : g){
			rgb.add(l);
		}
		for(int l : b){
			rgb.add(l);
		}
		return rgb;
	}
	
	public ArrayList<Integer> set(ArrayList<Integer> bitmapfileheader, 
										 ArrayList<Integer> rgb, 
										 int w, int h, int z){
		//The assembly of the file from segments
		ArrayList<Integer> image=new ArrayList<Integer>();
		
		for(int k : bitmapfileheader){
			image.add(k);
		}
		
		int r=0;
		int g=h*w;
		int b=h*w*2;
		
		for(int i=1; i<=h; i++){
			for(int j=1; j<=w; j++){
				image.add(rgb.get(r++));
				image.add(rgb.get(g++));
				image.add(rgb.get(b++));
			}
			for(int j=1; j<=z; j++){
				image.add(0);
			}
		}
		
		return image;
	}
}
