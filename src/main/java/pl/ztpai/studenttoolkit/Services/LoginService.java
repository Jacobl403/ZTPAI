package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Repository.UserRepository;
import pl.ztpai.studenttoolkit.RequestClass.LoginRequest;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userDB;

    public String signIn (LoginRequest request){
        String token=" ";

        return token;
    }

}
