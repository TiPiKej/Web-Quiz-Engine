package engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Quiz {
    private List<Question> questions = new ArrayList<>();

    private List<Question> getQuestionsFromFile() {
        List<Question> questions = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try {
            Path fileLoc = ResourceUtils.getFile("Questions.json").toPath();
            FileReader reader = new FileReader(String.valueOf(fileLoc));
//            read JSON file
            JSONArray a = (JSONArray) jsonParser.parse(reader);

            for (Object o : a) {
                JSONObject questionObj = (JSONObject) o;

                String title = (String) questionObj.get("title");

                String text = (String) questionObj.get("text");

                JSONArray optionsObj = (JSONArray) questionObj.get("options");
                List<String> optionsList = new ArrayList<>();
                for (Object option : optionsObj) {
                    optionsList.add((String) option);
                }
                String[] optionsString = new String[optionsObj.size()];
                for (int i = 0; i < optionsList.size(); i++) {
                    optionsString[i] = optionsList.get(i);
                }


                int answer = (int) questionObj.get("answer");

                Question question = new Question(title, text, optionsString, answer);
                questions.add(question);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private List<Question> setQuestions() {
        final List<Question> questions = new ArrayList<>();

        String[] title = new String[]{
                "The java logo",
                "The Ultimate Question"
        };
        String[] text = new String[]{
                "What is depicted on the Java logo?",
                "What is the answer to the Ultimate Question of Life, the Universe and Everything?"
        };
        String[][] options = new String[][]{
                {"Robot","Tea leaf","Cup of coffee","Bug"},
                {"Everything goes right","42","2+2=4","11011100"}
        };
        int[] answer = new int[]{
                2,
                1
        };

        for (int i = 0; i < title.length; i++) {
            questions.add(new Question(title[i], text[i], options[i], answer[i]));
        }

        return questions;
    }

    @PostMapping(path = "/api/quizzes")
    public Question addQuestion(@RequestBody Question question) {
        question.setId(questions.size());

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
    public AnswerRes solveQuestion(@PathVariable int id, @RequestParam("answer") int answer) {
        return new AnswerRes(questions.get(id).ifGuessed(answer));
    }
}
