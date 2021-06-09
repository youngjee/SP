package http.client;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

public class MyClient {
	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
//		ContentResponse contentRes = httpClient.newRequest("http://127.0.0.1:8080/mypath").method(HttpMethod.GET)
//				.send();
//		System.out.println(contentRes.getContentAsString());
//		
		Request request = httpClient.newRequest("http://127.0.0.1:8080/mypath").method(HttpMethod.POST);
		
		//URI query param
		request.param("param", "hellllloooooooooooo");
		
		//for Header
		request.header("key", "value");
		
		//request.attribute for in jvm exchange
		request.attribute("param", "attributeAddeeeddd");
		request.attribute("param1", "aatribbsdlifjxdfijsldifjslifj");
		
		//Set form fields for Post Body (application/x-www-form-urlencoded body)
		//Fields fields = new Fields();
                //fields.put("fruit", "apple");
                //request.content(new FormContentProvider(fields));

		//Set form multipart upload Post Body (multipart/form-data)
		//MultiPartContentProvider multiPart = new MultiPartContentProvider();
		//multiPart.addFieldPart("fruit", new StringContentProvider("apple"), null);
		//multiPart.close();
                //request.content(multiPart);
		
		// Set request body for json
                // request.content(new StringContentProvider(JSON_HERE), "application/json");
		
		// Set text/xml for body
		//request.content(new ByteBufferContentProvider("text/xml", ByteBuffer.wrap(SOMTHING)));
		
		ContentResponse contentRes = request.send();
		System.out.println(contentRes.getContentAsString());
		
	}
}
