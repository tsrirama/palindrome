package com.srirama.palindrome.data;

import com.srirama.palindrome.entities.Palindrome;

import java.util.List;

/**
 * ]
 * DataStore interface defines the functions to write and read from the persistent data store
 * allows the use of a different persistent store like db through another implementation class in future
 */
public interface DataStore {

    Palindrome save(Palindrome palindrome);

    List<Palindrome> findAll();
}
