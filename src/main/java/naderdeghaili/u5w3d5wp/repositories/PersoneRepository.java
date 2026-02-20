package naderdeghaili.u5w3d5wp.repositories;

import naderdeghaili.u5w3d5wp.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersoneRepository extends JpaRepository<Persona, UUID> {

    Optional<Persona> findByEmail(String email);

    boolean existsByEmail(String email);


}
