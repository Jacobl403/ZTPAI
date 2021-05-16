package pl.ztpai.studenttoolkit.Models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class UserNotes {
    @Id
    private Long id;
    private String noteText;
    //todo: zmienic na zmienna obsugującą plik
    private String file;


}
