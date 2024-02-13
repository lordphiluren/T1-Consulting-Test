package ru.sushchenko.t1consulting.web;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sushchenko.t1consulting.dto.RequestDto;
import ru.sushchenko.t1consulting.service.FrequencyService;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FrequencyControllerTest {
    @Mock
    private FrequencyService frequencyService;

    @InjectMocks
    private FrequencyController frequencyController;
    @Test
    void givenRequestDto_whenCountCharFrequency_thenReturnMapWithFrequencies() {
        // given
        String inputString = "aaaaabcccc";
        Map<Character, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put('a', 5);
        expectedFrequencyMap.put('c', 4);
        expectedFrequencyMap.put('b', 1);

        RequestDto requestDto = new RequestDto();
        requestDto.setInputString(inputString);
        // when
        when(frequencyService.countCharFrequency(inputString)).thenReturn(expectedFrequencyMap);

        ResponseEntity<Map<Character, Integer>> response = frequencyController.countCharFrequency(requestDto);
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFrequencyMap, response.getBody());
    }
}