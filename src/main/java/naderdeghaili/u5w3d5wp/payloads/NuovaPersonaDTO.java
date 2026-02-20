package naderdeghaili.u5w3d5wp.payloads;

import jakarta.validation.constraints.*;
import naderdeghaili.u5w3d5wp.entities.Ruolo;


public record NuovaPersonaDTO(
        @NotBlank(message = "il nome è obbligatorio") @Size(min = 2, max = 30, message = "il nome deve essere compreso tra 2 e 30 caratteri") String nomeCompleto,
        @NotBlank(message = "la mail è obbligatoria") @Email(message = "la mail non è nel formato corretto") String email,
        @NotBlank(message = "La password è obbligatoria") @Size(min = 4, message = "La password deve avere almeno 4 caratteri") @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "La password deve contenere almeno una maiuscola, una minuscola e un numero") String password,
        @NotNull Ruolo ruolo) {
}
