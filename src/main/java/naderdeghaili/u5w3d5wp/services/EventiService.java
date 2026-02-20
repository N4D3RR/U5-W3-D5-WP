package naderdeghaili.u5w3d5wp.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w3d5wp.entities.Evento;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.NotFoundException;
import naderdeghaili.u5w3d5wp.exceptions.UnauthorizedException;
import naderdeghaili.u5w3d5wp.payloads.NuovoEventoDTO;
import naderdeghaili.u5w3d5wp.repositories.EventiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EventiService {

    private final EventiRepository eventiRepository;


    public EventiService(EventiRepository eventiRepository) {
        this.eventiRepository = eventiRepository;
    }

    //GET LISTA EVENTI
    public Page<Evento> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.eventiRepository.findAll(pageable);
    }

    //GET EVENTO BY ID
    public Evento findById(UUID eventoId) {
        return eventiRepository.findById(eventoId).orElseThrow(() -> new NotFoundException("Evento con id: " + eventoId + " non trovato"));
    }

    //POST EVENTO (ORGANIZZATORE)
    public Evento saveEvento(NuovoEventoDTO payload, Persona organizzatore) {
        Evento nuovoEvento = new Evento(payload.titolo(), payload.descrizione(), payload.dataEvento(), payload.location(), payload.posti(), organizzatore);
        log.info("Nuovo evento " + nuovoEvento.getTitolo() + "creato con id: " + nuovoEvento.getId());
        return eventiRepository.save(nuovoEvento);
    }

    //PUT EVENTO (ORGANIZZATORE)
    public Evento findByIdAndUpdate(UUID eventoId, NuovoEventoDTO payload, Persona organizzatore) {

        Evento found = this.findById(eventoId);
        if (!found.getOrganizzatore().getId().equals(organizzatore.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a modificare questo evento");
        }

        found.setTitolo(payload.titolo());
        found.setDescrizione(payload.descrizione());
        found.setDataEvento(payload.dataEvento());
        found.setLocation(payload.location());
        found.setPosti(payload.posti());

        Evento eventoModificato = eventiRepository.save(found);
        log.info("Evento modificato");
        return eventoModificato;

    }

    //DELETE EVENTO (ORGANIZZATORE)
    public void findByIdAndDelete(UUID eventoId, Persona organizzatore) {
        Evento found = this.findById(eventoId);
        if (!found.getOrganizzatore().getId().equals(organizzatore.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo evento");
        }
        eventiRepository.delete(found);
        log.info("Evento " + found.getTitolo() + " eliminato");
    }
}
