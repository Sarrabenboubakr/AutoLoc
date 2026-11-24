package tn.esprit.autoloc.autolocapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {
    private Long id;
    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private Boolean valide;
}