package lzw;

import java.util.ArrayList;

public class Shamrock {
	public int k;
	public int c;
	public ArrayList<Shamrock> a=new ArrayList<>();
	
	Shamrock(){}
	Shamrock(int k, int c){
		this.k=k;
		this.c=c;
		this.a=new ArrayList<Shamrock>();
	}
	
	public void setLeafK(int k){this.k=k;}
	public void setLeafC(int c){this.c=c;}
	public void setBranch(ArrayList<Shamrock> a){this.a=a;}
	
	public int getLeafK(){return k;}
	public int getLeafC(){return c;}
	public ArrayList<Shamrock> getBranch(){return a;}
	
	public void addShamrockOnBranch(Shamrock sn){
		a.add(sn);
	}
	public Shamrock getShamrockOnBranch(int i){
		return a.get(i);
	}
	
	public Shamrock addShamrockOnTree(Shamrock parent, ArrayList<Integer> ac, Shamrock sn){
		if(ac.size()==0){
			parent.a.add(sn);
		}else{
			int i=0;
			for(Shamrock child : parent.getBranch()){
				if(child.getLeafC()==ac.get(0)){ 
					ac.remove(0);
					parent.a.set(i,(addShamrockOnTree(child,ac,sn)));
					break;
				}
				i++;
			}
		}
		
		return parent;
	}
	public int isThere(Shamrock parent, ArrayList<Integer> ac){
		int x=-1;
		
		boolean d=false;		
		for(int i=0; i<ac.size(); i++){
			d=false;			
			for(Shamrock child : parent.getBranch()){
				if(child.getLeafC()==ac.get(i)){
					d=true;		
					
					parent=child;
					x=child.getLeafK();
				
					break;
				}
			}
			if(d==false){
				break;
			}
		}
		
		if(d==false){
			x=-1;
		}
		
		return x;
	}
}
