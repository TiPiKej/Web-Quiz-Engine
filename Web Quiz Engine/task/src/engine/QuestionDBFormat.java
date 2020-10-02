package engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Quizzes")
public class QuestionDBFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "options")
    private String[] options;

    @Column(name = "answers")
    private String answers;

    public QuestionDBFormat(Question q) {
        this.id = q.getId();
        this.title = q.getTitle();
        this.text = q.getText();
        this.options = q.getOptions();
        this.answers = q.getAnswer().toString();
    }
}
