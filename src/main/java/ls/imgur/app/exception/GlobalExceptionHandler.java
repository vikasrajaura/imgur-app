package ls.imgur.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleNullPointerException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("exceptionMsg", e.getMessage());
        map.put("timeStamp", LocalDateTime.now().toString());
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        map.put("stackTrace", "Details: "+e);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("exceptionMsg", e.getMessage());
        map.put("timeStamp", LocalDateTime.now().toString());
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("stackTrace", "Details: "+e);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}