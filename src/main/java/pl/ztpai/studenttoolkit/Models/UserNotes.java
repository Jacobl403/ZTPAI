package pl.ztpai.studenttoolkit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class UserNotes {
    @Id
    @GeneratedValue
    private Long userNotesId;
    @Column(columnDefinition = "TEXT")
    private String noteText;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinColumn(name="subjectusernotes", referencedColumnName = "subjectID", nullable = false)
    private Subject subject;

    public UserNotes(String text, Subject subject) {
        this.noteText=text;
        this.subject=subject;
    }

    @Override
    public String toString() {
        return noteText;
    }
}
