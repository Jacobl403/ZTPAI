package pl.ztpai.studenttoolkit.Models;

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
    private String noteText;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinColumn(name="subjectusernotes", referencedColumnName = "subjectID", nullable = false)
    private Subject subject;

    @Override
    public String toString() {
        return noteText;
    }
}
