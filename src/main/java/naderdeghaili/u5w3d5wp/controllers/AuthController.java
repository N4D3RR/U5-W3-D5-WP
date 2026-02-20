package naderdeghaili.u5w3d5wp.controllers;


import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.ValidationException;
import naderdeghaili.u5w3d5wp.payloads.LoginDTO;
import naderdeghaili.u5w3d5wp.payloads.LoginResDTO;
import naderdeghaili.u5w3d5wp.payloads.NuovaPersonaDTO;
import naderdeghaili.u5w3d5wp.services.AuthService;
import naderdeghaili.u5w3d5wp.services.PersoneService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PersoneService personeService;


    public AuthController(AuthService authService, PersoneService personeService) {
        this.authService = authService;
        this.personeService = personeService;
    }

    //REGISTRAZIONE
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona registerPersona(@RequestBody @Validated NuovaPersonaDTO payload, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.personeService.savePersona(payload);
        }
    }

    //LOGIN
    @PostMapping("/login")
    public LoginResDTO login(@RequestBody LoginDTO body) {
        return new LoginResDTO(this.authService.getCredentialsGiveToken(body));
    }

}

