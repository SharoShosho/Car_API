// Kund.java
package org.example.biluthyrning.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kunder")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Kund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Förnamn är obligatoriskt")
    @Column(nullable = false)
    private String fornamn;

    @NotBlank(message = "Efternamn är obligatoriskt")
    @Column(nullable = false)
    private String efternamn;

    @Email(message = "Ogiltig e-postadress")
    @NotBlank(message = "E-post är obligatoriskt")
    @Column(nullable = false, unique = true)
    private String epost;

    @Pattern(regexp = "\\d{10}", message = "Telefonnummer måste vara 10 siffror")
    @Column(nullable = false)
    private String telefon;

    @OneToMany(mappedBy = "kund", cascade = CascadeType.ALL)
   // @JsonManagedReference
    private List<Uthyrning> uthyrningar = new ArrayList<>();

    // Konstruktorer
    public Kund() {}

    public Kund(String fornamn, String efternamn, String epost, String telefon) {
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.epost = epost;
        this.telefon = telefon;
    }

    // Getter och Setter metoder
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFornamn() { return fornamn; }
    public void setFornamn(String fornamn) { this.fornamn = fornamn; }

    public String getEfternamn() { return efternamn; }
    public void setEfternamn(String efternamn) { this.efternamn = efternamn; }

    public String getEpost() { return epost; }
    public void setEpost(String epost) { this.epost = epost; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public List<Uthyrning> getUthyrningar() { return uthyrningar; }
    public void setUthyrningar(List<Uthyrning> uthyrningar) { this.uthyrningar = uthyrningar; }
}