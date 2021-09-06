package pl.ztpai.studenttoolkit.Payload;


import lombok.*;
import pl.ztpai.studenttoolkit.Models.Activity;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ActivityView {
    List<Activity> activities;
}
