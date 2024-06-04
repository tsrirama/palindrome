package com.srirama.palindrome.service;


import com.srirama.palindrome.data.DataStore;
import com.srirama.palindrome.entities.Palindrome;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final DataStore dataStore;

    @Getter
    private final Map<String, Boolean> cacheObject = new HashMap<>();

    public CacheService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void loadCacheAtStartup() {
        loadCache();
    }

    public void loadCache() {
        List<Palindrome> palindromes = dataStore.findAll();

        if (!CollectionUtils.isEmpty(palindromes)) {
            palindromes.forEach(p -> cacheObject.putIfAbsent(p.getName(), p.isPalindrome()));
        }
    }
}
