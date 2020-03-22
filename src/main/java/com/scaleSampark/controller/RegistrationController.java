package com.scaleSampark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping("/participant")
	public Long saveParticipantDetails(ParticipantDetails participantDetails) {
		return registrationService.saveParticipantDetails(participantDetails);
	}

	@GetMapping("/participant")
	public List<ParticipantDetails> getParticipantDetails() {
		return registrationService.getParticipantList();
	}

	@DeleteMapping("/participant")
	public Boolean disableParticipant(@PathVariable("participantId") String participantId) {
		return registrationService.disableParticipant(participantId);
	}
}
