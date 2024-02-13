package ru.sushchenko.t1consulting.service;

import com.jayway.jsonpath.internal.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FrequencyServiceTest {
    @InjectMocks
    private FrequencyService frequencyService;
    @Test
    public void countCharFrequencyShouldReturnCharsFrequency() {
        String str = "aaaaabcccc";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('a', 5);
        expected.put('c', 4);
        expected.put('b', 1);

        Map<Character, Integer> result = frequencyService.countCharFrequency(str);
        assertEquals(expected, result);
    }
}