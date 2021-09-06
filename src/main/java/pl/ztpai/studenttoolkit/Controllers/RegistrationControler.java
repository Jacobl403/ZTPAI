package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Payload.RegistrationView;
import pl.ztpai.studenttoolkit.Services.RegistrationService;


@RestController
@RequestMapping("/rejestracja")
@CrossOrigin
@AllArgsConstructor
public class RegistrationControler {
    private  RegistrationService registrationService;

    @PostMapping("/nowyuzytkownik")
    public RegistrationView register(@RequestBody RegistrationView request){
        return registrationService.register(request);
    }

}
