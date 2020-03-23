package com.scaleSampark.dto;

public class MessageDetailsDto {
	private Long messageUuid;
	private Long participantUuid;
	private String message;
	private String sentTime;
	private String messageType;
	public Long getParticipantUuid() {
		return participantUuid;
	}
	public void setParticipantUuid(Long participantUuid) {
		this.participantUuid = participantUuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSentTime() {
		return sentTime;
	}
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	public Long getMessageUuid() {
		return messageUuid;
	}
	public void setMessageUuid(Long messageUuid) {
		this.messageUuid = messageUuid;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
}
