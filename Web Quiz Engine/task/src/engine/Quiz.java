package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class Quiz {
    private List<Question> questions = new ArrayList<>();

    @PostMapping(path = "/api/quizzes")
    public Question addQuestion(@Valid @RequestBody Question question) {
        question.setId(questions.size());

        if (question.getOptions().length <= 2) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid fields!");

        questions.add(question);

        return question;
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> getQuestion() {
        return questions;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Question getQuestion(@PathVariable int id) {
        if (id >= questions.size() || id < 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");

        return questions.get(id);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public AnswerRes solveQuestion(@PathVariable int id, /*@RequestParam(value = "answer", required = false)*/@RequestBody int[] answer) {
        if (id >= questions.size() || id < 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");

        if (answer == null) answer = new int[0];

        return new AnswerRes(questions.get(id).ifGuessed(answer));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect fields!")
    public HashMap<String, String> handleBadRequest(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "BAD_REQUEST");
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}
