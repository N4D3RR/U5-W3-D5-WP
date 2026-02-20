package naderdeghaili.u5w3d5wp.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.UnauthorizedException;
import naderdeghaili.u5w3d5wp.payloads.LoginDTO;
import naderdeghaili.u5w3d5wp.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final PersoneService personeService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;


    public AuthService(PersoneService personeService, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.personeService = personeService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }


    public String getCredentialsGiveToken(LoginDTO body) {
        Persona found = this.personeService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.createToken(found);

            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate, riprova");
        }

    }

}
