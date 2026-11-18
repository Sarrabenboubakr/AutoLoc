package tn.esprit.autoloc.autolocapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.io.RapportFactory;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.util.Optional;

// ⚠️ SEULEMENT JUNIT 5 (supprime l'import JUnit 4 s'il est là)
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgenceServiceImplTest {

    @Mock
    private IAgenceRepository repository;

    @Mock
    private IVehiculeService vehiculeService;

    @Mock
    private RapportFactory rapportFactory;

    @Mock
    private IEmployeService employeService;

    @InjectMocks
    private AgenceServiceImpl agenceService;

    @Test
    void testCreateAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Test");
        agence.setTelephone("71000000");

        when(repository.save(any(Agence.class))).thenReturn(agence);

        Agence created = agenceService.create(agence);

        assertNotNull(created);
        assertEquals("Agence Test", created.getNom());
        verify(repository, times(1)).save(agence);
    }

    @Test
    void testFindByIdExistant() {
        Agence agence = new Agence();
        agence.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(agence));

        Agence found = agenceService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testFindByIdInexistant() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            agenceService.findById(99L);
        });
    }
}