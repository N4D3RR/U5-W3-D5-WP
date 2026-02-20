package naderdeghaili.u5w3d5wp.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull UUID eventoId
) {
}
