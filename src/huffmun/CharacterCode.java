package huffmun;

public class CharacterCode {
	public static int[] set(int a, int b, int c){
		//a - character code
		//b - the number of bits in the character
		//c - the number of free bits in the current byte
		int d[]=new int[10];
		int e=2;
		//d[0] - (the number of bytes in the mask)-1
		//d[1] - the number of free bits in the last byte
		//d[2..] - mask
		
		
		while(b>0){
			if(b>c){
				d[e]|=((a<<(32-b))>>>(32-b))>>>(b-c);	//Cutting bits and their shift to the right
				b-=c;
				c=8;
				
				e++;
				continue;
			}
			if(b==c){
				d[e]|=((a<<(32-b))>>>(32-b));			//Cutting bits without shifting
				c=8;
				b=0;
				
				d[0]=e-2;
				d[1]=c;
				continue;
			}
			if(b<c){
				d[e]|=((a<<(32-b))>>>(32-b))<<(c-b);	//Cutting bits and shifting them to the left
				c-=b;
				b=0;
				
				d[0]=e-2;
				d[1]=c;
				continue;
			}
		}
		
		return d;
	}
	
	public static int get(int [] a, int b, int c){
		//a[0..] - character code
		//b - the number of bits in the character
		//c - the number of free bits in the current byte
		int d=0;
		int e=0;
		//e - character
		
		while(b>0){
			if(b>c){
				e|=((a[d]<<(32-c))>>>(32-c))<<(b-c);	//Cutting bits and shifting them to the left
				b-=c;
				c=8;
				d++;
				continue;
			}
			if(b==c){
				e|=((a[d]<<(32-c))>>>(32-c));			//Cutting bits without shifting
				b=0;
				
				continue;
			}
			if(b<c){
				e|=((a[d]<<(32-c))>>>(32-c))>>>(c-b);	//Cutting bits and their shift to the right
				b=0;
				
				continue;
			}
		}
	
		return e;
	}
	
}
