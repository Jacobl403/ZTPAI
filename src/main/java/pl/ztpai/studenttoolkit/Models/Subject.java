package pl.ztpai.studenttoolkit.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Subject {
    @Id
    private Long ID;
    private String Subject_name;
    private Integer xls_column;
    private Integer xls_row;
    private String note;
    private LocalDateTime date_of_occur;

    public Subject() {
    }

    public Subject(Long ID, String subject_name, Integer xls_column, Integer xls_row, String note, LocalDateTime date_of_occur) {
        this.ID = ID;
        Subject_name = subject_name;
        this.xls_column = xls_column;
        this.xls_row = xls_row;
        this.note = note;
        this.date_of_occur = date_of_occur;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getSubject_name() {
        return Subject_name;
    }

    public void setSubject_name(String subject_name) {
        Subject_name = subject_name;
    }

    public Integer getXls_column() {
        return xls_column;
    }

    public void setXls_column(Integer xls_column) {
        this.xls_column = xls_column;
    }

    public Integer getXls_row() {
        return xls_row;
    }

    public void setXls_row(Integer xls_row) {
        this.xls_row = xls_row;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDate_of_occur() {
        return date_of_occur;
    }

    public void setDate_of_occur(LocalDateTime date_of_occur) {
        this.date_of_occur = date_of_occur;
    }
}
