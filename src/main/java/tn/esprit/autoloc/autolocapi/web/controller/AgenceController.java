package tn.esprit.autoloc.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.calcul.VehiculeOffre;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;
import tn.esprit.autoloc.autolocapi.web.dto.AgenceDTO;
import tn.esprit.autoloc.autolocapi.web.dto.AgenceMapper;

import java.util.List;

@Tag(name = "Agences", description = "Gestion des agences de location")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;
    private final AgenceMapper agenceMapper; // <-- Injection du Mapper

    @Operation(summary = "Lister toutes les agences")
    @GetMapping
    public List<AgenceDTO> findAll() { // <-- Liste de DTO
        List<Agence> agences = agenceService.findAll();
        return agenceMapper.toDTOList(agences); // <-- Utilisation du Mapper
    }

    @Operation(summary = "Consulter une agence par son ID")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{id}")
    public AgenceDTO findById(@Parameter(description = "ID de l'agence") @PathVariable Long id) { // <-- DTO
        Agence agence = agenceService.findById(id);
        return agenceMapper.toDTO(agence); // <-- Utilisation du Mapper
    }

    @Operation(summary = "Créer une nouvelle agence")
    @ApiResponse(responseCode = "201", description = "Agence créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO create(@RequestBody AgenceDTO agenceDTO) { // <-- DTO en paramètre et en retour
        Agence agence = agenceMapper.fromDTO(agenceDTO);
        Agence created = agenceService.create(agence);
        return agenceMapper.toDTO(created);
    }

    @Operation(summary = "Modifier une agence existante")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PutMapping("/{id}")
    public AgenceDTO update(@Parameter(description = "ID de l'agence") @PathVariable Long id, @RequestBody AgenceDTO agenceDTO) {
        Agence agence = agenceMapper.fromDTO(agenceDTO);
        Agence updated = agenceService.update(id, agence);
        return agenceMapper.toDTO(updated);
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "ID de l'agence") @PathVariable Long id) {
        agenceService.deleteById(id);
    }

    @GetMapping("/{id}/simuler")
    public String simuler(
            @PathVariable Long id,
            @RequestParam int nombreJours,
            @RequestParam VehiculeOffre offre) {
        return agenceService.simuler(id, nombreJours, offre);
    }

    @PostMapping("/{id}/employes")
    public AgenceDTO ajouterEmployes(@PathVariable Long id, @RequestBody List<Employe> employes) {
        Agence agence = agenceService.ajouterEmployes(id, employes);
        return agenceMapper.toDTO(agence);
    }
}