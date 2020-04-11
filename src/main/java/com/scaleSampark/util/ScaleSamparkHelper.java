package com.scaleSampark.util;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.scaleSampark.dto.MessageDetailsDto;
import com.scaleSampark.dto.ParticipantDetailsDto;
import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.entity.MessageTypeEntity;
import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.util.DateUtil;

@Component
public class ScaleSamparkHelper {
	final String secretKey = "ssshhhhhhhhhhh!!!!";
	public List<MessageDetailsDto> messageDetailsResponseMapper(List<MessageDetailsEntity> list) {
		List<MessageDetailsDto> detailsDtoList= new ArrayList<MessageDetailsDto>();
		list.forEach(message->{
			MessageDetailsDto detailsDto = new MessageDetailsDto();
			detailsDto.setMessage(decryptName(message.getMessage()));
			detailsDto.setMessageType(message.getMessageType()!=null?message.getMessageType().getMessageType():"Text");
			detailsDto.setMessageUuid(message.getMessageUuid());
			detailsDto.setParticipantUuid(message.getParticipantUuid());
			detailsDto.setSentTime(DateUtil.setDateTime(message.getSentTime()));
			detailsDtoList.add(detailsDto);
		});
		return detailsDtoList;
	}
	
	public ParticipantDetails participantRequestMapper(ParticipantDetailsDto participantDetails) {
		ParticipantDetails details =  new ParticipantDetails();
		details.setEmail(participantDetails.getEmail());
		details.setNickName(participantDetails.getNickName());
		details.setParticipant_uuid(participantDetails.getParticipant_uuid());
		return details;
	}
	
	public List<ParticipantDetailsDto> mapParticipantResponse(List<ParticipantDetails> participantDetails) {
		List<ParticipantDetailsDto> list = new ArrayList<ParticipantDetailsDto>();
		participantDetails.forEach(participant -> {
			ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
			detailsDto.setParticipant_uuid(participant.getParticipant_uuid());
			detailsDto.setEmail(participant.getEmail());
			detailsDto.setNickName(participant.getNickName());
			detailsDto.setLastSeen(DateUtil.setDateTime(participant.getLastSeen()));
			list.add(detailsDto);
		});
		return list;
	}

	private String decryptName(String nickName) {
		String decryptedString = AES.decrypt(nickName, secretKey) ;
		return decryptedString;
	}

	private String encryptName(String originalString) {
		    String encryptedString = AES.encrypt(originalString, secretKey) ;
		return encryptedString;
	}

	public MessageDetailsEntity messageRequestMapper(MessageDetailsDto messageDetails) {
		MessageDetailsEntity detailsEntity = new MessageDetailsEntity();
		detailsEntity.setParticipantUuid(messageDetails.getParticipantUuid());
		detailsEntity.setMessage(encryptName(messageDetails.getMessage()));
		MessageTypeEntity entity = new MessageTypeEntity();
		entity.setMessageType(messageDetails.getMessageType());
		detailsEntity.setMessageType(entity);
		return detailsEntity;
	}
}
