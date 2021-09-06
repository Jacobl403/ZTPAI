package pl.ztpai.studenttoolkit.Services;



import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Activity;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Repository.ActivityRepository;
import pl.ztpai.studenttoolkit.Repository.SubjectRepository;
import pl.ztpai.studenttoolkit.Repository.UserRepository;
import pl.ztpai.studenttoolkit.Payload.editProfileView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class EditProfileService {

    private final UserRepository userDB;
    private final SubjectRepository subjectRepository;
    private final ActivityRepository activityRepository;
    private final BCryptPasswordEncoder encoder;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm";
    public editProfileView loadData(Authentication auth){

        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());

        String email=foundUser.get().getEmail();
        Integer amountOfSubjects=foundUser.get().getAmountOfSubjects();
        List<String> subjectsList=new ArrayList<>();
        for (Subject sub : foundUser.get().getSubjects()){
            subjectsList.add(sub.toString());
        }

        return new editProfileView(email,amountOfSubjects,subjectsList);
    }
    public editProfileView changeSubjects(Authentication auth, editProfileView request){
        String activity=new String(" ");
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        foundUser.get().setScheme("");
        subjectRepository.deleteAllByUser(foundUser.get());
        foundUser.get().setAmountOfSubjects(request.getAmountofsubjects());
        List<Subject> newList=new ArrayList<>();
        for (String name : request.getSubjects()){
            Subject sub =new Subject(name,foundUser.get());
            newList.add(sub);
            subjectRepository.save(sub);
        }

        userDB.save(foundUser.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        activity="Dokonano zmian przedmiotów w Edycja Profilu   ["+LocalDateTime.now().format(formatter)+ "]";
        activityRepository.save(new Activity(activity,foundUser.get()));
        return request;
    }

    public editProfileView changeEmail(Authentication auth, editProfileView request){
        String activity=new String(" ");
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        foundUser.get().setEmail(request.getEmail());
        userDB.save(foundUser.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        activity="Dokonano zmiany email w Edycja Profilu   ["+LocalDateTime.now().format(formatter)+ "]";
        activityRepository.save(new Activity(activity,foundUser.get()));
        return request;
    }
    public editProfileView changePassword(Authentication auth, editProfileView request){
        String activity=new String(" ");
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        String encodedPassword=encoder.encode(request.getPassword());
        foundUser.get().setPassword(encodedPassword);
        userDB.save(foundUser.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        activity="Dokonano zmiany hasła w Edycja Profilu   ["+LocalDateTime.now().format(formatter)+ "]";
        activityRepository.save(new Activity(activity,foundUser.get()));
        return request;
    }
}
