package naderdeghaili.u5w3d5wp.repositories;

import naderdeghaili.u5w3d5wp.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    //controllo doppia prenotazione utente
    boolean existsByEvento_IdAndPersona_Id(UUID eventoId, UUID personaId);

    //conto prenotazioni evento per posti disponibili
    long countByEvento_Id(UUID id);

    //lista prenotazioni utente
    List<Prenotazione> findByPersona_Id(UUID personaId);


}
