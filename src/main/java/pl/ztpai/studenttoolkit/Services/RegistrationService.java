package pl.ztpai.studenttoolkit.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.RequestClass.RegistrationRequest;
import pl.ztpai.studenttoolkit.Models.Users;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UsersService appUserService;
    public String register(RegistrationRequest request)
    {
        Users user1=new Users(
                request.getLogin(),
                request.getPassword(),
                request.getEmail());
       String registrationResult= appUserService.singUpUser(user1);


       return registrationResult;
    }


}
