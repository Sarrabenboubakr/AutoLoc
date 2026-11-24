package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeMapper {

    @Mapping(source = "agence.id", target = "agenceId")
    EmployeDTO toDTO(Employe employe);

    @Mapping(target = "agence", ignore = true)
    Employe fromDTO(EmployeDTO employeDTO);

    List<EmployeDTO> toDTOList(List<Employe> employes);
}