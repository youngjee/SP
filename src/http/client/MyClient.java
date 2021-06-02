package http.client;

public class MyClient {
	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		ContentResponse contentRes = httpClient.newRequest("http://127.0.0.1:8080/helloworld").method(HttpMethod.GET)
				.send();
		System.out.println(contentRes.getContentAsString());
	}
}