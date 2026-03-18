// Bil.java
package org.example.biluthyrning.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bilar")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Märke är obligatoriskt")
    @Column(nullable = false)
    private String marke;

    @NotBlank(message = "Modell är obligatoriskt")
    @Column(nullable = false)
    private String modell;

    @NotBlank(message = "Färg är obligatoriskt")
    @Column(nullable = false)
    private String farg;

    @Min(value = 1900, message = "Årsmodell måste vara minst 1900")
    @Max(value = 2030, message = "Årsmodell får inte vara senare än 2030")
    @Column(nullable = false)
    private Integer arsmodell;

    @Column(nullable = false)
    private Boolean tillganglig = true;

    @OneToMany(mappedBy = "bil", cascade = CascadeType.ALL)
   // @JsonManagedReference
    private List<Uthyrning> uthyrningar = new ArrayList<>();

    // Konstruktorer
    public Bil() {}

    public Bil(String marke, String modell, String farg, Integer arsmodell) {
        this.marke = marke;
        this.modell = modell;
        this.farg = farg;
        this.arsmodell = arsmodell;
        this.tillganglig = true;
    }

    // Getter och Setter metoder
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarke() { return marke; }
    public void setMarke(String marke) { this.marke = marke; }

    public String getModell() { return modell; }
    public void setModell(String modell) { this.modell = modell; }

    public String getFarg() { return farg; }
    public void setFarg(String farg) { this.farg = farg; }

    public Integer getArsmodell() { return arsmodell; }
    public void setArsmodell(Integer arsmodell) { this.arsmodell = arsmodell; }

    public Boolean getTillganglig() { return tillganglig; }
    public void setTillganglig(Boolean tillganglig) { this.tillganglig = tillganglig; }

    public List<Uthyrning> getUthyrningar() { return uthyrningar; }
    public void setUthyrningar(List<Uthyrning> uthyrningar) { this.uthyrningar = uthyrningar; }
}