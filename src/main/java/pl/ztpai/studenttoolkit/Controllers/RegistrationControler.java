package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.RequestClass.RegistrationRequest;
import pl.ztpai.studenttoolkit.Services.RegistrationService;

//todo: handle password corectness , handle redirection ,token_confirmation
@Controller
@AllArgsConstructor
public class RegistrationControler {

    private final RegistrationService registrationService;
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute(name="RegistrationRequest") RegistrationRequest request, BindingResult result){

        if (request.getPassword()!=request.getConfirmPassword()){
            throw new IllegalStateException("passwords are not the same");
        }
        registrationService.register(request);
        return "redirect:/login" ;
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
