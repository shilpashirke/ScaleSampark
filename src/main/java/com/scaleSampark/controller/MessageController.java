package com.scaleSampark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaleSampark.collector.MessageCollector;
import com.scaleSampark.dto.MessageDetailsDto;
import com.scaleSampark.response.ScaleSamparkResponse;
import com.scaleSampark.service.MessageService;
import com.scaleSampark.util.ScaleSamparkException;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	MessageCollector  messageCollector;

	@PostMapping("/saveMessage")
	public ResponseEntity<ScaleSamparkResponse> saveMessageDetails(@RequestBody MessageDetailsDto messageDetails) throws ScaleSamparkException {
		ScaleSamparkResponse response = null;
		if (null == messageDetails.getMessage() && null == messageDetails.getParticipantUuid() && null==messageDetails.getMessageType()) {
			return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		response = messageCollector.saveMessageDetails(messageDetails);;
		return new ResponseEntity<ScaleSamparkResponse>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/fetchMessage")
	public ResponseEntity<ScaleSamparkResponse> getMessageDetails(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) {
		
		ScaleSamparkResponse scaleSamparkResponse =messageCollector.getMessageDetails(pageNo, pageSize);
		return new ResponseEntity<ScaleSamparkResponse>(scaleSamparkResponse, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/fetchPendingMessage/{participantId}")
	public ResponseEntity<ScaleSamparkResponse> getPendingMessageDetails(@PathVariable("participantId") Long participantId,
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) {
		
		ScaleSamparkResponse scaleSamparkResponse =messageCollector.getPendingMessageDetails(pageNo, pageSize,participantId);
		return new ResponseEntity<ScaleSamparkResponse>(scaleSamparkResponse, new HttpHeaders(), HttpStatus.OK);
	}
}
