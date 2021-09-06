package pl.ztpai.studenttoolkit.Payload;


import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class editProfileView {
    private String email;
    private String password="";
    private Integer amountofsubjects;
    private List<String> subjects;



    public editProfileView(String email, Integer amountOfSubjects, List<String> subjects) {
        this.email = email;
        this.amountofsubjects = amountOfSubjects;
        this.subjects = subjects;
    }


    public editProfileView(String password) {
        this.password = password;
    }

    public editProfileView(Integer amountOfsubjects, List<String> subjects) {
        this.amountofsubjects = amountOfsubjects;
        this.subjects = subjects;
    }
}
