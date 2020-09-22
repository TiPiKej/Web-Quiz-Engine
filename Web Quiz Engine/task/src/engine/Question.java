package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Question {
    private int id;

    @NotNull(message = "Please enter title")
    @NotEmpty
    private String title;

    @NotNull(message = "Please enter text")
    @NotEmpty
    private String text;

    @NotNull
    @NotEmpty
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @NotNull
//    @NotEmpty
    private int[] answer;

    public Question() {
    }

    public Question(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Question(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new int[0];
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String[] getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean ifGuessed(int[] answer) {
        if (answer.length != this.answer.length) return false;

        return Objects.equals(answer, this.answer);

//        boolean isCorrect = true;

//        for (int a : this.answer) {
//            boolean is = false;
//            for (int aTemp : answer) {
//                if (aTemp == a) {
//                    is = true;
//                    break;
//                }
//            }
//            if (!is) {
//                isCorrect = false;
//                break;
//            }
//        }

//        return isCorrect;
    }
}

