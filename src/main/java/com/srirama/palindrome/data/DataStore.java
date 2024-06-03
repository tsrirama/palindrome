package com.srirama.palindrome.data;

import java.util.Map;

/**]
 * DataStore interface defines the functions to write and read from the persistent data store
 * allows the use of a different persistent store like db through another implementation class in future
 */
public interface DataStore {
	void SaveToDataStore(String value,boolean isPalindrome);
	Map<String,Boolean> getDataForCache();
}
