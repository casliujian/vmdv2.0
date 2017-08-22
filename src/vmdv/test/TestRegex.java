package vmdv.test;

import java.nio.charset.Charset;

public class TestRegex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "|- EU(x, y, (safe(x)), (okay(y)), {farmer:=true;wolf:=false;goat:=true;cabbage:=false})";
		System.out.println(str.matches(".*EU.*"));
		String defaultCharsetName=Charset.defaultCharset().displayName();   
        System.out.println("defaultCharsetName:"+defaultCharsetName);  
	}

}
