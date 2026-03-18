// BilService.java
package org.example.biluthyrning.service;

import org.example.biluthyrning.model.Bil;
import org.example.biluthyrning.repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BilService {

    @Autowired
    private BilRepository bilRepository;

    public List<Bil> hämtaAllaBilar() {
        return bilRepository.findAll();
    }

    public Optional<Bil> hämtaBil(Long id) {
        return bilRepository.findById(id);
    }

    public List<Bil> hämtaTillgängligaBilar() {
        return bilRepository.findByTillgangligTrue();
    }

    public Bil sparaBil(Bil bil) {
        return bilRepository.save(bil);
    }

    public Bil uppdateraBil(Long id, Bil bilDetaljer) {
        return bilRepository.findById(id)
                .map(bil -> {
                    bil.setMarke(bilDetaljer.getMarke());
                    bil.setModell(bilDetaljer.getModell());
                    bil.setFarg(bilDetaljer.getFarg());
                    bil.setArsmodell(bilDetaljer.getArsmodell());
                    bil.setTillganglig(bilDetaljer.getTillganglig());
                    return bilRepository.save(bil);
                })
                .orElse(null);
    }

    public boolean taBortBil(Long id) {
        if (bilRepository.existsById(id)) {
            bilRepository.deleteById(id);
            return true;
        }
        return false;
    }
}