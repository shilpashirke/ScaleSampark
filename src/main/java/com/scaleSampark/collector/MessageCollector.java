package com.scaleSampark.collector;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.scaleSampark.dto.MessageDetailsDto;
import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.response.ResponseStatus;
import com.scaleSampark.response.ScaleSamparkResponse;
import com.scaleSampark.service.MessageService;
import com.scaleSampark.util.ResponseStatusUtil;
import com.scaleSampark.util.ScaleSamparkConstant;
import com.scaleSampark.util.ScaleSamparkHelper;

@Component
public class MessageCollector {
	@Autowired
	MessageService messageService;
	
	@Autowired
	ScaleSamparkHelper scaleSamparkHelper; 

	public ScaleSamparkResponse getMessageDetails(Integer pageNo, Integer pageSize) {
		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try{
			List<MessageDetailsEntity> list = messageService.getMessageDetails(pageNo, pageSize);
			List<MessageDetailsDto> detailsList =scaleSamparkHelper.messageDetailsResponseMapper(list);
			response.setData(detailsList);
			response.setResponseStatus(ResponseStatusUtil.setResponseStatus());
		}catch(Exception e){
			response.setResponseStatus(ResponseStatusUtil.setErrorResponseStatus(e));
		}
		
		return response;
	}
	

	public ScaleSamparkResponse getPendingMessageDetails(Integer pageNo, Integer pageSize, Long participantId) {

		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try{
			List<MessageDetailsEntity> list = messageService.getPendingMessageDetails(pageNo, pageSize,participantId);
			List<MessageDetailsDto> detailsList =scaleSamparkHelper.messageDetailsResponseMapper(list);
			response.setData(detailsList);
			response.setResponseStatus(ResponseStatusUtil.setResponseStatus());
		}catch(Exception e){
			e.printStackTrace();
			response.setResponseStatus(ResponseStatusUtil.setErrorResponseStatus(e));
		}
		
		return response;
	
	}


	public ScaleSamparkResponse saveMessageDetails(MessageDetailsDto messageDetails) {

		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try {
			MessageDetailsEntity detailEntity =scaleSamparkHelper.messageRequestMapper(messageDetails);
			Long registrationUuid = messageService.saveMessageDetails(detailEntity);
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
}
