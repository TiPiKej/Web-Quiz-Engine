package engine;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class Quiz {
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public List<Question> getQuestion() {
        return Question.parseListQuestion(quizRepository.findAll());
    }

    @PostMapping
    public QuestionDBFormat addQuestion(@Valid @RequestBody Question question) {
        if (question.getAnswers() == null) {
            question.setAnswers(new int[0]);
        }

        return quizRepository.save(new QuestionDBFormat(question));
    }

    @GetMapping(path = "/{id}")
    public Question getQuestion(@PathVariable(value = "id") Long questionId) {
        return new Question(quizRepository.findById(questionId).orElseThrow(
                () -> new NoQuizException(questionId)
        ));
    }

    @PostMapping(path = "/{id}/solve")
    public Map<String, String> solveQuestion(@PathVariable(value = "id") Long questionId, @RequestBody Answer answer) {
        Map<String, String> resAns = new HashMap<>();

        Answer correctAnswer = getQuestion(questionId).getAnswers();
        if (correctAnswer == null) correctAnswer = new Answer(new int[0]);

        boolean success = correctAnswer.equals(answer);

        resAns.put("success", Boolean.toString(success));
        resAns.put("feedback", success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.");

        return resAns;
    }
}
