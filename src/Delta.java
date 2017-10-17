import java.util.ArrayList;

public class Delta {
	public ArrayList<Integer> encoding(ArrayList<Integer> a, int w, int h){		
		int wb=w*3;
		
		int i=0;
		for(int j=1; j<=h; j++){
			for(int k=1; k<wb; k++){				
				a.set(i,(a.get(i)-a.get(i+1)));		
				i++;
			}
			i++;
		}
		
		i=0;
		for(int j=1; j<h; j++){
			for(int k=1; k<=(wb); k++){
				a.set(i,(a.get(i)-a.get(i+wb)));
				i++;
			}
		}
			
		return a;
	}
	
	public ArrayList<Integer> decoding(ArrayList<Integer> a, int w, int h){
		int wb=w*3;
		
		int i=wb*h-wb-1;
		for(int j=h-1; j>=1; j--){
			for(int k=wb; k>=1; k--){
				a.set(i,(a.get(i)+a.get(i+wb)));
				i--;
			}
		}
		
		i=wb*h-1;;
		for(int j=h; j>=1; j--){
			i--;
			for(int k=wb-1; k>=1; k--){				
				a.set(i,(a.get(i)+a.get(i+1)));		
				i--;
			}
		}
		
		return a;
	}
}
