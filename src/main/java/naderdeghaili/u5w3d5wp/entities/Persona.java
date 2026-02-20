package naderdeghaili.u5w3d5wp.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "persone")
public class Persona {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nomeCompleto;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Persona() {
    }

    public Persona(String nomeCompleto, String email, String password, Ruolo ruolo) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public UUID getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }


    @Override
    public String toString() {
        return "Persona: " +
                "id: " + id +
                " | nomeCompleto: " + nomeCompleto +
                " | email: " + email +
                " | password: " + password +
                " | ruolo: " + ruolo +
                '}';
    }
}
