package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssurance;

    @Column(nullable = false, length = 100)
    private String compagnie;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroContrat;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal primeAnnuelle;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal franchise;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TypeAssurance type;

    @Column(nullable = false)
    private Long idVehicle;

    public enum TypeAssurance {
        TOUS_RISQUES,
        TIERS,
        TIERS_PLUS,
        VOL_INCENDIE
    }
}