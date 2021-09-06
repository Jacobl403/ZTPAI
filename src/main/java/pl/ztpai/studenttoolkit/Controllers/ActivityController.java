package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ztpai.studenttoolkit.Payload.ActivityView;
import pl.ztpai.studenttoolkit.Services.ActivityService;

@RestController
@RequestMapping("/aktualnosci")
@AllArgsConstructor
@CrossOrigin
public class ActivityController {
    private final ActivityService activityService;
    @GetMapping
    public ActivityView getActivities(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return activityService.getActivities(auth);
    }
}
