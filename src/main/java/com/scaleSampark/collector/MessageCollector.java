package com.scaleSampark.collector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scaleSampark.dto.MessageDetailsDto;
import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.response.ScaleSamparkResponse;
import com.scaleSampark.service.MessageService;

@Component
public class MessageCollector {
	@Autowired
	MessageService messageService;

	public ScaleSamparkResponse getMessageDetails(Integer pageNo, Integer pageSize, String sortBy) {
		ScaleSamparkResponse response = new ScaleSamparkResponse();
		try{
			List<MessageDetailsEntity> list = messageService.getMessageDetails(pageNo, pageSize, sortBy);
			List<MessageDetailsDto> detailsList =messageDetailsResponseMapper(list);
			response.setData(detailsList);
			setResponseStatus();
		}catch(Exception e){
			setErrorResponseStatus();
		}
		
		return response;
	}
	
	private void setResponseStatus() {
		// TODO Auto-generated method stub
		
	}

	private void setErrorResponseStatus() {
		// TODO Auto-generated method stub
		
	}

	private List<MessageDetailsDto> messageDetailsResponseMapper(List<MessageDetailsEntity> list) {
		List<MessageDetailsDto> detailsDtoList= new ArrayList<MessageDetailsDto>();
		list.forEach(message->{
			MessageDetailsDto detailsDto = new MessageDetailsDto();
			detailsDto.setMessage(message.getMessage());
			detailsDto.setMessageType(message.getMessageType()!=null?message.getMessageType().getMessageType():"Text");
			detailsDto.setMessageUuid(message.getMessageUuid());
			detailsDto.setParticipantUuid(message.getParticipantUuid());
			detailsDto.setSentTime(setDateTime(message.getSentTime()));
			detailsDtoList.add(detailsDto);
		});
		return detailsDtoList;
	}

	private String setDateTime(Date sentTime) {
		if(sentTime !=null){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return dateFormat.format(sentTime);
		}
		else
			return "";
	}
}
