package naderdeghaili.u5w3d5wp.controllers;

import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.entities.Prenotazione;
import naderdeghaili.u5w3d5wp.exceptions.ValidationException;
import naderdeghaili.u5w3d5wp.payloads.PrenotazioneDTO;
import naderdeghaili.u5w3d5wp.services.PrenotazioniService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
    private final PrenotazioniService prenotazioniService;


    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    //POST PRENOTAZIONE (UTENTE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('UTENTE')")
    public Prenotazione saveEvento(@RequestBody @Validated PrenotazioneDTO payload, BindingResult validateResult, @AuthenticationPrincipal Persona utente) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.prenotazioniService.savePrenotazione(payload, utente);
        }
    }


}
