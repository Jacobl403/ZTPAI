package pl.ztpai.studenttoolkit.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.VerificationToken;
import pl.ztpai.studenttoolkit.Repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    public void saveToken(VerificationToken verificationToken){
        tokenRepository.save(verificationToken);
    }

    public Optional<VerificationToken>getToken(String token){
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
