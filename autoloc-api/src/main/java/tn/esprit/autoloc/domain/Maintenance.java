package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaintenance;

    @Column(nullable = false)
    private LocalDate dateMaintenance;

    @Column(nullable = false)
    private LocalDate dateProchaineMaintenance;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cout;

    @Column(nullable = false, length = 100)
    private String garage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TypeMaintenance type;

    @Column(nullable = false)
    private Long idVehicle;

    public enum TypeMaintenance {
        PREVENTIVE,
        CORRECTIVE,
        REVISION,
        CONTROLE_TECHNIQUE
    }
}