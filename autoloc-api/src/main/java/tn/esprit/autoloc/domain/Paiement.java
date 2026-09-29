package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    @Column(nullable = false)
    private LocalDateTime datePaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ModePaiement mode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutPaiement statut;

    @Column(nullable = false)
    private Long idReservation;

    public enum ModePaiement {
        CARTE_BANCAIRE,
        ESPECES,
        VIREMENT,
        CHEQUE
    }

    public enum StatutPaiement {
        EN_ATTENTE,
        VALIDE,
        REFUSE,
        REMBOURSE
    }
}