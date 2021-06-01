package pl.ztpai.studenttoolkit.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.RequestClass.LoginRequest;


@Controller
@AllArgsConstructor
public class LoginControler {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginInto( @ModelAttribute(name="LoginRequest") LoginRequest user ,BindingResult result){
        if (result.hasErrors()){
            return "/login";
        }

       return "/index";
    }


}
