package com.web.app.url;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

	public static void main(String[] args) {

		int chunckSize = 40;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of webUrls.....");
		
		int totalUrls = sc.nextInt();
		
		System.out.println("Eneter your urls....");
		//https://www.github.com
		

		String urlContainer[] = new String[totalUrls];
		int i = 0;

		while (i < totalUrls) {
			urlContainer[i] = sc.next();
			i++;
		}

		System.out.println("Give you local path's file path");
		// C:\\Users\\abdul\\OneDrive\\Desktop\\webUrlFiles\pathList.txt

		String pathListFile = sc.next();

		List pathsList = readFileInList(pathListFile);
		
		

		List<String> outPutUrlStatus = Collections.synchronizedList(new ArrayList<String>());

		ExecutorService executor = Executors.newFixedThreadPool(9);

		int pathListSize = pathsList.size();

		System.out.println("Total Paths from file ->  " +pathListSize);
		System.out.println("Running brute force......on all paths.....");

		for (String url : urlContainer) {
			
			for (int j = 0; j < pathListSize; j += chunckSize) {
				int endIndex = pathListSize - (chunckSize + j) > 0 ? (chunckSize + j) : pathListSize;
				ThreadWorker worker = new ThreadWorker(outPutUrlStatus, pathsList,url, j, endIndex);
				executor.execute(worker);

			}
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		
		System.out.println("Finished all threads");
		
		
		System.out.println("Total Url Withs Status [302,200] -> "+ outPutUrlStatus.size()+"\n\n");

		for (String urlStatuts : outPutUrlStatus) {

			System.out.println(urlStatuts);

		}

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
