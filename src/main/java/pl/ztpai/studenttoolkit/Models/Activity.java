package pl.ztpai.studenttoolkit.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Activity {
    @Id
    private Long ID;

    private Integer type_of_activity;
    private String log;
    //tu nie jestem pewien
    private LocalDateTime date_of_activity;

    public Activity() {
    }

    public Activity(Long ID, Integer type_of_activity, String log, LocalDateTime date_of_activity) {
        this.ID = ID;
        this.type_of_activity = type_of_activity;
        this.log = log;
        this.date_of_activity = date_of_activity;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Integer getType_of_activity() {
        return type_of_activity;
    }

    public void setType_of_activity(Integer type_of_activity) {
        this.type_of_activity = type_of_activity;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDateTime getDate_of_activity() {
        return date_of_activity;
    }

    public void setDate_of_activity(LocalDateTime date_of_activity) {
        this.date_of_activity = date_of_activity;
    }
}
