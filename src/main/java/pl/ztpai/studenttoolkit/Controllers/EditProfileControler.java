package pl.ztpai.studenttoolkit.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.RequestClass.editProfileRequest;
import pl.ztpai.studenttoolkit.Services.EditProfileService;




@RestController
@RequestMapping("/edycjaprofilu")
@CrossOrigin
public class EditProfileControler {
    @Autowired
    private EditProfileService editProfileService;
    @GetMapping
    public @ResponseBody ObjectNode loadData(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return editProfileService.loadData(auth);
    }

    @PostMapping
    public void saveChanges(editProfileRequest request){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
                "gumisie"));
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       editProfileService.saveChanges(auth,request);
    }
}
