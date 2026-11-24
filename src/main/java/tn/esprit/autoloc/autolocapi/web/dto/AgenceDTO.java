package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgenceDTO {
    private Long id;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
    private Integer effectif;
}