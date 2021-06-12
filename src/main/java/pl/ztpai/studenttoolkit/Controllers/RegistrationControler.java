package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.RequestClass.RegistrationRequest;
import pl.ztpai.studenttoolkit.Services.RegistrationService;


@RestController
@RequestMapping("/rejestracja")
@CrossOrigin
public class RegistrationControler {
    @Autowired
    private  RegistrationService registrationService;

    @PostMapping
    public void register(RegistrationRequest request){
        registrationService.register(request);
    }

}
