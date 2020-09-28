package engine;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class Quiz {
    private List<Question> questions = new ArrayList<>();

    @GetMapping
    public List<Question> getQuestion() {
        return questions;
    }

    @PostMapping
    public Question addQuestion(@Valid @RequestBody Question question) {
        question.setId(questions.size());

        questions.add(question);

        return question;
    }

    @GetMapping(path = "/{id}")
    public Question getQuestion(@PathVariable int id) {
        if (id >= questions.size() || id < 0) throw new NoQuizException(id);

        return questions.get(id);
    }

    @PostMapping(path = "/{id}/solve")
    public AnswerRes solveQuestion(@PathVariable int id, @RequestBody Answer answer) throws ParseException {
        if (id >= questions.size() || id < 0) throw new NoQuizException(id);

        Answer correctAnswer = questions.get(id).getAnswer();
        if (correctAnswer == null) correctAnswer = new Answer(new int[0]);

        return new AnswerRes(correctAnswer.equals(answer));
    }
}
