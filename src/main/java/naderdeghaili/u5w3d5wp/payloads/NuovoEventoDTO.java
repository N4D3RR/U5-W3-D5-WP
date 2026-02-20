package naderdeghaili.u5w3d5wp.payloads;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NuovoEventoDTO(
        @NotBlank(message = "il titolo è obbligatorio") @Size(min = 2, max = 30, message = "il titolo deve essere compreso tra 2 e 30 caratteri") String titolo,
        @NotBlank(message = "la descrizione è obbligatoria") String descrizione,
        @NotNull(message = "la data è obbligatoria") LocalDate dataEvento,
        @NotBlank(message = "la location è obbligatoria") String location,
        @NotNull(message = "il numero di posti è obbligatorio") @Min(value = 1, message = "l'evento deve avere almeno un posto") int posti
) {
}
