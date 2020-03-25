package com.scaleSampark.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.entity.MessageTypeEntity;
import com.scaleSampark.repository.MessageDetailsRepository;
import com.scaleSampark.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessageDetailsRepository messageDetailsRepository;

	public Long saveMessageDetails(MessageDetailsEntity messageDetails) {
		MessageTypeEntity messageType = messageDetails.getMessageType();
		messageDetails = messageRepository.save(messageDetails);
		return messageDetails.getMessageUuid();
	}

	public List<MessageDetailsEntity> getMessageDetails(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = new PageRequest(pageNo, pageSize, new Sort(sortBy));

		Page<MessageDetailsEntity> pagedResult = messageDetailsRepository.findAll(paging);
System.out.println(pagedResult.getSize());
		List<MessageDetailsEntity> messageList = new ArrayList<>();
		Iterable<MessageDetailsEntity> i = pagedResult.getContent();//messageRepository.findAll();
		i.forEach(message -> {
			messageList.add(message);
		});
		return messageList;

	}

}
