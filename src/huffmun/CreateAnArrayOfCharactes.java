package huffmun;

import java.util.ArrayList;

public class CreateAnArrayOfCharactes {
	public static ArrayList<int []> exe(ArrayList<Integer> a) {
		//Create sorted array of characters and they numbers
		
		//Create an array
		ArrayList<int[]> d=new ArrayList<>();
		int[] e=null;
		boolean f=false;
		for(int c : a){
			f=false;
			for(int g=0; g<d.size(); g++){
				e=new int[2];
				e=d.get(g);
				if(e[0]==c){	//Character
					e[1]++;		//Number
					d.set(g,e);
					f=true;
				}
			}
			if(f==false){
				e=new int[2];
				e[0]=c;			//Character
				e[1]=1;			//Number
				d.add(e);
			}
		}
	
		//Sorting an array bubble
		int[] lValue=new int[2];
		int[] rValue=new int[2];	
		
		boolean changes;
		while(true){
			changes=false;
			for(int g=0; g<d.size()-1; g++){ 
				lValue=d.get(g);	
				rValue=d.get(g+1);	 
				if(lValue[1]<rValue[1]){
					d.set(g, rValue);
					d.set(g+1, lValue);
					changes=true;
				}
			}
			if(changes==false) break;
		}
	
		return d;
	}
}

/*
	public static ArrayList<int []> exe(ArrayList<Integer> a) {
		//Create sorted array of characters and they numbers
		
		//The creation of one-dimensional array
		int[] b=new int[512+1054];
		for(int c : a){
			c+=512;
			b[c]++;
		}
		
		//The creation of two-dimensional array
		ArrayList<int []> d=new ArrayList<int []>();	
		int[] i;
		int e=-1;
		for(int f=0; f<(512+1054); f++){
			if(b[f]>0){
				i=new int[2];
				i[0]=f;			//Character
				i[1]=b[f];		//Number
				e++;			//Lines
				d.add(e,i); 
			}
		}
	
		//Bubble sorting a two-dimensional array
		int[] lValue=new int[2];
		int[] rValue=new int[2];	
		if(e>0){
			boolean changes;
			while(true){
				changes=false;
				for(int g=0; g<e; g++){ 
					lValue=d.get(g);	
					rValue=d.get(g+1);	 
					if(lValue[1]<rValue[1]){
						d.set(g, rValue);
						d.set(g+1, lValue);
						changes=true;
					}
				}
				if(changes==false) break;
			}
		}
		
		return d;
	}
*/
