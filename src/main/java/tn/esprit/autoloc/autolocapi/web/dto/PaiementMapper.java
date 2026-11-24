package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
    PaiementDTO toDTO(Paiement paiement);
    List<PaiementDTO> toDTOList(List<Paiement> paiements);
}