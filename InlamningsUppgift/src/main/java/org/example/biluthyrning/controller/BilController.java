// BilController.java
package org.example.biluthyrning.controller;

import org.example.biluthyrning.model.Bil;
import org.example.biluthyrning.service.BilService;
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
@RequestMapping("/api/bilar")
@Validated
@Tag(name = "Bil Management", description = "API för hantering av bilar")
public class BilController {

    @Autowired
    private BilService bilService;

    @GetMapping
    @Operation(summary = "Hämta alla bilar")
    public ResponseEntity<List<Bil>> hämtaAllaBilar() {
        List<Bil> bilar = bilService.hämtaAllaBilar();
        return ResponseEntity.ok(bilar);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Hämta bil med ID")
    public ResponseEntity<Bil> hämtaBil(@PathVariable Long id) {
        Optional<Bil> bil = bilService.hämtaBil(id);
        return bil.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tillgangliga")
    @Operation(summary = "Hämta tillgängliga bilar")
    public ResponseEntity<List<Bil>> hämtaTillgängligaBilar() {
        List<Bil> bilar = bilService.hämtaTillgängligaBilar();
        return ResponseEntity.ok(bilar);
    }

    @PostMapping
    @Operation(summary = "Skapa ny bil")
    public ResponseEntity<?> skapaBil(@Valid @RequestBody Bil bil) {
        try {
            Bil sparadBil = bilService.sparaBil(bil);
            return ResponseEntity.status(HttpStatus.CREATED).body(sparadBil);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fel vid skapande av bil: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Uppdatera bil")
    public ResponseEntity<Bil> uppdateraBil(@PathVariable Long id, @Valid @RequestBody Bil bil) {
        Bil uppdateradBil = bilService.uppdateraBil(id, bil);
        if (uppdateradBil != null) {
            return ResponseEntity.ok(uppdateradBil);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Ta bort bil")
    public ResponseEntity<?> taBortBil(@PathVariable Long id) {
        boolean borttagen = bilService.taBortBil(id);
        if (borttagen) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}