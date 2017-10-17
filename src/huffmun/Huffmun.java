package huffmun;

import java.util.ArrayList;

public class Huffmun {
	public ArrayList<Integer> encoding(ArrayList<Integer> data) {
		ArrayList<int []> a=CreateAnArrayOfCharactes.exe(data);
		ArrayList<ArrayList<Integer>> table=CreateTableHuffmun.exe(a, 0, 0);
		
		ArrayList<Integer> cypher=DataEncoding.exe(table,data);
		
		return cypher;
	}
	
	public ArrayList<Integer> decoding(ArrayList<Integer> cypher) {
		ArrayList<ArrayList<Integer>> table=ExtractionTable.get(cypher);
		ArrayList<Integer> data=DataExtraction.get(table,cypher);
		
		return data;
	}
}

/*

		for(ArrayList<Integer> gg : table){
			System.out.print("chatacter "+(gg.get(0)-512));
			System.out.print(" bits "+gg.get(1));
			int v=gg.get(1);
			System.out.print(" code ");
			for(int w=v; w>=1; w--){
				int mask=(1<<(w-1))&gg.get(2);
				if(mask>0){
					System.out.print("1");
				}else{
					System.out.print("0");
				}
			}
			System.out.println("");
		}

 */



//само число \ количество бит в замен€ющем символе \ массив этих бит
