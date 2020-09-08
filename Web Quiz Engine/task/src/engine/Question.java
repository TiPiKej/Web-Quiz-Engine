package engine;

public class Question {
    private String title;
    private String text;
    private String[] options;

    public Question() {

    }

    public Question(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
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

    public String[] getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
