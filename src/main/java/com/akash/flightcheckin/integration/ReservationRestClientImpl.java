package com.akash.flightcheckin.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.akash.flightcheckin.controller.CheckInController;
import com.akash.flightcheckin.integration.dto.Reservation;
import com.akash.flightcheckin.integration.dto.ReservationUpdateRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	//private static final String RESERVATION_REST_URL = "http://localhost:8080/flightreservation/reservations/";
	
	@Value("${com.akash.flightcheckin.reservation.rest.url}")
	private String RESERVATION_REST_URL;

	private Logger LOGGER=LoggerFactory.getLogger(CheckInController.class);
	
	@Override
	public Reservation findReservation(Long id) {
		LOGGER.info("Inside findReservation() with id "+id);

		RestTemplate restTemplate=new RestTemplate();
		Reservation reservation = restTemplate.getForObject(RESERVATION_REST_URL+id, Reservation.class);
		LOGGER.info("Reservation found");
		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation()");
		RestTemplate restTemplate=new RestTemplate();
		Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL, request, Reservation.class);
		LOGGER.info("Reservation Updated");
		return reservation;
	}

}
