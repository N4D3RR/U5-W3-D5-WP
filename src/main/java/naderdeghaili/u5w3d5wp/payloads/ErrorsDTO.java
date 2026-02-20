package naderdeghaili.u5w3d5wp.payloads;


import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timeStamp) {
}
