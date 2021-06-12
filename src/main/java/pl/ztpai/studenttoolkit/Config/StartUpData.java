package pl.ztpai.studenttoolkit.Config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.ztpai.studenttoolkit.Repository.SubjectRepository;
import pl.ztpai.studenttoolkit.RequestClass.RegistrationRequest;
import pl.ztpai.studenttoolkit.Services.RegistrationService;

@Component
@AllArgsConstructor
public class StartUpData {
    private final RegistrationService service;
    private final SubjectRepository subDB;

    @EventListener
    public void appStart(ApplicationReadyEvent event){
        RegistrationRequest request=new RegistrationRequest("jacob403",
                "kuba_lag@o2.pl",
                "gumisie",
                "gumisie");
        service.register(request);

    }
}
