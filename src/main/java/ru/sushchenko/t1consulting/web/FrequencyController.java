package ru.sushchenko.t1consulting.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.sushchenko.t1consulting.dto.RequestDto;
import ru.sushchenko.t1consulting.service.FrequencyService;
import ru.sushchenko.t1consulting.utils.ControllerErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Frequency", description = "String frequency operations")
public class FrequencyController {
    private final FrequencyService frequencyService;
    @Operation(
            summary = "Count char frequency",
            description = "Count char frequency in a given string and return dictionary"
    )
    @PostMapping("/frequencies") // Using DTO class for good validation and scalability
    public ResponseEntity<Map<Character, Integer>> countCharFrequency(@Valid @RequestBody RequestDto req) {
        return ResponseEntity.ok(frequencyService.countCharFrequency(req.getInputString()));
    }

    //     Exception Handling
    @ExceptionHandler
    private ResponseEntity<ControllerErrorResponse> handleException(RuntimeException e) {
        ControllerErrorResponse errorResponse = new ControllerErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        HttpStatus httpStatus = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
    // validation exception handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
