package pl.ztpai.studenttoolkit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Activity {
    @Id
    @GeneratedValue
//    @JsonIgnore
    private Long activityID;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name="userId", referencedColumnName = "userId", nullable = false)
    @JsonIgnore
    private Users user;

    public Activity(String content, Users user) {
        this.content = content;
        this.user = user;
    }
}
