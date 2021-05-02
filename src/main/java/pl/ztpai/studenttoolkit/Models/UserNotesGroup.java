package pl.ztpai.studenttoolkit.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserNotesGroup {
    @Id
    private Long ID;
    private String Group_note_text;
    private LocalDateTime date_of_add;

    public UserNotesGroup() {
    }

    public UserNotesGroup(Long ID, String group_note_text, LocalDateTime date_of_add) {
        this.ID = ID;
        Group_note_text = group_note_text;
        this.date_of_add = date_of_add;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getGroup_note_text() {
        return Group_note_text;
    }

    public void setGroup_note_text(String group_note_text) {
        Group_note_text = group_note_text;
    }

    public LocalDateTime getDate_of_add() {
        return date_of_add;
    }

    public void setDate_of_add(LocalDateTime date_of_add) {
        this.date_of_add = date_of_add;
    }
}
