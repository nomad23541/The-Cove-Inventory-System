package com.chrisreading.coveis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Logs into an ftp server and checks if there is a newer version 
 * of the program, downloads, replaces, and restarts.
 */
public class CoveUpdater {
	
	private final String URL = "http://chrisreading.net/projects/cove-inventory-system/";
	private boolean update = 
	
	public CoveUpdater() throws IOException {
		List<String> files = new ArrayList<String>();
		List<String> potentials = new ArrayList<String>();
		
		System.out.println("Checking for updates...");
		Document doc = Jsoup.connect(URL).get();
		for(Element file : doc.select("ul li a")) {
			files.add(file.attr("href"));
		}
		
		files.remove("/projects/"); // remove stupid a href
		
		double curVersion = Vars.APP_VERSION;
		// now scan file names
		for(String file : files) {
			double version = Double.parseDouble(file.substring(file.lastIndexOf('-') + 1).replace(".jar", ""));
			if(version > curVersion) {
				System.out.println("Found newer version: " + file);
				potentials.add(file);
			}
		}
		
		// notify of update found, if update request accepted, update
		
		
		System.out.println("No updates");
	}
	
}
