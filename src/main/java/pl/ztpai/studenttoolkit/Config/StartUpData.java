package pl.ztpai.studenttoolkit.Config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.ztpai.studenttoolkit.Payload.editProfileView;
import pl.ztpai.studenttoolkit.Repository.SubjectRepository;
import pl.ztpai.studenttoolkit.Payload.RegistrationView;
import pl.ztpai.studenttoolkit.Services.EditProfileService;
import pl.ztpai.studenttoolkit.Services.RegistrationService;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class StartUpData {
    private final RegistrationService service;
    private final SubjectRepository subDB;
    private final EditProfileService editProfileService;

    @EventListener
    public void appStart(ApplicationReadyEvent event){
        RegistrationView request=new RegistrationView("kuba_lag@o2.pl", "gumisie",null);
        service.register(request);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> subjects=new ArrayList<>();
        subjects.add("ZTPAI");
        subjects.add("Nokia");
        subjects.add("BSK");
        subjects.add("PSK");

        editProfileView changeSubRequest=new editProfileView(4,subjects);
        editProfileService.changeSubjects(auth,  changeSubRequest);
    }
}
