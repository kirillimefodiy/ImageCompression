package wavelet;

import java.util.ArrayList;

public class ConversionLine {
	public ArrayList<Integer> set(ArrayList<Integer> line, int a, int b){
		//line - the segment of the array before of conversion
		//a - the segment length of the array
		//b - how many times to divide
		ArrayList<Double> befLine=new ArrayList<>();
		for(int c : line){
			befLine.add((double)c);	//Convert segment to the format double
		}
		ArrayList<Double> aftLine=new ArrayList<>();
		
		int middle=a/2;	
		
		while(a>1){
			for(int e=0; e<middle; e++){
				aftLine.add((befLine.get(e*2)+befLine.get(e*2+1))/2);
			}
			
			for(int e=0; e<middle; e++){
				aftLine.add((befLine.get(e*2)-befLine.get(e*2+1))/2);
			}
			
			befLine=new ArrayList<>();
			befLine=aftLine;
			aftLine=new ArrayList<>();
			
			a/=2;
		}
		
		line=new ArrayList<Integer>();
		for(double c : befLine){
			c/=b;				//Division of a segment in "b" times
			line.add((int)(c));	//Convert segment to the format integer
		}
		
		return line;
	}
	
	public ArrayList<Integer> get(ArrayList<Integer> line, int a, int b){
		//line - the segment of the array before of conversion
		//a - the segment length of the array
		//b - how many times to multiple
		ArrayList<Integer> befLine=new ArrayList<>();
		for(int c : line){
			befLine.add(c*b);	//Multiplication of a segment in "b" times
		}
		ArrayList<Integer> aftLine=new ArrayList<>();
		
		int middle=a/2;
		
		while(a>1){
			for(int e=0; e<middle; e++){
				aftLine.add(befLine.get(e)+befLine.get(e+middle));
				aftLine.add(befLine.get(e)-befLine.get(e+middle));
			}
			
			befLine=new ArrayList<>();
			befLine=aftLine;
			aftLine=new ArrayList<>();
			
			a/=2;
		}
		
		return befLine;
	}
}
