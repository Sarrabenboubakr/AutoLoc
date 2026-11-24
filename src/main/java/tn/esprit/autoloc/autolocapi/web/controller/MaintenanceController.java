package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;
import tn.esprit.autoloc.autolocapi.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.web.dto.MaintenanceMapper;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    @GetMapping
    public List<MaintenanceDTO> findAll() {
        List<Maintenance> maintenances = maintenanceService.findAll();
        return maintenanceMapper.toDTOList(maintenances);
    }

    @GetMapping("/{id}")
    public MaintenanceDTO findById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.findById(id);
        return maintenanceMapper.toDTO(maintenance);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO create(@RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = maintenanceMapper.fromDTO(maintenanceDTO);
        Maintenance created = maintenanceService.create(maintenance);
        return maintenanceMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public MaintenanceDTO update(@PathVariable Long id, @RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = maintenanceMapper.fromDTO(maintenanceDTO);
        Maintenance updated = maintenanceService.update(id, maintenance);
        return maintenanceMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}