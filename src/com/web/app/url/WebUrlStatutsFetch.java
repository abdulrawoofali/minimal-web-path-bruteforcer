package com.web.app.url;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;

public class WebUrlStatutsFetch {
	
	public static String getStatus(String url,Set<Integer> statusCodes) throws IOException {
		//System.out.println("Loading.....");
		String result = "";
		int code = 200;
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(100);
			connection.connect();
 
			code = connection.getResponseCode();
			//System.out.println(code);
			if (statusCodes.contains(code)){
				
				//System.out.println(code);
				
				result = url + "\t\t[ Status : "+code+"]";
				
				return result;
				
			}
		} catch (Exception e) {
			
			//System.out.println("Connect timed out");
			//.printStackTrace();
			
		}
		//System.out.println(url + "\t\tStatus:" + result);
		return "";
	}
 
}


