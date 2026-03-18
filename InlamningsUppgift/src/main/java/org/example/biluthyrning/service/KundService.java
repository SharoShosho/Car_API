// KundService.java
package org.example.biluthyrning.service;

import org.example.biluthyrning.model.Kund;
import org.example.biluthyrning.repository.KundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KundService {

    @Autowired
    private KundRepository kundRepository;

    public List<Kund> hämtaAllaKunder() {
        return kundRepository.findAll();
    }

    public Optional<Kund> hämtaKund(Long id) {
        return kundRepository.findById(id);
    }

    public Kund sparaKund(Kund kund) {
        // Kontrollera om e-post redan finns
        if (kundRepository.existsByEpost(kund.getEpost())) {
            throw new RuntimeException("En kund med denna e-postadress finns redan");
        }
        return kundRepository.save(kund);
    }

    public Kund uppdateraKund(Long id, Kund kundDetaljer) {
        return kundRepository.findById(id)
                .map(kund -> {
                    kund.setFornamn(kundDetaljer.getFornamn());
                    kund.setEfternamn(kundDetaljer.getEfternamn());
                    kund.setEpost(kundDetaljer.getEpost());
                    kund.setTelefon(kundDetaljer.getTelefon());
                    return kundRepository.save(kund);
                })
                .orElse(null);
    }

    public boolean taBortKund(Long id) {
        if (kundRepository.existsById(id)) {
            kundRepository.deleteById(id);
            return true;
        }
        return false;
    }
}