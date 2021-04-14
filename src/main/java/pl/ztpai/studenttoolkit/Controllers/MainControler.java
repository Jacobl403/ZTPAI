package pl.ztpai.studenttoolkit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//controler post ktory po wpisaniu loginu i hasła przesyła metoda post zapytanie
//login/register controller
@Controller
public class MainControler {
    @GetMapping("/")
    public String printHelloWorld(){
        return "index";
    }

}
