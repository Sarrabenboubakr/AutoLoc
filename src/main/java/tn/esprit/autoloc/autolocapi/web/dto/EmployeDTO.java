package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String role;
    private Long agenceId;
}