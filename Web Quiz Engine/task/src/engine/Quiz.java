package engine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Quiz {
    final String JSONLoc = "./Questions.json";

    private List<Question> questions = new ArrayList<>();

    public Quiz() {
//        catch questions from json file
        JSONParser parser = new JSONParser();
        try {
            JSONArray questionsArrayJSON = (JSONArray) parser.parse(new FileReader(JSONLoc));


            for (Object o : questionsArrayJSON) {
                JSONObject questionJSON = (JSONObject) o;

                String title = (String) questionJSON.get("title");
                String text = (String) questionJSON.get("text");
                JSONArray optionsArray = (JSONArray) questionJSON.get("options");
                String[] options = new String[optionsArray.size()];

                for (int i = 0; i < optionsArray.size(); i++) {
                    options[i] = (String) optionsArray.get(i);
                }

                Question curQuestion = new Question(title, text, options);

                questions.add(curQuestion);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/api/quiz")
    public int getQuestion() {
        return questions.size();
    }

    @GetMapping(path = "/api/quiz/{id}")
    public Question getQuestion(@PathVariable int id) {
        return questions.get(id);
    }
}
