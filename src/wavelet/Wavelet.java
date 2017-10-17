package wavelet;

import java.util.ArrayList;

public class Wavelet {
	public ArrayList<Integer> encoding(ArrayList<Integer> data, int a, int b){
		//data - the array to convert
		//a - the segment length of the array
		//b - how many times to divide
		ArrayList<Integer> cypher=new ArrayList<>();
		int s=data.size();
		cypher.add(s%a);
		cypher.add(a);
		cypher.add(b);
		
		int theSummOfTheWholeArray=s/a;
		int lenghtOfIncompleteArray=s%a;
		
		ArrayList<Integer> line=new ArrayList<>();
		
		int arrayNumber=1;
		int elementNumber=1;
		
		for(int c : data){
			//Parsing array into segments
			if(arrayNumber<=theSummOfTheWholeArray){
				if(elementNumber<a){
					line.add(c);
					
					elementNumber++;
					
					continue;
				}
				if(elementNumber==a){
					line.add(c);
					cypher.addAll(new ConversionLine().set(line, a, b));
	
					arrayNumber++;
					elementNumber=1;
					line=new ArrayList<Integer>();
					
					continue;
				}
			}
			if(arrayNumber>theSummOfTheWholeArray){
				if(lenghtOfIncompleteArray==0){
					break;
				}
				
				if(elementNumber<lenghtOfIncompleteArray){
					line.add(c);
					
					elementNumber++;
					
					continue;
				}
				if(elementNumber==lenghtOfIncompleteArray){
					line.add(c);
					for(int d=1; d<=(a-lenghtOfIncompleteArray); d++){
					//Adding zeros to a standard size segment
						line.add(0);
					}
					cypher.addAll(new ConversionLine().set(line, a, b));
					
					continue;
				}
			}
		}
		
		return cypher;
	}
	
	public ArrayList<Integer> decoding(ArrayList<Integer> cypher){
		//data - the array after conversion
		//a - the segment length of the array
		//b - how many times to divide
		ArrayList<Integer> data=new ArrayList<>();
		int i=0;
		int s=cypher.get(i++);
		int a=cypher.get(i++);
		int b=cypher.get(i++);
		
		int theSummOfTheWholeArray=(cypher.size()-3-s)/a;
		int lenghtOfIncompleteArray=s;
		
		ArrayList<Integer> line=new ArrayList<>();
		
		int arrayNumber=1;
		int elementNumber=1;
		
		for(;;){
			//The assembly of the segments into an array
			if(arrayNumber<=theSummOfTheWholeArray){
				if(elementNumber<a){
					line.add(cypher.get(i++));
					
					elementNumber++;
					
					continue;
				}
				if(elementNumber==a){
					line.add(cypher.get(i++));
					data.addAll(new ConversionLine().get(line, a, b));
					
					arrayNumber++;
					elementNumber=1;
					line=new ArrayList<Integer>();
					
					continue;
				}
			}
			if(arrayNumber>theSummOfTheWholeArray){
				if(lenghtOfIncompleteArray==0){
					break;
				}
				
				if(elementNumber<a){
					line.add(cypher.get(i++));
					
					elementNumber++;
					
					continue;
				}
				if(elementNumber==a){
					line.add(cypher.get(i++));
					line=new ConversionLine().get(line, a, b);
					
					for(elementNumber=1; elementNumber<=lenghtOfIncompleteArray; elementNumber++){
						//Deletion of zeros to the original segment size
						data.add(line.get(elementNumber-1));
					}	
					
					break;
				}
			}
		}
		
		return data;
	}
}
