package com.web.app.url;

import java.util.*;

public class WorkerThread implements Runnable {
	List<String> result;
	String path;
	String url;
	Set<Integer> statusCodes;

	WorkerThread(List result, String path, String url, Set statusCodes) {
		this.result = result;
		this.path = path;
		this.url = url;
		this.statusCodes = statusCodes;

	}

	@Override
	public void run() {

		try {
			String currentResult = WebUrlStatutsFetch.getStatus(url + "/" + path, statusCodes);
			if (currentResult.length() > 0) {
				result.add(currentResult);
				// System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
