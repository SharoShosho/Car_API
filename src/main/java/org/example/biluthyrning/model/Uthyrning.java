// Uthyrning.java
package org.example.biluthyrning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "uthyrningar")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Uthyrning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kund_id", nullable = false)
    //@JsonBackReference
    private Kund kund;

    @ManyToOne
    @JoinColumn(name = "bil_id", nullable = false)
   // @JsonBackReference
    private Bil bil;

    @NotNull(message = "Startdatum är obligatoriskt")
    @Column(nullable = false)
    private LocalDate startDatum;

    @NotNull(message = "Slutdatum är obligatoriskt")
    @Column(nullable = false)
    private LocalDate slutDatum;

    @Column(nullable = false)
    private Boolean aktiv = true;

    // Konstruktorer
    public Uthyrning() {}

    public Uthyrning(Kund kund, Bil bil, LocalDate startDatum, LocalDate slutDatum) {
        this.kund = kund;
        this.bil = bil;
        this.startDatum = startDatum;
        this.slutDatum = slutDatum;
        this.aktiv = true;
    }

    // Getter och Setter metoder
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Kund getKund() { return kund; }
    public void setKund(Kund kund) { this.kund = kund; }

    public Bil getBil() { return bil; }
    public void setBil(Bil bil) { this.bil = bil; }

    public LocalDate getStartDatum() { return startDatum; }
    public void setStartDatum(LocalDate startDatum) { this.startDatum = startDatum; }

    public LocalDate getSlutDatum() { return slutDatum; }
    public void setSlutDatum(LocalDate slutDatum) { this.slutDatum = slutDatum; }

    public Boolean getAktiv() { return aktiv; }
    public void setAktiv(Boolean aktiv) { this.aktiv = aktiv; }
}