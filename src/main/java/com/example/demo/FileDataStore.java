package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.springframework.stereotype.Component;

/**
 * FileDataStore is an implementation to use file system as persistent store
 */
@Component
public class FileDataStore implements DataStore {
	
	static Map<String, Boolean> cacheMap = new HashMap<String, Boolean>();
	static final String FILE_PATH = "cache.txt";

	/**
	 * Writes to the input and palindrome check to the file
	 * @param value
	 * @param isPalindrome
	 */
	@Override
	public void SaveToDataStore(String value, boolean isPalindrome) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(FILE_PATH, true));
			System.out.println("trying to write to file");
			out.write(value + "=" + isPalindrome);
			out.newLine();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}finally {
			if(null != out) {
				try {
					out.close();
				}catch(IOException ex){
					System.out.println("COULD NOT LOG!!");
				}
			}
		}
	}

	/**
	 * This method is used for loading cache by getting the data from file system
	 * @return
	 */
	@Override
	public Map<String, Boolean> getDataForCache() {
		
		try (Scanner scanner = new Scanner(new FileReader(FILE_PATH))) {

			String line;

			while (scanner.hasNext()) {
				line = scanner.nextLine();
				if (!line.isEmpty()) {
					String[] columns = line.split("=");

					cacheMap.put(columns[0], Boolean.parseBoolean(columns[1]));
				}

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("unable to load the cache");
		}
		return cacheMap;
	}

}
