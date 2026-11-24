package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {
    ContratDTO toDTO(Contrat contrat);
    Contrat fromDTO(ContratDTO contratDTO);
    List<ContratDTO> toDTOList(List<Contrat> contrats);
}