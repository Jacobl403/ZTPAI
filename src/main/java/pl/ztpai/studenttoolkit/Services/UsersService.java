package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Repository.SubjectRepository;
import pl.ztpai.studenttoolkit.Repository.UserRepository;


@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {
    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return  userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("USER NOT FOUND"));
    }
    public String singUpUser(Users userEntity)
    {

        boolean isUserInDB= userRepository.findByEmail(userEntity.getEmail()).isPresent();
        if(isUserInDB)return"uzytkownik o podanym emailu istnieje";
        String encodedPassword=encoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        return "Pomyslnie utworzono uzytkownika";
    }

    public int enableUsers(String  email ){
        return userRepository.enableUsers(email);
    }
}
