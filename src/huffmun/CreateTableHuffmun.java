package huffmun;

import java.util.ArrayList;

public class CreateTableHuffmun {
	public static ArrayList<ArrayList<Integer>> exe(ArrayList<int []> a, int bits, int code){
		//a - an array of characters, and their quantities
		//bits - for recursion, - the number of bits in the mask
		//code - for recursion, - the current code mask
		long c=0L;	//The sum of the number
		for(int[] d : a){
			c+=d[1];
		}	
		
		long e=c;	//The current residual of the quantities
		int zC=-1;	//The number of lines in the block of zero
		int oC=-1;	//The number of lines in the block of one
		ArrayList<int []> zA=new ArrayList<int []>();	//The array of quantities on the brunch of zeros
		ArrayList<int []> oA=new ArrayList<int []>();	//The array of quantities on the brunch of ones
		int[] f;
		for(int[] g : a){
			f=new int[2];
			f[0]=g[0];
			f[1]=g[1];
			if((e*2)>c){	//If it is more than half of the array
				zC++;
				zA.add(zC,f);
			}
			if((e*2)<=c){	//If it is less or equal than half of the array
				oC++;
				oA.add(oC,f);
			}
			e-=g[1];
		}
		
		ArrayList<ArrayList<Integer>> zH=new ArrayList<>();
		ArrayList<ArrayList<Integer>> oH=new ArrayList<>();
		
		bits++;
		
		if(zC==0){	//If array of zeros, there is one row
			ArrayList<Integer> zHln=new ArrayList<>();
			int[] i=new int[2];
			i[0]=zA.get(0)[0];
			zHln.add(0,i[0]);	//Character
			zHln.add(1,bits);	//The number of bits in the character
			
			int reverseCode=0;					//The reverse character code
			for(int x=1,y=bits; x<=bits&y>=1; x++,y--){
				int z=(0b00000001<<(x-1))&code;
				if(z>0){
					reverseCode|=(1<<(bits-1))>>>(x-1);
				}
			}
			
			zHln.add(2,reverseCode);	//|int character|int bits|int reverseCode|

			zH.add(zHln);
		}
		if(zC>0){	//If array of zeros has many rows
			zH=CreateTableHuffmun.exe(zA, bits, code);
		}
		if(oC==0){	//If array one has one line
			ArrayList<Integer> oHln=new ArrayList<>();
			int[] i=new int[2];
			i[0]=oA.get(0)[0];
			oHln.add(0,i[0]); //Character
			oHln.add(1,bits);	//The number of bits in the character
			code|=(0b000000001 << (bits-1));	//The character code
			
			int reverseCode=0;					//The reverse character code
			for(int x=1,y=bits; x<=bits&y>=1; x++,y--){
				int z=(0b00000001<<(x-1))&code;
				if(z>0){
					reverseCode|=(1<<(bits-1))>>>(x-1);
				}
			}
			
			oHln.add(2,reverseCode);

			oH.add(oHln);
		}
		if(oC>0){	//If array one has many rows
			code|=(0b000000001 << (bits-1));
			oH=CreateTableHuffmun.exe(oA, bits, code);
		}
		
		zH.addAll(oH);
			
		return zH;
	}
}

/*
	public static ArrayList<ArrayList<Integer>> exe(ArrayList<int []> a, int bits, int code){
		//a - an array of characters, and their quantities
		//bits - for recursion, - the number of bits in the mask
		//code - for recursion, - the current code mask
		long c=0L;	//The sum of the number
		for(int[] d : a){
			c+=d[1];
		}	
		
		long e=c;	//The current residual of the quantities
		int zC=-1;	//The number of lines in the block of zero
		int oC=-1;	//The number of lines in the block of one
		ArrayList<int []> zA=new ArrayList<int []>();	//The array of quantities on the brunch of zeros
		ArrayList<int []> oA=new ArrayList<int []>();	//The array of quantities on the brunch of ones
		int[] f;
		for(int[] g : a){
			f=new int[2];
			f[0]=g[0];
			f[1]=g[1];
			if((e*2)>c){	//If it is more than half of the array
				zC++;
				zA.add(zC,f);
			}
			if((e*2)<=c){	//If it is less or equal than half of the array
				oC++;
				oA.add(oC,f);
			}
			e-=g[1];
		}
		
		ArrayList<ArrayList<Integer>> zH=new ArrayList<>();
		ArrayList<ArrayList<Integer>> oH=new ArrayList<>();
		
		bits++;
		
		if(zC==0){	//If array of zeros, there is one row
			ArrayList<Integer> zHln=new ArrayList<>();
			int[] i=new int[2];
			i[0]=zA.get(0)[0];
			zHln.add(0,i[0]-512);	//Character
			zHln.add(1,bits);	//The number of bits in the character
			
			int reverseCode=0;					//The reverse character code
			for(int x=1,y=bits; x<=bits&y>=1; x++,y--){
				int z=(0b00000001<<(x-1))&code;
				if(z>0){
					reverseCode|=(1<<(bits-1))>>>(x-1);
				}
			}
			
			zHln.add(2,reverseCode);	//|int character|int bits|int reverseCode|

			zH.add(zHln);
		}
		if(zC>0){	//If array of zeros has many rows
			zH=CreateTableHuffmun.exe(zA, bits, code);
		}
		if(oC==0){	//If array one has one line
			ArrayList<Integer> oHln=new ArrayList<>();
			int[] i=new int[2];
			i[0]=oA.get(0)[0];
			oHln.add(0,i[0]-512); //Character
			oHln.add(1,bits);	//The number of bits in the character
			code|=(0b000000001 << (bits-1));	//The character code
			
			int reverseCode=0;					//The reverse character code
			for(int x=1,y=bits; x<=bits&y>=1; x++,y--){
				int z=(0b00000001<<(x-1))&code;
				if(z>0){
					reverseCode|=(1<<(bits-1))>>>(x-1);
				}
			}
			
			oHln.add(2,reverseCode);

			oH.add(oHln);
		}
		if(oC>0){	//If array one has many rows
			code|=(0b000000001 << (bits-1));
			oH=CreateTableHuffmun.exe(oA, bits, code);
		}
		
		zH.addAll(oH);
			
		return zH;
	}
 */

