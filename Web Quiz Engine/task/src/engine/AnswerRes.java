package engine;

public class AnswerRes {
    private boolean success;
    private String feedback;

    public AnswerRes() {}

    public AnswerRes(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public AnswerRes(boolean success) {
        this.success = success;
        this.feedback = success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
