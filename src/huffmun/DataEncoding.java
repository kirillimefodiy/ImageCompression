package huffmun;

import java.util.ArrayList;

public class DataEncoding {
	public static ArrayList<Integer> exe(ArrayList<ArrayList<Integer>> table, 
		ArrayList<Integer> data){
		ArrayList<Integer> tableOneDimension=new ArrayList<>();
		ArrayList<Integer> dataHuffmun=new ArrayList<>();

		int c=0;	//The number of bits in the character code
		int d=0;	//The character code
		int e=-1;	//The number of the current cell
		int f=8;	//The number of free bits in the current cell
		for(int a : data){
			for(ArrayList<Integer> b : table){
				if(b.get(0)==a){
					c=b.get(1);		//The number of bits
					d=b.get(2);		//The character code
					break;
				}
			}
			
			int [] g=new int[10];
			g=CharacterCode.set(d, c, f);
			for(int h=0; h<=g[0]; h++){
				if(h==0){
					if(f==8){
						dataHuffmun.add(0);
						e++;
					}
				}
				if(h>0){
					dataHuffmun.add(0);
					e++;
				}
				
				dataHuffmun.set(e,dataHuffmun.get(e)|g[2+h]); 
			}
			f=g[1];
		}
		
		
		for(ArrayList<Integer> line : table){
			ArrayList<Integer> arrayOfBytes=new ArrayList<>();
			arrayOfBytes.addAll(ForFile.splitIntoBytes(line.get(0)));
			arrayOfBytes.add(line.get(1));
			arrayOfBytes.addAll(ForFile.splitIntoBytes(line.get(2)));
			
			tableOneDimension.addAll(arrayOfBytes);
		}

		tableOneDimension.addAll(0,ForFile.splitIntoBytes(table.size()));
		dataHuffmun.addAll(0,ForFile.splitIntoBytes(data.size()));
		
		tableOneDimension.addAll(dataHuffmun);

		return tableOneDimension;
	}
}

/*test\/
int yy=0;
for(int xx : dataHuffmun){
	if(yy==0){
		System.out.println("number bodyHuffmun"+xx);
	}
	if(yy>0){
		for(int w=8; w>=1; w--){
			int mask=(1<<(w-1))&xx;
			if(mask>0){
				System.out.print("1");
			}else{
				System.out.print("0");
			}
		}
		System.out.println("");
	}
	yy++;
}
*/

