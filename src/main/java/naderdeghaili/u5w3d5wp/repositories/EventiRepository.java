package naderdeghaili.u5w3d5wp.repositories;

import naderdeghaili.u5w3d5wp.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventiRepository extends JpaRepository<Evento, UUID> {

    List<Evento> findByDataEvento(LocalDate dataEvento);
}
