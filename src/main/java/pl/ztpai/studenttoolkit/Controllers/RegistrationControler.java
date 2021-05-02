package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Models.RegistrationRequest;
import pl.ztpai.studenttoolkit.Services.RegistrationService;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationControler {

    private final RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegistrationRequest request ){
        return registrationService.register(request);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
