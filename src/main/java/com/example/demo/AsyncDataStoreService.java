package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncDataStoreService provides a non blocking asynchronous service to write to the persistent store.
 */
@Service
public class AsyncDataStoreService {
	
	@Autowired
	private DataStore dataStore;

	/**
	 * saveToDataStore calls the saveToDataStore function on the autowired implementation of DataStore.
	 * @param value
	 * @param isPalindrome
	 * @throws IOException
	 */
	@Async
	public void saveToDataStore(String value, boolean isPalindrome) throws IOException {	
		dataStore.SaveToDataStore(value, isPalindrome);
	}
}
