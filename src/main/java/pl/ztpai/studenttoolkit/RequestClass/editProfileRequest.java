package pl.ztpai.studenttoolkit.RequestClass;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.ztpai.studenttoolkit.Models.Subject;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class editProfileRequest {
    private String Email;
    private String password;
    private Integer amountOfSubjects;
    private List<Subject> subjects;
}
