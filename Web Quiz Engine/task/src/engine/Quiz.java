package engine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Quiz {
//    final String JSONLoc = "./Questions.json";

    private List<Question> questions = new ArrayList<>();
    private List<Integer> answers = new ArrayList<>();

    public Quiz() {
        String title = "The Java Logo";
        String text = "What is depicted on the Java logo?";
        String[] options = new String[]{"Robot","Tea leaf","Cup of coffee","Bug"};
        int rightAnswer = 2; // cup of coffee

        Question tempQuestion = new Question(title, text, options);
        answers.add(rightAnswer);
        questions.add(tempQuestion);
    }

    @GetMapping(path = "/api/quiz")
    public Question getQuestion() {
        return questions.get(0);
    }

    @PostMapping(path = "/api/quiz")
    public AnswerRes getQuestion(@RequestParam("answer") int answer) {
        if (answers.get(0) == answer) return new AnswerRes(true, "Congratulations, you're right!");
        else return new AnswerRes(false, "Wrong answer! Please, try again.");
    }
}
