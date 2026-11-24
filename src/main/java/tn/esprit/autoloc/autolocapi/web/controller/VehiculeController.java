package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;
import tn.esprit.autoloc.autolocapi.web.dto.VehiculeDTO;
import tn.esprit.autoloc.autolocapi.web.dto.VehiculeMapper;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper; // <-- Injection du Mapper

    @GetMapping
    public List<VehiculeDTO> findAll() { // <-- Liste de DTO
        List<Vehicule> vehicules = vehiculeService.findAll();
        return vehiculeMapper.toDTOList(vehicules);
    }

    @GetMapping("/{id}")
    public VehiculeDTO findById(@PathVariable Long id) { // <-- DTO
        Vehicule vehicule = vehiculeService.findById(id);
        return vehiculeMapper.toDTO(vehicule);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@Valid @RequestBody VehiculeDTO vehiculeDTO) { // <-- DTO en entrée et sortie
        Vehicule vehicule = vehiculeMapper.fromDTO(vehiculeDTO);
        Vehicule created = vehiculeService.create(vehicule);
        return vehiculeMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public VehiculeDTO update(@PathVariable Long id,@Valid @RequestBody VehiculeDTO vehiculeDTO) { // <-- DTO
        Vehicule vehicule = vehiculeMapper.fromDTO(vehiculeDTO);
        Vehicule updated = vehiculeService.update(id, vehicule);
        return vehiculeMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("/desaffecter/{id}")
    public VehiculeDTO desaffecter(@PathVariable Long id) { // <-- DTO
        Vehicule vehicule = vehiculeService.desaffecter(id);
        return vehiculeMapper.toDTO(vehicule);
    }

    @PutMapping("/{idVehicule}/agence/{idAgence}")
    public VehiculeDTO affecter(@PathVariable Long idVehicule, @PathVariable Long idAgence) { // <-- DTO
        Vehicule vehicule = vehiculeService.affecterAgence(idVehicule, idAgence);
        return vehiculeMapper.toDTO(vehicule);
    }

    @PutMapping("/{idVehicule}/equipement/{idEquipement}")
    public VehiculeDTO affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) { // <-- DTO
        Vehicule vehicule = vehiculeService.affecterEquipement(idVehicule, idEquipement);
        return vehiculeMapper.toDTO(vehicule);
    }
}