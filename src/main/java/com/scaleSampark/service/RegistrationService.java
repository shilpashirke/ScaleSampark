package com.scaleSampark.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;

	public Long saveParticipantDetails(ParticipantDetails participantDetails) {
		participantDetails = registrationRepository.save(participantDetails);
		return participantDetails.getParticipant_uuid();
	}

	public List<ParticipantDetails> getParticipantList() {
		List<ParticipantDetails>  particiPantList = new ArrayList<>();
		Iterable<ParticipantDetails> i = registrationRepository.findAll();
		i.forEach(particiPant->{
			particiPantList.add(particiPant);
		});
		return Arrays.asList();
	}

	public boolean disableParticipant(String id) {
		registrationRepository.delete(Long.parseLong(id));
		return true;
	}
}
