package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Models.VerificationToken;
import pl.ztpai.studenttoolkit.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {

    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenService tokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("USER NOT FOUND"));

    }
    public String singUpUser(Users userModel){
        boolean isUserInDB= userRepository.findByEmail(userModel.getEmail()).isPresent();
        if (isUserInDB){
            throw new IllegalStateException("User with that email exist");
        }

        String encodedPassword=encoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);

        userRepository.save(userModel);

        String token=UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                userModel
        );
        tokenService.saveToken(verificationToken);

        return token;
    }

    public int enableUsers(String  email ){
        return userRepository.enableUsers(email);
    }
}
