package pl.ztpai.studenttoolkit.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserNotes {
    @Id
    private Long ID;
    private String note_text;
    //todo: zmienic na zmienna obsugującą plik
    private String file;

    public UserNotes() {
    }

    public UserNotes(Long ID, String note_text, String file) {
        this.ID = ID;
        this.note_text = note_text;
        this.file = file;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
