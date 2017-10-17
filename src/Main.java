import java.util.ArrayList;
import huffmun.*;
import lzw.*;
import wavelet.*;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> image=null;
		image=new ReadOrWriteBytesImage().get("i.bmp");
		//Read bytes from the image
		
		int w=new WidthHeight().getWidth(image);	//The width of the image
		int h=new WidthHeight().getHeight(image);	//The height of the image
		int z=new WidthHeight().getExtra(image);	//Extra zeros in a row
		
		ArrayList<Integer> bitmapfileheader=new SplittingFileIntoSegments().getBITMAPFILEHEADER(image);
		//Create an array header
		ArrayList<Integer> rgb=new SplittingFileIntoSegments().getColorPixels(image,w,h,z);
		//Create an array of color pixels
		rgb=new Convert().toIntegers(rgb);
		
		System.out.println(rgb.size());	
		
		//rgb=new Delta().encoding(rgb, w, h);	
		//System.out.println("Delta   "+rgb.size());			
		
		rgb=new Wavelet().encoding(rgb, 4, 32);	
		System.out.println("Wavelet "+rgb.size()+" ("+rgb.size()*4+")");	
			
		rgb=new LZW().encoding(rgb);
		System.out.println("lZW     "+rgb.size()+" ("+rgb.size()*4+")");
			
		rgb=new ForFile().splitForHuffmun(rgb);
		System.out.println("ForFile "+rgb.size());
		
		rgb=new Huffmun().encoding(rgb);	
		System.out.println("Huffmun "+rgb.size());	

		new ReadOrWriteArchive().set(bitmapfileheader,rgb);
		//The entry in the archive file
		
		ArrayList<ArrayList<Integer>> archive=new ReadOrWriteArchive().get();
		bitmapfileheader=archive.get(0);
		rgb=archive.get(1);
		//Read from archive file
		
		rgb=new Convert().toIntegers(rgb);		//Because of the conversion to significant type when reading
		
		rgb=new Huffmun().decoding(rgb);	
		System.out.println("Huffmun "+rgb.size());	
		
		rgb=new ForFile().assembleForHuffmun(rgb);
		System.out.println("ForFile "+rgb.size());
		
		rgb=new LZW().decoding(rgb);
		System.out.println("lZW     "+rgb.size());	
		
		rgb=new Wavelet().decoding(rgb);
		System.out.println("Wavelet "+rgb.size());
		
		//rgb=new Delta().decoding(rgb, w, h);	
		//System.out.println("Delta   "+rgb.size());
		
		rgb=new Convert().toBytes(rgb);
		
		image=new SplittingFileIntoSegments().set(bitmapfileheader,rgb,w,h,z);
		//The assembly of the file from segments
		new ReadOrWriteBytesImage().set(image,"i(2).bmp");
		//Write bytes image
		
		System.out.println("The end");
	}
}
