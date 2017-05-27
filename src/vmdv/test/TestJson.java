package vmdv.test;
import org.json.*;

public class TestJson {

	
	public static void main(String[] args) {
		String str = "[{\"username\": \"your name\", \"user_json\": {\"username\": \"your name\", \"nickname\": \"your nickname\"}}]";
		JSONArray json = new JSONArray(str);
		System.out.println(json.toString());
	}

}
