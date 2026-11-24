package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;
import tn.esprit.autoloc.autolocapi.web.dto.EquipementDTO;
import tn.esprit.autoloc.autolocapi.web.dto.EquipementMapper;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;
    private final EquipementMapper equipementMapper;

    @GetMapping
    public List<EquipementDTO> findAll() {
        List<Equipement> equipements = equipementService.findAll();
        return equipementMapper.toDTOList(equipements);
    }

    @GetMapping("/{id}")
    public EquipementDTO findById(@PathVariable Long id) {
        Equipement equipement = equipementService.findById(id);
        return equipementMapper.toDTO(equipement);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO create(@RequestBody EquipementDTO equipementDTO) {
        Equipement equipement = equipementMapper.fromDTO(equipementDTO);
        Equipement created = equipementService.create(equipement);
        return equipementMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public EquipementDTO update(@PathVariable Long id, @RequestBody EquipementDTO equipementDTO) {
        Equipement equipement = equipementMapper.fromDTO(equipementDTO);
        Equipement updated = equipementService.update(id, equipement);
        return equipementMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}