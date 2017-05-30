package vmdv.test;
import org.json.*;

public class TestJson {

	
	public static void main(String[] args) {
		String str = "{\"username\": \"your name\", \"user_json\": {\"username\": \"your name\", \"nickname\": \"your nickname\"}}";
		JSONObject json = new JSONObject(str);
		json.accumulate("hello", "world");
		JSONObject json2 = new JSONObject();
		json2.accumulate("hello2", "world2");
		json.accumulate("hello_2", json2);
		System.out.println(json.get("username"));
		System.out.println(json.toString());
	}

}
