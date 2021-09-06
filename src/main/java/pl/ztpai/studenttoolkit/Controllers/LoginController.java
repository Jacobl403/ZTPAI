package pl.ztpai.studenttoolkit.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Config.JwtTokenUtil;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Payload.LoginView;

@RestController
@RequestMapping("/zaloguj")
@CrossOrigin
@AllArgsConstructor
public class LoginController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody LoginView request){
        try{

            Authentication authenticate= authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            Users user=(Users)authenticate.getPrincipal();
            request.setToken(jwtTokenUtil.generateAccessToken(user));
            return ResponseEntity.ok().body(request);
        }catch(BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error");
        }

    }
}
