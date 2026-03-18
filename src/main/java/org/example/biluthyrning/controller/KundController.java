// KundController.java
package org.example.biluthyrning.controller;

import org.example.biluthyrning.model.Kund;
import org.example.biluthyrning.service.KundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kunder")
@Validated
@Tag(name = "Kund Management", description = "API för hantering av kunder")
public class KundController {

    @Autowired
    private KundService kundService;

    @GetMapping
    @Operation(summary = "Hämta alla kunder")
    public ResponseEntity<List<Kund>> hämtaAllaKunder() {
        List<Kund> kunder = kundService.hämtaAllaKunder();
        return ResponseEntity.ok(kunder);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Hämta kund med ID")
    public ResponseEntity<Kund> hämtaKund(@PathVariable Long id) {
        Optional<Kund> kund = kundService.hämtaKund(id);
        return kund.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Skapa ny kund")
    public ResponseEntity<?> skapaKund(@Valid @RequestBody Kund kund) {
        try {
            Kund sparadKund = kundService.sparaKund(kund);
            return ResponseEntity.status(HttpStatus.CREATED).body(sparadKund);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internt serverfel: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Uppdatera kund")
    public ResponseEntity<Kund> uppdateraKund(@PathVariable Long id, @Valid @RequestBody Kund kund) {
        Kund uppdateradKund = kundService.uppdateraKund(id, kund);
        if (uppdateradKund != null) {
            return ResponseEntity.ok(uppdateradKund);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Ta bort kund")
    public ResponseEntity<?> taBortKund(@PathVariable Long id) {
        boolean borttagen = kundService.taBortKund(id);
        if (borttagen) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}