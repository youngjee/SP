package http.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpClientExample {
	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpClientExample http = new HttpClientExample();

		System.out.println("GET으로 데이터 가져오기");
		http.sendGet("https://www.naver.com");

		System.out.println("POST로 데이터 가져오기");
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		http.sendPost("https://www.google.co.kr/", urlParameters);
	}

	// HTTP GET request
	private void sendGet(String targetUrl) throws Exception {

		URL url = new URL(targetUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET"); // optional default is GET
		con.setRequestProperty("User-Agent", USER_AGENT); // add request header

		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println("HTTP 응답 코드 : " + responseCode);
		System.out.println("HTTP body : " + response.toString());
	}

	// HTTP POST request
	private void sendPost(String targetUrl, String parameters) throws Exception {

		URL url = new URL(targetUrl);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("POST"); // HTTP POST 메소드 설정
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setDoOutput(true); // POST 파라미터 전달을 위한 설정

		// Send post request
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println("HTTP 응답 코드 : " + responseCode);
		System.out.println("HTTP body : " + response.toString());

	}
	
	
}
