package com.scaleSampark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaleSampark.entity.MessageDetails;
import com.scaleSampark.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping("/saveMessage")
	public Long saveMessageDetails(@RequestBody MessageDetails messageDetails) {
		return messageService.saveMessageDetails(messageDetails);
	}

	@GetMapping("/fetchMessage")
	public List<MessageDetails> getParticipantDetails() {
		return messageService.getMessageDetails();
	}

}
