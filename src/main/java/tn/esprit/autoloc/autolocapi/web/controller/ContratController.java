package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.service.IContratService;
import tn.esprit.autoloc.autolocapi.web.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.web.dto.ContratMapper;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;
    private final ContratMapper contratMapper;

    @GetMapping
    public List<ContratDTO> findAll() {
        List<Contrat> contrats = contratService.findAll();
        return contratMapper.toDTOList(contrats);
    }

    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) {
        Contrat contrat = contratService.findById(id);
        return contratMapper.toDTO(contrat);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO create(@RequestBody ContratDTO contratDTO) {
        Contrat contrat = contratMapper.fromDTO(contratDTO);
        Contrat created = contratService.create(contrat);
        return contratMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id, @RequestBody ContratDTO contratDTO) {
        Contrat contrat = contratMapper.fromDTO(contratDTO);
        Contrat updated = contratService.update(id, contrat);
        return contratMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}