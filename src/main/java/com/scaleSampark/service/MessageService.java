package com.scaleSampark.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaleSampark.entity.MessageDetails;
import com.scaleSampark.entity.MessageType;
import com.scaleSampark.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	public Long saveMessageDetails(MessageDetails messageDetails) {
		MessageType messageType =messageDetails.getMessageType();
		messageDetails = messageRepository.save(messageDetails);
		return messageDetails.getMessageUuid();
	}

	public List<MessageDetails> getMessageDetails() {
		List<MessageDetails> messageList = new ArrayList<>();
		Iterable<MessageDetails> i = messageRepository.findAll();
		i.forEach(message->{
			messageList.add(message);
		});
		return messageList;
	
	}


}
