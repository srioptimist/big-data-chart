package com.cts.bigdata.snanalytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/attrition")
public class AttritionRestService {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getResults() throws IOException{
	    
	    final List<Indicators> reasonList = new ArrayList<Indicators>();
	    final Map<String, List<String>> primeIndicators = readFile();
	    Iterator<Entry<String,List<String>>> iterator = primeIndicators.entrySet().iterator();
	    while (iterator.hasNext()) {
            Map.Entry<String,List<String>> entry = (Map.Entry<String,List<String>>) iterator.next();
            reasonList.add(new Indicators(entry.getKey(), Integer.valueOf(entry.getValue().size()).toString(), "reasons.html?key="+entry.getKey()+""));
        }
        return Response.status(Response.Status.OK).entity(reasonList).build();
        
    }
	
	@GET
    @Path("/reasons/{indicator}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getResons(@PathParam("indicator") final String indicator) throws IOException{
	    final Map<String, List<String>> primeIndicators = readFile();
	    return Response.status(Response.Status.OK).entity(primeIndicators.get(indicator)).build(); 
	}
	
	public Map<String, List<String>> readFile() throws FileNotFoundException{
	    
	    final Map<String, List<String>> primeIndicators = new HashMap<String, List<String>>();
	    final URL url  = this.getClass().getClassLoader().getResource("Output.txt");
	    final Scanner scan = new Scanner(new File(url.getFile()));

	    while(scan.hasNext()){
	        String curLine = scan.nextLine();
	        String[] splitted = curLine.split("\t");
	        String indicator = splitted[0].trim();
	        String reason = splitted[1].trim();
	        if(primeIndicators.containsKey(indicator)){
	            final List<String> eReasons = primeIndicators.get(indicator);
	                if(!eReasons.contains(reason)){ // to skip duplicate/same reasons
	                    eReasons.add(reason);
	                }
	        }else{
    	        final List<String> reasons = new ArrayList<String>();
    	        reasons.add(reason);
    	        primeIndicators.put(indicator, reasons);
	        }
	    }
	    scan.close();
	    
	    return primeIndicators;
	}

}
