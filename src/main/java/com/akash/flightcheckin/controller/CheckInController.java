package com.akash.flightcheckin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akash.flightcheckin.integration.ReservationRestClient;
import com.akash.flightcheckin.integration.dto.Reservation;
import com.akash.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {
	
	@Autowired
	ReservationRestClient restClient;
	
	private Logger LOGGER=LoggerFactory.getLogger(CheckInController.class);
	
	@RequestMapping("/showStartCheckin")
	public String showStartCheckin() {
		LOGGER.info("Inside showStartCheckin()");
		return "startCheckIn";
	}

	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId,ModelMap modelMap) {
		LOGGER.info("Inside startCheckIn() with reservationId "+reservationId);
		Reservation reservation = restClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,@RequestParam("numberOfBags")int numberOfBags)
	{
		LOGGER.info("Inside completeCheckIn() with reservationId "+reservationId+" number of Bags "+numberOfBags);
		ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		restClient.updateReservation(reservationUpdateRequest);
		return "checkInConfirmation";
	}
}
