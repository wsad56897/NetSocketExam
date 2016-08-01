package Exam5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class Geta extends Thread {
	 public Geta(String uri) {
	 this.uri = uri;
	 // TODO Auto-generated constructor stub
	 }
	private String uri = "http://files.saas.hand-china.com/java/target.pdf";
	private HttpClient client = HttpClients.createDefault();

	public void run() {

		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			BufferedInputStream bio = new BufferedInputStream(entity
					.getContent());
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File("target.pdf")));
			byte b[] = new byte[1024];
			int len = 0;
			while ((len = bio.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
			bos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
