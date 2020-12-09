package com.web.app.url;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

	public static void main(String[] args) {

		Set<Integer> statusCodes = new HashSet<>();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of webUrls.....");

		int totalUrls = sc.nextInt();

		System.out.println("Enter your webUrls....");
		// https://www.github.com

		String urlContainer[] = new String[totalUrls];
		int i = 0;

		while (i < totalUrls) {
			urlContainer[i] = sc.next();
			i++;
		}

		System.out.println("Give you local path's file path");
		// C:\\Users\\abdul\\OneDrive\\Desktop\\webUrlFiles\pathList.txt

		String pathListFile = sc.next();

		System.out.println("number of status code's to track...");
		int numberOfStatutsCodesToMonitor = sc.nextInt();
		System.out.println("Enter your status codes..");

		i = 0;
		while (i < numberOfStatutsCodesToMonitor) {
			statusCodes.add(sc.nextInt());
			i++;
		}

		List pathsList = readFileInList(pathListFile);

		List<String> outputContainer = Collections.synchronizedList(new ArrayList<String>());

		int pathListSize = pathsList.size();

		//Preparing  thread pool...
		ExecutorService executor = Executors.newFixedThreadPool(8);

		System.out.println("Total Paths from file ->  " + pathListSize);
		System.out.println("Running brute force......on all paths.....");

		for (String url : urlContainer) {

			for (int j = 0; j < pathListSize; j++) {
				WorkerThread worker = new WorkerThread(outputContainer, (String) pathsList.get(j), url, statusCodes);
				executor.execute(worker);

			}
		}

		executor.shutdown();
		int prevCount =0;
		while (!executor.isTerminated()) {
			if(prevCount < outputContainer.size()){
				prevCount = outputContainer.size();

			}
			System.out.println("getting status.....!");
			System.out.println("Urls matching with status Code"+statusCodes+"  = "+prevCount);
		}

		System.out.println("Finished all threads");

		System.out.println("Total Urls With Status" + statusCodes + "-> " + outputContainer.size() + "\n\n");

		for (String urlAndStatus : outputContainer) {

			System.out.println(urlAndStatus);

		}
		return;

	}

	public static List<String> readFileInList(String fileName) {

		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
