package com.srirama.palindrome.data;

import com.srirama.palindrome.entities.Palindrome;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

/**
 * FileDataStore is an implementation to use file system as persistent store
 */
@Repository
public class FileDataStore implements DataStore {

    static Map<String, Boolean> cacheMap = new HashMap<String, Boolean>();
    static final String FILE_PATH = "cache.txt";

    /**
     * Writes to the input and palindrome check to the file
     *
     * @param palindrome
     */
    @Override
    public Palindrome save(Palindrome palindrome) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(FILE_PATH, true));
            System.out.println("trying to write to file");
            out.write(palindrome.getName() + "=" + palindrome.isPalindrome());
            out.newLine();
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException ex) {
                    System.out.println("COULD NOT LOG!!");
                }
            }
        }
        return palindrome;
    }

    /**
     * This method is used for loading cache by getting the data from file system
     *
     * @return
     */
    @Override
    public List<Palindrome> findAll() {

        List<Palindrome> palindromes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(FILE_PATH))) {

            String line;

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] columns = line.split("=");

                    palindromes.add(new Palindrome(columns[0], Boolean.parseBoolean(columns[1])));
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("unable to load the cache");
        }
        return palindromes;
    }

}
