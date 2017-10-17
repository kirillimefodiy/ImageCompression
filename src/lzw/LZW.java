package lzw;

import java.util.ArrayList;

public class LZW {
	public ArrayList<Integer> encoding(ArrayList<Integer> data){
		Shamrock tree=new Shamrock(-1,-1);
		int key=0;
		
		boolean was;
		for(int a : data){
			was=false;			//Was the earlier character
			for(Shamrock sh : tree.getBranch()){
				if(sh.getLeafC()==a){
					was=true;	//The symbol previously was
				}
			}
			if(was==false){	//The symbol was not previously
				tree.addShamrockOnBranch(new Shamrock(key++,a));
							//Add a new shamrock
			}
		}
		//The root table is created
		
		ArrayList<Integer> cypher=new ArrayList<>();
		
		cypher.add((tree.getBranch()).size());	//Writing the array size
		for(Shamrock sh : tree.getBranch()){
			cypher.add(sh.getLeafK());			//Whiting the key 
			cypher.add(sh.getLeafC());			//Writing the character 
		}
		//The root table is written
		
		ArrayList<Integer> map=new ArrayList<>();
		
		int lastKey=0;
		int i=0;
		int s=data.size();
		for(int b : data){
			i++;
				
			map.add(b);				//Path
			boolean d=false;
			int e=tree.isThere(tree, map);	//Returns the key or "-1"
			if(e!=-1){						//If the return key
				lastKey=e;			//Save the last key
				d=true;
			}
			if((d==true)&(i<s)){	//If such a line exists in the table
				continue;
			}else{					//If no such line in the table
				cypher.add(lastKey);	
				
				map.remove(map.size()-1);	//The last symbol is not included in the path		
				tree.addShamrockOnTree(tree,map,new Shamrock(key++,b));	//Write a symbol at the specified address
				
				map=new ArrayList<>();
				map.add(b);
				
				lastKey=tree.isThere(tree, map);	//Save the lastKey for a single character
				
				if(d==false&i==s){	//If this is a last and single character
					cypher.add(tree.isThere(tree, map));	//Add the code for the character
				}	
			}
		}		

		return cypher;
	}
	
	public ArrayList<Integer> decoding(ArrayList<Integer> cypher){
		ArrayList<ArrayList<Integer>> table=new ArrayList<>();
		ArrayList<Integer> line=null;
		
		int irt=0;
		int srt=cypher.get(irt++);
		for(int a=0; a<srt; a++){
			line=new ArrayList<>();
			line.add(cypher.get(irt++));	//The character code
			line.add(cypher.get(irt++));	//A string of characters
			table.add(line);
		}
		
		int key=srt; 
		
		ArrayList<Integer> a=new ArrayList<>();		//The previous line
		ArrayList<Integer> b=new ArrayList<>();		//The current line
		int c=0;
		ArrayList<Integer> data=new ArrayList<>();
		
		int ic=-1;
		for(int code : cypher){
			ic++;
			if(ic<irt){
				continue;
			}
			
			if(code<table.size()){		//If the key was known
				line=new ArrayList<Integer>();
				line=table.get(code);
				
				for(int d=1; d<line.size(); d++){
					data.add(line.get(d));
				}
				c=line.get(1);			//Adding to previous line
				if(ic>irt){
					b=new ArrayList<>();
					b.addAll(a);
					b.add(0,key++);
					b.add(c);			//Creating a new line to table
				}
				a=new ArrayList<>();
				for(int d=1; d<line.size(); d++){	//The creation of previous line
					a.add(line.get(d));
				}
				
				if(ic>irt){				//Ignore the case ic=irt
					table.add(b);		//The entry of new line to table
				}
			}else{						//If the key was not known (it is repetition with the addition)
				b=new ArrayList<>();
				b.addAll(a);
				b.add(a.get(0));		//Creating a new line to table (Instead of "b.add(c);")
				data.addAll(b);
				
				a=new ArrayList<>();
				a.addAll(b);
				
				b.add(0,key++);
				table.add(b);			//The entry of new line to table
			}
		}

		return data;
	}
}

/*
package lzw;

import java.util.ArrayList;

public class LZW {
	public ArrayList<Integer> encoding(ArrayList<Integer> data){
		ArrayList<ArrayList<Integer>> table=new ArrayList<>();
		int key=0;
		
		boolean was;
		for(int a : data){
			was=false;			//Was the earlier character
			for(ArrayList<Integer> line : table){
				if(a==line.get(1)){
					was=true;	//The symbol previously was
				}
			}
			if(was==false){		//The symbol was not previously
				ArrayList<Integer> line=new ArrayList<>();
				line.add(0,key++);	//The character code
				line.add(1,a);		//A string of characters
				table.add(line);
			}
		}
		//The root table is created
		
		ArrayList<Integer> cypher=new ArrayList<>();
		
		cypher.add(table.size()); 
		for(ArrayList<Integer> line : table){
			cypher.add(line.get(0));
			cypher.add(line.get(1));
		}
		//The root table is written
		
		ArrayList<Integer> anArrayOfMatches=new ArrayList<>();
		
		int lastKey=0;
		int i=0;
		int s=data.size();
		for(int b : data){
			i++;
				System.out.print(b+" ");
			anArrayOfMatches.add(b); 
			boolean d=false;
			for(ArrayList<Integer> line : table){
				if(anArrayOfMatches.size()==(line.size()-1)){
					boolean e=true;
					for(int c=1; c<line.size(); c++){
						if(anArrayOfMatches.get(c-1)!=line.get(c)){
							e=false;//There is a mismatch in current row
						}
					}
					if(e==true){ 	//If there is no mismatch in current line
						d=true;		//If the current string of characters meet before
						lastKey=line.get(0);
					}
				}
			}
			if((d==true)&(i<s)){
				continue;
			}else{
				cypher.add(lastKey);	System.out.print(" ("+lastKey+") ");
				
				ArrayList<Integer> line=new ArrayList<>();
				line.add(key++);	//Entry new character code
				for(int e : anArrayOfMatches){
					line.add(e);	//Writing new string of characters
				}
				table.add(line);	//The entry resulting row into the table
					System.out.println("["+(key-1)+"]-");
				anArrayOfMatches=new ArrayList<>();
				anArrayOfMatches.add(b); System.out.print(b+" ");
				
				if(d==false&i==s){
					for(ArrayList<Integer> lineOneSymbol : table){	 
						if(lineOneSymbol.size()==2){
							if(b==lineOneSymbol.get(1)){
								cypher.add(lineOneSymbol.get(0)); System.out.print("("+lineOneSymbol.get(0)+")");
							}
						}
					}
				}
				
				for(ArrayList<Integer> lineOneSymbol : table){	 
					if(lineOneSymbol.size()==2){
						if(b==lineOneSymbol.get(1)){
							lastKey=lineOneSymbol.get(0);	//Create a lastKey for a single character
							if(d==false&i==s){
								cypher.add(lastKey);		//Enter lastKey for a single character						
							}
						}
					}
				}
			}
		}
		
		return cypher;
	}
	
	public ArrayList<Integer> decoding(ArrayList<Integer> cypher){
		ArrayList<ArrayList<Integer>> table=new ArrayList<>();
		int irt=0;
		int srt=cypher.get(irt++);
		for(int a=0; a<srt; a++){
			ArrayList<Integer> line=new ArrayList<>();
			line.add(cypher.get(irt++));	//The character code
			line.add(cypher.get(irt++));	//A string of characters
			table.add(line);
		}
		
		int key=srt; 
		
		ArrayList<Integer> a=new ArrayList<>();
		ArrayList<Integer> b=new ArrayList<>();
		int c=0;
		ArrayList<Integer> data=new ArrayList<>();
		
		int ic=-1;
		for(int code : cypher){
			ic++;
			if(ic<irt){
				continue;
			}
			
			ArrayList<Integer> line=null;
			boolean found=false;
			for(int it=0; it<table.size(); it++){
				line=new ArrayList<Integer>();
				line=table.get(it);
				
				if(code==line.get(0)){
					found=true;
					for(int d=1; d<line.size(); d++){
						data.add(line.get(d));
					}
					c=line.get(1);
					if(ic>irt){
						b=new ArrayList<>();
						b.addAll(a);
						b.add(0,key++);
						b.add(c);
					}
					a=new ArrayList<>();
					for(int d=1; d<line.size(); d++){
						a.add(line.get(d));
					}
					break;
				}		
			}
			if(found==false){
				b=new ArrayList<>();
				b.addAll(a);
				b.add(a.get(0));
				data.addAll(b);
				
				a=new ArrayList<>();
				a.addAll(b);
				
				b.add(0,key++);
				table.add(b);
			}
			
			if(ic>irt){
				table.add(b);
			}
		}

		return data;
	}
}
 */
