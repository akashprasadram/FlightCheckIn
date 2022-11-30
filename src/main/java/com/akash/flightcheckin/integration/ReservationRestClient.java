package com.akash.flightcheckin.integration;

import com.akash.flightcheckin.integration.dto.Reservation;
import com.akash.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	public Reservation findReservation(Long id);
	public Reservation updateReservation(ReservationUpdateRequest request);
}
