package com.web.app.url;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;

public class WebUrlStatutsFetch {
	
	public static String getStatus(String url) throws IOException {
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
			if (code == 200 || code == 302) {
				
				//System.out.println(code);
				
				result = url + "\t\t[ Status : "+code+"]";
				
				return result;
				
			}
			else {
				//System.out.println(code);
				return "";
			}
		} catch (Exception e) {
			
			//System.out.println("Connect timed out");
			//.printStackTrace();
			
		}
		//System.out.println(url + "\t\tStatus:" + result);
		return result;
	}
 
}


