package engine;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        if (question.getAnswer() == null) question.setAnswer(new int[0]);

        return quizRepository.save(new QuestionDBFormat(question));
    }

    @GetMapping(path = "/{id}")
    public Question getQuestion(@PathVariable(value = "id") Long questionId) {
        return new Question(quizRepository.findById(questionId).orElseThrow(
                () -> new NoQuizException(questionId)
        ));
    }

    @PostMapping(path = "/{id}/solve")
    public AnswerRes solveQuestion(@PathVariable(value = "id") Long questionId, @RequestBody Answer answer) {
        Answer correctAnswer = getQuestion(questionId).getAnswer();
        if (correctAnswer == null) correctAnswer = new Answer(new int[0]);

        return new AnswerRes(correctAnswer.equals(answer));
    }
}
