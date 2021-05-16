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
public class UserPrefences {
    @Id
    private Long ID;
    private String xls_file;
    private Integer subject_count;


}
