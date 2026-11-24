package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {
    private Long id;
    private String immatriculation;
    private String marque;
    private String modele;
    private Integer annee;
    private String couleur;
    private Integer kilometrage;
    private BigDecimal prixParJour;
    private String statut;
    private String categorie;
    private Long agenceId; // On expose juste l'ID de l'agence, pas l'agence entière
}