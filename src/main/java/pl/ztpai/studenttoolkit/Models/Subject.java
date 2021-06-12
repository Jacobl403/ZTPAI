package pl.ztpai.studenttoolkit.Models;

import lombok.*;

import javax.persistence.*;
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
    private Integer xRow;
    private Integer yRow;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name="usersubject", referencedColumnName = "userId", nullable = false)
    private Users user;

    @OneToMany(mappedBy="subject",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<UserNotes> userNotes=new ArrayList<UserNotes>();

    @OneToMany(mappedBy="subject",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Materials>materials=new ArrayList<Materials>();

    public Subject(String subjectName, Users user) {
        this.subjectName = subjectName;
        this.user = user;
    }

    @Override
    public String toString() {
        return  subjectName;
    }
}
