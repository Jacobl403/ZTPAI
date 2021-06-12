package pl.ztpai.studenttoolkit.Models;

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
    private Long activityID;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name="userId", referencedColumnName = "userId", nullable = false)
    private Users user;
}
