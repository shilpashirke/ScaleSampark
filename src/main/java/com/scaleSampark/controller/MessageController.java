package com.scaleSampark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaleSampark.collector.MessageCollector;
import com.scaleSampark.entity.MessageDetailsEntity;
import com.scaleSampark.response.ScaleSamparkResponse;
import com.scaleSampark.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	MessageCollector  messageCollector;

	@PostMapping("/saveMessage")
	public Long saveMessageDetails(@RequestBody MessageDetailsEntity messageDetails) {
		return messageService.saveMessageDetails(messageDetails);
	}

	@GetMapping("/fetchMessage")
	public ScaleSamparkResponse getParticipantDetails(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "messageUuid") String sortBy) {
		
		ScaleSamparkResponse scaleSamparkResponse =messageCollector.getMessageDetails(pageNo, pageSize, sortBy);
		//List<MessageDetailsDto> list = messageService.getMessageDetails();
		//return new ResponseEntity<>(ScaleSamparkResponse);
		return scaleSamparkResponse;
	}

}
