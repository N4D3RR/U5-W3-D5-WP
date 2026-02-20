package naderdeghaili.u5w3d5wp.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import naderdeghaili.u5w3d5wp.entities.Evento;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.ValidationException;
import naderdeghaili.u5w3d5wp.payloads.NuovoEventoDTO;
import naderdeghaili.u5w3d5wp.services.EventiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/eventi")
public class EventiController {

    private final EventiService eventiService;


    public EventiController(EventiService eventiService) {
        this.eventiService = eventiService;
    }

    //GET EVENTI
    @GetMapping
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                @RequestParam(defaultValue = "15") @Min(0) @Max(15) int size) {
        return this.eventiService.findAll(page, size);
    }

    //GET EVENTO
    @GetMapping("/{eventoId}")
    public Evento getEventobyId(@PathVariable UUID eventoId) {
        return this.eventiService.findById(eventoId);
    }

    //POST EVENTO (ORGANIZZATORE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento saveEvento(@RequestBody @Validated NuovoEventoDTO payload, BindingResult validateResult, @AuthenticationPrincipal Persona organizzatore) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.eventiService.saveEvento(payload, organizzatore);
        }
    }

    //PUT EVENTO (ORGANIZZATORE)
    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento updateEvento(@PathVariable UUID eventoId,
                               @RequestBody @Validated NuovoEventoDTO payload,
                               BindingResult validateResult,
                               @AuthenticationPrincipal Persona organizzatore) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return this.eventiService.findByIdAndUpdate(eventoId, payload, organizzatore);
        }

    }

    //DELETE EVENTO (ORGANIZZATORE)
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void deleteEvento(@PathVariable UUID eventoId, @AuthenticationPrincipal Persona organizzatore) {
        this.eventiService.findByIdAndDelete(eventoId, organizzatore);
    }


}
