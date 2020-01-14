package lk.ijse.dep.spring.advice;

import lk.ijse.dep.spring.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@CrossOrigin
@RestControllerAdvice
public class AppwideExceptionHandler {
/*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception e){
    e.printStackTrace();
    return "Something Went Wrong Please Contact Dev";
}*/

@ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity handleNotFoundException(Exception e){
    return new ResponseEntity(HttpStatus.NOT_FOUND);
}
}
