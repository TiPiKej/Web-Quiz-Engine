package engine;

public class Question {
    private int id;
    private String title;
    private String text;
    private String[] options;
    private int answer;

    public Question() {

    }

    public Question(String title, String text, String[] options, int answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
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
        return this.answer == answer;
    }
}

