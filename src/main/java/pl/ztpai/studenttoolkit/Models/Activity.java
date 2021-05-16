package pl.ztpai.studenttoolkit.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Activity {
    @Id
    private Long ID;
    private Integer typeOfActivity;
    private String log;
    private LocalDateTime dateOfActivity;

}
