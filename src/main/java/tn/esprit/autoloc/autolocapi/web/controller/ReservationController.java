package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.service.IReservationService;
import tn.esprit.autoloc.autolocapi.web.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.web.dto.ReservationMapper;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @GetMapping
    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = reservationService.findAll();
        return reservationMapper.toDTOList(reservations);
    }

    @GetMapping("/{id}")
    public ReservationDTO findById(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id);
        return reservationMapper.toDTO(reservation);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO create( @Valid @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.fromDTO(reservationDTO);
        Reservation created = reservationService.create(reservation);
        return reservationMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ReservationDTO update(@PathVariable Long id,@Valid @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.fromDTO(reservationDTO);
        Reservation updated = reservationService.update(id, reservation);
        return reservationMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}