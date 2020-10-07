package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @Size(min = 2)
    @NotNull
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter(AccessLevel.NONE)
    private Answer answers;

    public Question(QuestionDBFormat question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.text = question.getText();
        this.options = question.getOptions();
        this.setAnswers(parseAnswers(question.getAnswers()));
    }

    public void setAnswers(int[] answers) {
        this.answers = new Answer(answers);
    }

    public static int[] parseAnswers(String answersStr) {
        if (answersStr == null) return new int[0];
        String trimmed = answersStr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "");
        String[] items = trimmed.equals("") ? new String[0] : trimmed.split(",");
        int[] answers = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            answers[i] = Integer.parseInt(items[i]);
        }

        return answers;
    }

    public static List<Question> parseListQuestion(List<QuestionDBFormat> questions) {
        final List<Question> z = new ArrayList<>();

        for (QuestionDBFormat question : questions) {
            z.add(new Question(question));
        }

        return z;
    }
}

