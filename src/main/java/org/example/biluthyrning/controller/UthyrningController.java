// UthyrningController.java
package org.example.biluthyrning.controller;

import org.example.biluthyrning.model.Uthyrning;
import org.example.biluthyrning.service.UthyrningService;
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
@RequestMapping("/api/uthyrningar")
@Validated
@Tag(name = "Uthyrning Management", description = "API för hantering av uthyrningar")
public class UthyrningController {

    @Autowired
    private UthyrningService uthyrningService;

    @GetMapping
    @Operation(summary = "Hämta alla uthyrningar")
    public ResponseEntity<List<Uthyrning>> hämtaAllaUthyrningar() {
        List<Uthyrning> uthyrningar = uthyrningService.hämtaAllaUthyrningar();
        return ResponseEntity.ok(uthyrningar);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Hämta uthyrning med ID")
    public ResponseEntity<Uthyrning> hämtaUthyrning(@PathVariable Long id) {
        Optional<Uthyrning> uthyrning = uthyrningService.hämtaUthyrning(id);
        return uthyrning.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/kund/{kundId}")
    @Operation(summary = "Hämta uthyrningar för kund")
    public ResponseEntity<List<Uthyrning>> hämtaUthyrningarForKund(@PathVariable Long kundId) {
        List<Uthyrning> uthyrningar = uthyrningService.hämtaUthyrningarForKund(kundId);
        return ResponseEntity.ok(uthyrningar);
    }

    @GetMapping("/aktiva")
    @Operation(summary = "Hämta aktiva uthyrningar")
    public ResponseEntity<List<Uthyrning>> hämtaAktivaUthyrningar() {
        List<Uthyrning> uthyrningar = uthyrningService.hämtaAktivaUthyrningar();
        return ResponseEntity.ok(uthyrningar);
    }

    @PostMapping
    @Operation(summary = "Skapa ny uthyrning")
    public ResponseEntity<?> skapaUthyrning(@Valid @RequestBody Uthyrning uthyrning) {
        try {
            Uthyrning sparadUthyrning = uthyrningService.skapaUthyrning(uthyrning);
            return ResponseEntity.status(HttpStatus.CREATED).body(sparadUthyrning);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internt serverfel: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/avsluta")
    @Operation(summary = "Avsluta uthyrning")
    public ResponseEntity<Uthyrning> avslutaUthyrning(@PathVariable Long id) {
        Uthyrning avslutadUthyrning = uthyrningService.avslutaUthyrning(id);
        if (avslutadUthyrning != null) {
            return ResponseEntity.ok(avslutadUthyrning);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Ta bort uthyrning")
    public ResponseEntity<?> taBortUthyrning(@PathVariable Long id) {
        boolean borttagen = uthyrningService.taBortUthyrning(id);
        if (borttagen) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}