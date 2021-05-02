package pl.ztpai.studenttoolkit.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class UserPrefences {
    @Id
    private Long ID;
    private String xls_file;
    private Integer subject_count;

    public UserPrefences() {
    }

    public UserPrefences(Long ID, String xls_file, Integer subject_count) {
        this.ID = ID;
        this.xls_file = xls_file;
        this.subject_count = subject_count;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getXls_file() {
        return xls_file;
    }

    public void setXls_file(String xls_file) {
        this.xls_file = xls_file;
    }

    public Integer getSubject_count() {
        return subject_count;
    }

    public void setSubject_count(Integer subject_count) {
        this.subject_count = subject_count;
    }
}
