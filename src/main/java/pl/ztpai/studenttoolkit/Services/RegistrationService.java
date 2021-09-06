package pl.ztpai.studenttoolkit.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Payload.RegistrationView;
import pl.ztpai.studenttoolkit.Models.Users;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UsersService appUserService;
    public RegistrationView register(RegistrationView request)
    {
        Users user1=new Users(request.getEmail(), request.getPassword());
        String status= appUserService.singUpUser(user1);
        request.setStatus(status);
       return request;
    }


}
