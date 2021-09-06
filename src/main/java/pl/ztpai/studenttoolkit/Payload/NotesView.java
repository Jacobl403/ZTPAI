package pl.ztpai.studenttoolkit.Payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ztpai.studenttoolkit.Models.Subject;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesView {
    String subject;
    String text;
    List<Subject>subjects;

    public NotesView(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public NotesView(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }
}
