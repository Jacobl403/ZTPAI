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
public class UserNotesGroup {
    @Id
    private Long ID;
    private String Group_note_text;
    private LocalDateTime date_of_add;
}
