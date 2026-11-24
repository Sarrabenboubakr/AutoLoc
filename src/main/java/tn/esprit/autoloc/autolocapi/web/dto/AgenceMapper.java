package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Agence;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgenceMapper {

    // Convertit une entité Agence en AgenceDTO
    AgenceDTO toDTO(Agence agence);

    // Convertit un AgenceDTO en entité Agence
    Agence fromDTO(AgenceDTO agenceDTO);

    // Convertit une liste d'Agences en liste d'AgenceDTO
    List<AgenceDTO> toDTOList(List<Agence> agences);
    List<Agence> fromDTOList(List<AgenceDTO> agencesDTO);
}