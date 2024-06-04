package com.srirama.palindrome.data;

import java.io.IOException;

import com.srirama.palindrome.entities.Palindrome;
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
		dataStore.save(new Palindrome(value, isPalindrome));
	}
}
