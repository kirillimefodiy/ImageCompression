import java.util.ArrayList;
import java.io.*;

public class ReadOrWriteBytesImage {
	public ArrayList<Integer> get(String path){
	//Read bytes from the image
		int buf=127;
		byte[] bytesReaded=new byte[buf];
		ArrayList<Integer> image=new ArrayList<>();
		FileInputStream fr=null;
		
		try{
			fr=new FileInputStream(path);
			int i=buf;
			do{
				i=fr.read(bytesReaded);
				for(int j=0; j<i; j++){
					image.add((int)bytesReaded[j]);
				}
			}while(i==buf);
		
			return image;
			
		}catch(IOException exc){
			System.out.println("Error reading from file");
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				System.out.println("Error when closing file");
			}
		}
		
		return image;
	}
	
	public void set(ArrayList<Integer> image,String path){
	//Write bytes image
		FileOutputStream fw=null;
		
		try{
			fw=new FileOutputStream(path,false);
			for(int i : image){
				fw.write(i);
			}
			
		}catch(IOException exc){
			System.out.println("Error whriting to file");
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
				System.out.println("Error when closing file");
			}
		}
	}
}

