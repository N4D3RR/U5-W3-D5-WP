package naderdeghaili.u5w3d5wp.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    public Prenotazione() {
    }

    public Prenotazione(Evento evento, Persona persona) {
        this.data = LocalDate.now();
        this.evento = evento;
        this.persona = persona;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Prenotazione: " +
                "id: " + id +
                " | data: " + data +
                " | evento: " + evento.getTitolo() +
                " | persona: " + persona.getNomeCompleto()
                ;
    }
}
