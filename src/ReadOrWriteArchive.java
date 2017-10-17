import java.util.ArrayList;

public class ReadOrWriteArchive {
	public void set(ArrayList<Integer> a, ArrayList<Integer> b){
		a.addAll(b);
		new ReadOrWriteBytesImage().set(a,"i.dat");
	}
	
	public ArrayList<ArrayList<Integer>> get(){
		ArrayList<Integer> a=new ReadOrWriteBytesImage().get("i.dat");
		ArrayList<Integer> bitmapfileheader=new ArrayList<Integer>();
		ArrayList<Integer> rgb=new ArrayList<Integer>();
		int i=0;
		for(int j : a){
			if(i<54){
				bitmapfileheader.add(j);
				i++;
				continue;
			}
			if(i>=54){
				rgb.add(j);
				i++;
				continue;
			}
		}
		
		ArrayList<ArrayList<Integer>> b=new ArrayList<>();
		b.add(bitmapfileheader);
		b.add(rgb);
		
		return b;
	}
}
