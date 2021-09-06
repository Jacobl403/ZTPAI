package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Activity;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Payload.ScheduleView;
import pl.ztpai.studenttoolkit.Repository.ActivityRepository;
import pl.ztpai.studenttoolkit.Repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private  final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm";
    public ScheduleView getSchedule(Authentication auth){
        Optional<Users> foundUser=userRepository.findByEmail(auth.getPrincipal().toString());
        return new ScheduleView(foundUser.get().getScheme());
    }

    public ScheduleView saveSchedule(Authentication auth, ScheduleView request){
        String activity=new String(" ");
        Optional<Users> foundUser=userRepository.findByEmail(auth.getPrincipal().toString());
        foundUser.get().setScheme(request.getState());
        userRepository.save(foundUser.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        if(request.getState()=="null"){
            activity="Zresetowano plan  w Plan Zajęć   ["+ LocalDateTime.now().format(formatter)+ "]";
        }else{
            activity="Zaktualizowano plan  w Plan Zajęć   ["+ LocalDateTime.now().format(formatter)+ "]";
        }
        activityRepository.save(new Activity(activity,foundUser.get()));
        return request;
    }
}
