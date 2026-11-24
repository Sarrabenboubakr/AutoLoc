package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;
import tn.esprit.autoloc.autolocapi.web.dto.PaiementDTO;
import tn.esprit.autoloc.autolocapi.web.dto.PaiementMapper;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;
    private final PaiementMapper paiementMapper;

    @GetMapping
    public List<PaiementDTO> findAll() {
        List<Paiement> paiements = paiementService.findAll();
        return paiementMapper.toDTOList(paiements);
    }

    @GetMapping("/{id}")
    public PaiementDTO findById(@PathVariable Long id) {
        Paiement paiement = paiementService.findById(id);
        return paiementMapper.toDTO(paiement);
    }
}