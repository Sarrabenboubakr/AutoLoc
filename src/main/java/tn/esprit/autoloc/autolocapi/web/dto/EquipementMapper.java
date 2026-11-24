package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipementMapper {
    EquipementDTO toDTO(Equipement equipement);
    Equipement fromDTO(EquipementDTO equipementDTO);
    List<EquipementDTO> toDTOList(List<Equipement> equipements);
}