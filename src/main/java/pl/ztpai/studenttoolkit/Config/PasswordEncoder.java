package pl.ztpai.studenttoolkit.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public BCryptPasswordEncoder bcryptPassEncoder(){
        return new BCryptPasswordEncoder();
    }
}
