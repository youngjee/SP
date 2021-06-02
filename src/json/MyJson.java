package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MyJson {
	public static void main(String[] args) {
		JsonElement jsonElement = JsonParser.parseString("{ \"key\":\"value\" }");
		System.out.println(jsonElement.toString());
		
		//Json<-> Object
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		         
		Employee emp = new Employee(1001, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		 
		String jsonString = gson.toJson(emp);
		 
		Employee empObject = gson.fromJson(jsonString, Employee.class);
		System.out.println(empObject.toString());
	}
}