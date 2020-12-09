package com.web.app.url;
import java.util.*;

public class WorkerThread implements Runnable {
	List<String> result;
	List pathsList;
	int startIndex;
	int endIndex;
	String url;
	Set<Integer> statusCodes;
	WorkerThread(List result,List pathsList,String url,int startIndex,int endIndex,Set statusCodes){
		this.result = result;
		this.pathsList = pathsList;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.url = url;
		this.statusCodes = statusCodes;
		
	}


	@Override
	public void run() {
	
		for(int i=startIndex;i<endIndex;i++) {
			try {
	    		String currentResult  = WebUrlStatutsFetch.getStatus(url+"/"+pathsList.get(i),statusCodes);
	    		if(currentResult.length()>0) {
	    			result.add(currentResult);
	    			//System.out.println(result);
	    		}
	    		
	    	}
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
			
		}
	}
	


}
