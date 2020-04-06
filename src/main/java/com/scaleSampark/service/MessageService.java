package com.scaleSampark.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.entity.MessageTypeEntity;
import com.scaleSampark.entity.ParticipantDetails;
import com.scaleSampark.repository.MessageDetailsRepository;
import com.scaleSampark.repository.MessageRepository;
import com.scaleSampark.repository.RegistrationRepository;
import com.scaleSampark.util.ScaleSamparkException;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessageDetailsRepository messageDetailsRepository;
	
	@Autowired
	private RegistrationRepository participantDetailsRepository;

	public Long saveMessageDetails(MessageDetailsEntity messageDetails) throws ScaleSamparkException {
		//MessageTypeEntity messageType = messageDetails.getMessageType();
		//update last seen
				ParticipantDetails entity= participantDetailsRepository.findOne(messageDetails.getParticipantUuid());
				if(entity ==null){
					throw new ScaleSamparkException("No Participant found with id:"+messageDetails.getParticipantUuid());
				}
		messageDetails = messageRepository.save(messageDetails);
		return messageDetails.getMessageUuid();
	}

	public List<MessageDetailsEntity> getMessageDetails(Integer pageNo, Integer pageSize) {
		Pageable paging = new PageRequest(pageNo, pageSize);
		Page<MessageDetailsEntity> pagedResult = messageDetailsRepository.findAll(paging);
		List<MessageDetailsEntity> messageList = new ArrayList<>();
		Iterable<MessageDetailsEntity> messageDetails = pagedResult.getContent();// messageRepository.findAll();
		messageDetails.forEach(message -> {
			messageList.add(message);
		});
		return messageList;

	}

	public List<MessageDetailsEntity> getPendingMessageDetails(Integer pageNo, Integer pageSize, Long participantId) throws ScaleSamparkException {

		Pageable paging = new PageRequest(pageNo, pageSize);
		Page<MessageDetailsEntity> pagedResult = messageDetailsRepository.findAllPendingMessage(paging,participantId);
		List<MessageDetailsEntity> messageList = new ArrayList<>();
		Iterable<MessageDetailsEntity> messageDetails = pagedResult.getContent();// messageRepository.findAll();
		messageDetails.forEach(message -> {
			messageList.add(message);
		});
		//update last seen
		ParticipantDetails entity= participantDetailsRepository.findOne(participantId);
		if(entity ==null){
			throw new ScaleSamparkException("No Participant found with id:"+participantId);
		}
		entity.setLastSeen(new Date());
		participantDetailsRepository.save(entity);
		return messageList;

	
	}

}
