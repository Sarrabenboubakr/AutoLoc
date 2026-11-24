package tn.esprit.autoloc.autolocapi.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDTO toDTO(Reservation reservation);
    Reservation fromDTO(ReservationDTO reservationDTO);
    List<ReservationDTO> toDTOList(List<Reservation> reservations);
}