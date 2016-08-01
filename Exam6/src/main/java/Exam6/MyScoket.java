package Exam6;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MyScoket {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 10022);
		BufferedInputStream bis= new BufferedInputStream(new FileInputStream(new File("../Exam5/target.pdf")));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("target.pdf")));
		
		byte b[] = new byte[1024];
		int len = 0;
		while((len=bis.read(b))!=-1){
			bos.write(b, 0, len);
		}
		bos.flush();
		bos.close();	
	}
}
