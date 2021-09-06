package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Activity;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Payload.ActivityView;
import pl.ztpai.studenttoolkit.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActivityService {
    private final UserRepository userDB;
    public ActivityView getActivities(Authentication auth){
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        List<Activity> activityList=new ArrayList<>();
        activityList=foundUser.get().getActivities();
        return new ActivityView(activityList);
    }
}
