package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
    MaintenanceDTO toDTO(Maintenance maintenance);
    Maintenance fromDTO(MaintenanceDTO maintenanceDTO);
    List<MaintenanceDTO> toDTOList(List<Maintenance> maintenances);
}