package com.chrisreading.coveis.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.chrisreading.coveis.model.Item;

/**
 * Utils for file use
 */
public class FileUtils {

	/**
	 * Get files from a directory
	 * @param dir Directory 
	 * @return File array
	 */
	public static List<File> getFiles(File dir) {
		List<File> results = new ArrayList<File>();
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				results.add(files[i]);
			}
		}
		
		return results;
	}
	
}
