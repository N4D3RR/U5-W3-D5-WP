package naderdeghaili.u5w3d5wp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private LocalDate dataEvento;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int posti;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Persona organizzatore;

    public Evento() {
    }

    public Evento(String titolo, String descrizione, LocalDate dataEvento, String location, int posti, Persona organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataEvento = dataEvento;
        this.location = location;
        this.posti = posti;
        this.organizzatore = organizzatore;
    }

    public UUID getId() {
        return id;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

    public Persona getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Persona organizzatore) {
        this.organizzatore = organizzatore;
    }

    @Override
    public String toString() {
        return "Evento: " +
                "id: " + id +
                " | titolo: " + titolo +
                " | descrizione: " + descrizione +
                " | dataEvento: " + dataEvento +
                " | location: " + location +
                " | posti: " + posti +
                " | organizzatore: " + organizzatore.getNomeCompleto();
    }
}
