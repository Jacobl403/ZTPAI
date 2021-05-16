package pl.ztpai.studenttoolkit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/")
public class MainControler {

    @GetMapping(path = "/login")
    public String getLoginForm(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegisterForm(){
        return "register";
    }
    @GetMapping("/index")
    public String getIndexForm(){
        return "index";
    }
    @GetMapping("/settings")
    public String getSettingsForm(){return "settings";}
    @GetMapping("/materials")
    public String getMaterialsForm(){
        return "materials";
    }
    @GetMapping("/notes")
    public String getNotesForm(){
        return "notes";
    }
    @GetMapping("/schedule")
    public String getScheduleForm(){
        return "schedule";
    }
}
