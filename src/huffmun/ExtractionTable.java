package huffmun;

import java.util.ArrayList;

public class ExtractionTable {
	public static ArrayList<ArrayList<Integer>> get(ArrayList<Integer> cypher){
		ArrayList<ArrayList<Integer>> table=new ArrayList<>();
		ArrayList<Integer> line=null;
		ArrayList<Integer> arrayOfBytes=null;
		int i=0;
		
		arrayOfBytes=new ArrayList<>();
		arrayOfBytes.add(cypher.get(i++)); 
		arrayOfBytes.add(cypher.get(i++));
		arrayOfBytes.add(cypher.get(i++));
		arrayOfBytes.add(cypher.get(i++));
		int s=ForFile.assembleIntoAnInteger(arrayOfBytes); 
	
		for(int j=1; j<=s; j++){
			line=new ArrayList<>();
			
			arrayOfBytes=new ArrayList<>();
			arrayOfBytes.add(cypher.get(i++)); 
			arrayOfBytes.add(cypher.get(i++));
			arrayOfBytes.add(cypher.get(i++));
			arrayOfBytes.add(cypher.get(i++));
			line.add(ForFile.assembleIntoAnInteger(arrayOfBytes)); 
			
			line.add(cypher.get(i++));
			
			arrayOfBytes=new ArrayList<>();
			arrayOfBytes.add(cypher.get(i++)); 
			arrayOfBytes.add(cypher.get(i++));
			arrayOfBytes.add(cypher.get(i++));
			arrayOfBytes.add(cypher.get(i++));
			line.add(ForFile.assembleIntoAnInteger(arrayOfBytes)); 
			
			table.add(line);
		}
		return table;
	}
}
