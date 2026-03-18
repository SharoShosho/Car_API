// UthyrningService.java
package org.example.biluthyrning.service;

import org.example.biluthyrning.model.Bil;
import org.example.biluthyrning.model.Kund;
import org.example.biluthyrning.model.Uthyrning;
import org.example.biluthyrning.repository.BilRepository;
import org.example.biluthyrning.repository.KundRepository;
import org.example.biluthyrning.repository.UthyrningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UthyrningService {

    @Autowired
    private UthyrningRepository uthyrningRepository;

    @Autowired
    private KundRepository kundRepository;

    @Autowired
    private BilRepository bilRepository;

    public List<Uthyrning> hämtaAllaUthyrningar() {
        return uthyrningRepository.findAll();
    }

    public Optional<Uthyrning> hämtaUthyrning(Long id) {
        return uthyrningRepository.findById(id);
    }

    public List<Uthyrning> hämtaUthyrningarForKund(Long kundId) {
        return uthyrningRepository.findByKundId(kundId);
    }

    public List<Uthyrning> hämtaAktivaUthyrningar() {
        return uthyrningRepository.findByAktivTrue();
    }

    public Uthyrning skapaUthyrning(Uthyrning uthyrning) {
        // Validering: Kontrollera att kund och bil finns
        Kund kund = kundRepository.findById(uthyrning.getKund().getId())
                .orElseThrow(() -> new RuntimeException("Kund inte funnen"));

        Bil bil = bilRepository.findById(uthyrning.getBil().getId())
                .orElseThrow(() -> new RuntimeException("Bil inte funnen"));

        // Validering: Kontrollera att bilen är tillgänglig
        if (!bil.getTillganglig()) {
            throw new RuntimeException("Bilen är inte tillgänglig för uthyrning");
        }

        // Validering: Kontrollera datumlogik
        if (uthyrning.getStartDatum().isBefore(LocalDate.now())) {
            throw new RuntimeException("Startdatum får inte vara i det förflutna");
        }

        if (uthyrning.getSlutDatum().isBefore(uthyrning.getStartDatum())) {
            throw new RuntimeException("Slutdatum får inte vara före startdatum");
        }

        // Spara uthyrning och uppdatera bilens status
        uthyrning.setKund(kund);
        uthyrning.setBil(bil);
        uthyrning.setAktiv(true);

        Uthyrning sparadUthyrning = uthyrningRepository.save(uthyrning);

        // Uppdatera bilens status
        bil.setTillganglig(false);
        bilRepository.save(bil);

        return sparadUthyrning;
    }

    public Uthyrning avslutaUthyrning(Long id) {
        return uthyrningRepository.findById(id)
                .map(uthyrning -> {
                    uthyrning.setAktiv(false);

                    // Återställ bilens status
                    Bil bil = uthyrning.getBil();
                    bil.setTillganglig(true);
                    bilRepository.save(bil);

                    return uthyrningRepository.save(uthyrning);
                })
                .orElse(null);
    }

    public boolean taBortUthyrning(Long id) {
        if (uthyrningRepository.existsById(id)) {
            uthyrningRepository.deleteById(id);
            return true;
        }
        return false;
    }
}