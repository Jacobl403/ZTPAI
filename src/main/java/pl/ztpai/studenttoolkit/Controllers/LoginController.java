package pl.ztpai.studenttoolkit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ztpai.studenttoolkit.RequestClass.LoginRequest;
import pl.ztpai.studenttoolkit.Services.LoginService;

@RestController
@RequestMapping("/zaloguj")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public String register(LoginRequest request){
        String result=loginService.signIn(request);

        if (result.equals("ERROR")){
            //co należy wysłać do reacta aby to działało
            return "ERROR";
        }else {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(request.getLogin(),
                    request.getPassword()));
            return result;
        }

    }
}
