package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(source = "agence.id", target = "agenceId")
    VehiculeDTO toDTO(Vehicule vehicule);

    @Mapping(target = "agence", ignore = true) // L'agence sera gérée par le service
    Vehicule fromDTO(VehiculeDTO vehiculeDTO);

    List<VehiculeDTO> toDTOList(List<Vehicule> vehicules);
}