package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Client;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client fromDTO(ClientDTO clientDTO);
    List<ClientDTO> toDTOList(List<Client> clients);
}