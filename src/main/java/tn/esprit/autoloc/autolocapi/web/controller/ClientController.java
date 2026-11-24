package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;
import tn.esprit.autoloc.autolocapi.web.dto.ClientDTO;
import tn.esprit.autoloc.autolocapi.web.dto.ClientMapper;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public List<ClientDTO> findAll() {
        List<Client> clients = clientService.findAll();
        return clientMapper.toDTOList(clients);
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return clientMapper.toDTO(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.fromDTO(clientDTO);
        Client created = clientService.create(client);
        return clientMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id,@Valid @RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.fromDTO(clientDTO);
        Client updated = clientService.update(id, client);
        return clientMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}