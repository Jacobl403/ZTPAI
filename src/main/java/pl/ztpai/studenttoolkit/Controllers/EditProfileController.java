package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Payload.editProfileView;
import pl.ztpai.studenttoolkit.Services.EditProfileService;


@RestController
@RequestMapping("/edycjaprofilu")
@CrossOrigin
@AllArgsConstructor
public class EditProfileController {

    private EditProfileService editProfileService;

    @GetMapping
    public @ResponseBody
    editProfileView loadData(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return editProfileService.loadData(auth);
    }

    @PostMapping("/zmianaprzedmiotow")
    public editProfileView changeSubjects(@RequestBody editProfileView request){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return editProfileService.changeSubjects(auth,request);

    }
    @PostMapping("/zmianaemail")
    public editProfileView changeEmail(@RequestBody editProfileView request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return editProfileService.changeEmail(auth,request);

    }
    @PostMapping("/zmianahasla")
    public editProfileView changePassword(@RequestBody editProfileView request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return editProfileService.changePassword(auth,request);
    }
}
