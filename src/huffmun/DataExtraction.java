package huffmun;

import java.util.ArrayList;

public class DataExtraction {
	public static ArrayList<Integer> get(ArrayList<ArrayList<Integer>> table, ArrayList<Integer> cypher){
		ArrayList<Integer> data=new ArrayList<>();
		int i=3+table.size()*9+1;
		int j=cypher.size()-1;
		
		ArrayList<Integer> arrayOfBytes=new ArrayList<>();
		arrayOfBytes.add(cypher.get(i++)); 
		arrayOfBytes.add(cypher.get(i++));
		arrayOfBytes.add(cypher.get(i++));
		arrayOfBytes.add(cypher.get(i++));
		int s=ForFile.assembleIntoAnInteger(arrayOfBytes);
		
		int[] a=new int[10];
		int b;
		
		int c;		//Character
		int d=0;	//The number of bits in the character
		int e=8;	//The number of free bits in the current byte
		
		for(int k=1; k<=s; k++){
			if(j-i>=5){
				for(b=0; b<5; b++){
					a[b]=cypher.get(i+b);
				}
				d=32;
			}
			if(j-i<5){
				for(b=0; b<=(j-i); b++){
					a[b]=cypher.get(i+b);
				}
				d=(b==5) ? (32) : (b*8);
			}
			c=CharacterCode.get(a, d, e);
			
			for(ArrayList<Integer> line : table){
				if((c>>>(d-line.get(1)))==line.get(2)){ 
					data.add(line.get(0));
			
					if(line.get(1)>e){
						i+=(line.get(1)-e)/8+1;
						e=8-(line.get(1)-e)%8;
						
						break;
					}
					if(line.get(1)==e){
						i++;
						e=8;
						
						break;
					}
					if(line.get(1)<e){
						e-=line.get(1);
						
						break;
					}
				}
			}
		}
		
		return data;
	}
}
