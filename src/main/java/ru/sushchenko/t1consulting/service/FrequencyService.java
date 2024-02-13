package ru.sushchenko.t1consulting.service;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FrequencyService {
    public Map<Character, Integer> countCharFrequency(String str) {
        Map<Character, Integer> map  = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            if(map.containsKey(currChar)) {
                map.put(str.charAt(i), map.get(currChar)+1);
            } else {
                map.put(currChar, 1);
            }
        }
        return sortByValueDesc(map);
    }
    private Map<Character, Integer> sortByValueDesc(Map<Character, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
