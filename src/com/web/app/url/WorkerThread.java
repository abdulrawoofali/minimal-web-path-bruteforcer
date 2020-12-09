package com.web.app.url;
import java.util.*;

public class WorkerThread implements Runnable {
	List<String> result;
	List pathsList;
	int startIndex;
	int endIndex;
	String url;
	WorkerThread(List result,List pathsList,String url,int startIndex,int endIndex){
		this.result = result;
		this.pathsList = pathsList;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.url = url;
		
	}

	@Override
	public void run() {
	
		for(int i=startIndex;i<endIndex;i++) {
			try {
	    		String currentResult  = WebUrlStatutsFetch.getStatus(url+"/"+pathsList.get(i));
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
