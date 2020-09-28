package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoQuizException extends RuntimeException {
    public NoQuizException(int id) {
        super(String.format("%d# quiz not found!", id));
    }
}
