package pl.ztpai.studenttoolkit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence")

    private Long subjectID;

    private String subjectName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name="usersubject", referencedColumnName = "userId", nullable = false)
    private Users user;

    @OneToMany(mappedBy="subject",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<UserNotes> userNotes=new ArrayList<UserNotes>();


    public Subject(String subjectName, Users user) {
        this.subjectName = subjectName;
        this.user = user;
    }

    @Override
    public String toString() {
        return  subjectName;
    }
}
