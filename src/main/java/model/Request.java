package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

@SuppressWarnings("deprecation")
public class Request {
	private final static String USER_AGENT = "Mozilla/5.0";
	
	//private static final String BASE_URL = Config.getString("SERVER_BASE_URL", "");
	private static final String BASE_URL = "http://10.10.4.10:4567/";
	//private static final String BASE_URL = "http://127.0.0.1:4567/";
	//private static final HttpClient client = new DefaultHttpClient();

	public static void main(String[] a) {
		System.err.println(postSentenceVector("%").length);
	}
	
//	public static double[] getSentenceVector(String sentence) {
//		try {
//			final HttpClient client = new DefaultHttpClient();
//			String encode = URLEncoder.encode(sentence, "UTF-8");
//			String url = BASE_URL+"sentencevector?"+"entity1="+encode;
//			final HttpGet request = new HttpGet(url);
//			final HttpResponse response = client.execute(request);
//			final BufferedReader rd = new BufferedReader(
//					new InputStreamReader(response.getEntity().getContent()));
//			String line = "";
//			if ((line = rd.readLine()) != null) {
//				return fromString(line);
//			}else {
//				return null;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static double[] postSentenceVector(String sentence) {
		try {
			final HttpClient client = new DefaultHttpClient();
			String url = BASE_URL+"sentencevector";
			final HttpPost post = new HttpPost(url);
			post.setHeader("User-Agent", USER_AGENT);
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("entity1", sentence));

			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);

			if(response.getStatusLine().getStatusCode()!=200) {
				return null;
			}
			final BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			
			String line = "";
			if ((line = rd.readLine()) != null) {
				if(line==null || line.equalsIgnoreCase(null)) {
					return null;
				}
				return fromString(line);
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static double[] fromString(String string) {
		String[] strings = string.replace("[", "").replace("]", "").split(", ");
		double result[] = new double[strings.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Double.parseDouble(strings[i]);
		}
		return result;
	}
}