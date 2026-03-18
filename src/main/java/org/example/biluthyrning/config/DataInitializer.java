// DataInitializer.java
package org.example.biluthyrning.config;

import org.example.biluthyrning.model.Bil;
import org.example.biluthyrning.model.Kund;
import org.example.biluthyrning.repository.BilRepository;
import org.example.biluthyrning.repository.KundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BilRepository bilRepository;

    @Autowired
    private KundRepository kundRepository;

    @Override
    public void run(String... args) throws Exception {
        // Lägg till testdata om databasen är tom
        if (bilRepository.count() == 0) {
            bilRepository.save(new Bil("Volvo", "XC60", "Svart", 2022));
            bilRepository.save(new Bil("BMW", "X3", "Vit", 2023));
            bilRepository.save(new Bil("Audi", "A4", "Blå", 2021));
            bilRepository.save(new Bil("Tesla", "Model 3", "Röd", 2023));
        }

        if (kundRepository.count() == 0) {
            kundRepository.save(new Kund("Sharo", "Hawraman", "shahaw@example.com", "0701122334"));
            kundRepository.save(new Kund("Erik", "Eriksson", "erik@example.com", "0701234567"));
            kundRepository.save(new Kund("Maria", "Svensson", "maria.svensson@example.com", "0707654321"));
        }
    }
}