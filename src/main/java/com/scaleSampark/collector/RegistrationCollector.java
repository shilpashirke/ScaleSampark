package com.scaleSampark.collector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.scaleSampark.dto.ParticipantDetailsDto;
import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.response.ResponseStatus;
import com.scaleSampark.response.ScaleSamparkResponse;
import com.scaleSampark.service.RegistrationService;
import com.scaleSampark.util.ResponseStatusUtil;
import com.scaleSampark.util.ScaleSamparkHelper;

@Component
public class RegistrationCollector {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	ScaleSamparkHelper scaleSamparkHelper;

	public ScaleSamparkResponse saveParticipantDetails(ParticipantDetailsDto participantDetails) {
		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try {
			ParticipantDetails detailEntity =scaleSamparkHelper.participantRequestMapper(participantDetails);
			Long registrationUuid = registrationService.saveParticipantDetails(detailEntity);
			response.setData(registrationUuid);
			response.setResponseStatus(ResponseStatusUtil.setResponseStatus());
		}catch (DataIntegrityViolationException ex) {
			ResponseStatus responseStatus=ResponseStatusUtil.setErrorResponseStatus(ex);
			responseStatus.setMessage("Duplicate name or email.");
			response.setResponseStatus(responseStatus);
		} catch (Exception e) {
			response.setResponseStatus(ResponseStatusUtil.setErrorResponseStatus(e));
		}
		return response;

	}

	public ScaleSamparkResponse getParticipantList() {
		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try {
			List<ParticipantDetails> participantDetails = registrationService.getParticipantList();
			List<ParticipantDetailsDto> details =scaleSamparkHelper.mapParticipantResponse(participantDetails);
			response.setData(details);
			response.setResponseStatus(ResponseStatusUtil.setResponseStatus());
		} catch (Exception e) {
			response.setResponseStatus(ResponseStatusUtil.setErrorResponseStatus(e));
		}
		return response;
	}

	public ScaleSamparkResponse disableParticipant(Long participantId) {

		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try {
			Boolean isDeleted = registrationService.disableParticipant(participantId);
			response.setData(isDeleted);
			response.setResponseStatus(ResponseStatusUtil.setResponseStatus());
		}catch(EmptyResultDataAccessException ex){
			ResponseStatus responseStatus=ResponseStatusUtil.setErrorResponseStatus(ex);
			responseStatus.setMessage("No Data found with id:"+participantId);
			response.setResponseStatus(responseStatus);
		}catch (Exception e) {
			response.setResponseStatus(ResponseStatusUtil.setErrorResponseStatus(e));
		}
		return response;
	}
}
