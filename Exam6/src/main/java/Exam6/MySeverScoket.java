package Exam6;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;




public class MySeverScoket {
	public static void main(String[] args) throws IOException {
		final ServerSocket server = new ServerSocket(10022);
		while(true){
		 Socket s = server.accept();
		 
		JOptionPane.showMessageDialog(null, "有客户端接入");
		}
	}
}
