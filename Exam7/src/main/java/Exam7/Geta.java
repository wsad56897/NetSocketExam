package Exam7;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exam7.entity.Date;

public class Geta extends Thread{
	private HttpClient client = HttpClients.createDefault();
	private String uri;
	private String value;

	public Geta(String uri) {
		this.uri = uri;
	}

	public void run() {
		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			value = EntityUtils.toString(entity, "UTF-8");
			String s[] = value.split(",");
			Date d = new Date();
			d.setName(s[0].substring(s[0].indexOf("\"") + 1));
			d.setOpen(s[1]);
			d.setClose(s[2]);
			d.setCurent(s[3]);
			d.setHigh(s[4]);
			d.setLow(s[5]);
			getJSON(d);
			getXML(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getJSON(Date d) throws Exception {
		JSONObject json = JSONObject.fromObject(d);
		String date = json.toString();
		File f = new File("date.json");
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(f));
		bos.write(date.getBytes());
		bos.flush();
		bos.close();
	}

	public void getXML(Date d) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dbf.newDocumentBuilder();
		Document doc = build.newDocument();
		Element stock = doc.createElement("stock");
		Element name = doc.createElement("name");
		name.setTextContent(d.getName());
		Element open = doc.createElement("open");
		open.setTextContent(d.getOpen());
		Element close = doc.createElement("close");
		close.setTextContent(d.getClose());
		Element current = doc.createElement("current");
		current.setTextContent(d.getCurent());
		Element high = doc.createElement("high");
		high.setTextContent(d.getHigh());
		Element low = doc.createElement("low");
		low.setTextContent(d.getLow());
		stock.appendChild(name);
		stock.appendChild(open);
		stock.appendChild(close);
		stock.appendChild(current);
		stock.appendChild(high);
		stock.appendChild(low);
		doc.appendChild(stock);
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		StringWriter sw = new StringWriter();
		tf.transform(new DOMSource(doc), new StreamResult(sw));
		File f = new File("date.xml");
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(f));
		bos.write(sw.toString().getBytes());
		bos.flush();
		bos.close();
	}
}
