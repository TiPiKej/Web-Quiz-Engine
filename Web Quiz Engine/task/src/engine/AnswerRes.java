package engine;

import lombok.Getter;

@Getter
public class AnswerRes {
    private final boolean success;
    private final String feedback;

    public AnswerRes(boolean success) {
        this.success = success;
        this.feedback = success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.";
    }
}
