package naderdeghaili.u5w3d5wp.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.NotFoundException;
import naderdeghaili.u5w3d5wp.payloads.NuovaPersonaDTO;
import naderdeghaili.u5w3d5wp.repositories.PersoneRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PersoneService {
    private final PersoneRepository personeRepository;
    private final PasswordEncoder bcrypt;

    public PersoneService(PersoneRepository personeRepository, PasswordEncoder bcrypt) {
        this.personeRepository = personeRepository;
        this.bcrypt = bcrypt;
    }

    //REGISTRAZIONE
    public Persona savePersona(NuovaPersonaDTO payload) {

        if (personeRepository.existsByEmail(payload.email())) {
            throw new IllegalArgumentException("l'email è già in uso");
        }

        Persona nuovaPersona = new Persona(payload.nomeCompleto(), payload.email(), bcrypt.encode(payload.password()), payload.ruolo());
        log.info("Nuovo " + nuovaPersona.getRuolo() + " registrato con id: " + nuovaPersona.getId());
        return personeRepository.save(nuovaPersona);
    }

    //FIND BY EMAIL PER LOGIN
    public Persona findByEmail(String email) {
        return personeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    //FIND BY ID PER PRENOTAZIONI
    public Persona findById(UUID id) {
        return personeRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

}
