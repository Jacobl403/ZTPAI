package pl.ztpai.studenttoolkit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//controler post ktory po wpisaniu loginu i hasła przesyła metoda post zapytanie
//login/register controller
//sesion
@Controller("/")
public class MainControler {
    @RequestMapping("/login")
    public String printHelloWorld(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegisterform(){
        return "register";
    }

    @GetMapping("/index")
    public String getindex(){
        return "index";
    }
    @GetMapping("/materials")
    public String getmaterials(){
        return "materials";
    }
    @GetMapping("/notes")
    public String getnotes(){
        return "notes";
    }
    @GetMapping("/schedule")
    public String getschedule(){
        return "schedule";
    }
}
