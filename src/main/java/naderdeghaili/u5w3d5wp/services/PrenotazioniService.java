package naderdeghaili.u5w3d5wp.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d5wp.entities.Evento;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.entities.Prenotazione;
import naderdeghaili.u5w3d5wp.payloads.PrenotazioneDTO;
import naderdeghaili.u5w3d5wp.repositories.PrenotazioniRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;
    private final EventiService eventiService;


    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, EventiService eventiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.eventiService = eventiService;
    }

    //POST PRENOTAZIONE (UTENTE)
    public Prenotazione savePrenotazione(PrenotazioneDTO payload, Persona utente) {

        Evento evento = eventiService.findById(payload.eventoId());

        //controllo se ha già prenotato questo evento
        if (prenotazioniRepository.existsByEvento_IdAndPersona_Id(evento.getId(), utente.getId())) {
            throw new IllegalArgumentException("Hai già prenotato questo evento");
        }

        //controllo se l'evento è al completo
        long prenotazioniAttuali = prenotazioniRepository.countByEvento_Id(evento.getId());
        if (prenotazioniAttuali >= evento.getPosti()) {
            throw new IllegalArgumentException("L'evento è al completo");
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(evento, utente);
        return prenotazioniRepository.save(nuovaPrenotazione);

    }

}
