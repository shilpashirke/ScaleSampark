package com.scaleSampark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaleSampark.collector.RegistrationCollector;
import com.scaleSampark.dto.ParticipantDetailsDto;
import com.scaleSampark.response.ScaleSamparkResponse;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationCollector registrationCollector;

	@PostMapping("/participant")
	public ResponseEntity<ScaleSamparkResponse> saveParticipantDetails(
			@RequestBody ParticipantDetailsDto participantDetails) {
		ScaleSamparkResponse response = null;
		if (null == participantDetails.getEmail() || null == participantDetails.getNickName()) {
			return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		response = registrationCollector.saveParticipantDetails(participantDetails);
		return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/participant")
	public ResponseEntity<ScaleSamparkResponse> getParticipantDetails() {
		ScaleSamparkResponse response = registrationCollector.getParticipantList();
		if (response.getData() != null)
			return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.OK);
		else
			return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/participant/{participantId}")
	public ResponseEntity<ScaleSamparkResponse> disableParticipant(@PathVariable("participantId") Long participantId) {
		ScaleSamparkResponse response = registrationCollector.disableParticipant(participantId);
		if (response.getData() == null) {
			return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.OK);
	}

}
