package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {
    private int id;
    final private String title;
    final private String text;
    final private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    final private int answer;

    public Question(String title, String text, String[] options, int answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
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

    public boolean ifGuessed(int answer) {
        return answer == this.answer;
    }
}

