package engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int[] answers;

    public int length() {
        return answers.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Answer)) return false;

        Answer answer = (Answer) obj;

        if (this.length() != answer.length()) return false;

        for (int curAns : answer.getAnswers()) {
            boolean isThere = false;
            for (int cThisAns : this.getAnswers()) {
                if (cThisAns == curAns) {
                    isThere = true;
                    break;
                }
            }

            if (!isThere) return false;
        }

        for (int cThisAns : this.getAnswers()) {
            boolean isThere = false;
            for (int curAns : answer.getAnswers()) {
                if (cThisAns == curAns) {
                    isThere = true;
                    break;
                }
            }

            if (!isThere) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(answers);
    }
}
