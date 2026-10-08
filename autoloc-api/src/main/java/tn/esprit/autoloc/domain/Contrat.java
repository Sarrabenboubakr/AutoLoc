package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false)
    private Integer kilometrageDepart;

    @Column(nullable = false)
    private Integer kilometrageRetour;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutContrat statut;

    @Column(nullable = false)
    private Long idReservation;

    @Column(nullable = false)
    private Long idClient;

    @Column(nullable = false)
    private Long idVehicle;

    public enum StatutContrat {
        EN_COURS,
        CLOTURE,
        RESILIE
    }
}