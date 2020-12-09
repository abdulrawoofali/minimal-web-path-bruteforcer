package com.web.app.url;

import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;

public class WebUrlStatutsFetch {

	public static String getStatus(String url, Set<Integer> statusCodes) throws IOException {
		// System.out.println("Loading.....");
		String result = "";
		int code = 200;
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(1000);
			connection.connect();

			code = connection.getResponseCode();
			if (statusCodes.contains(code)) {

				result = url + "\t\t[ Status : " + code + "]";

			}
		} catch (Exception e) {

			// System.out.println("Connect timed out");
			// e.printStackTrace();

		}
		return result;
	}

}
