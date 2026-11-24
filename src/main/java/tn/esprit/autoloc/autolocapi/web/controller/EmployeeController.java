package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;
import tn.esprit.autoloc.autolocapi.web.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.web.dto.EmployeMapper;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
class EmployeController { // <-- J'ai mis public

    private final IEmployeService employeService;
    private final EmployeMapper employeMapper;

    @GetMapping
    public List<EmployeDTO> findAll() {
        List<Employe> employes = employeService.findAll();
        return employeMapper.toDTOList(employes);
    }

    @GetMapping("/{id}")
    public EmployeDTO findById(@PathVariable Long id) {
        Employe employe = employeService.findById(id);
        return employeMapper.toDTO(employe);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO create(@RequestBody EmployeDTO employeDTO) {
        Employe employe = employeMapper.fromDTO(employeDTO);
        Employe created = employeService.create(employe);
        return employeMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @RequestBody EmployeDTO employeDTO) {
        Employe employe = employeMapper.fromDTO(employeDTO);
        Employe updated = employeService.update(id, employe);
        return employeMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}