package com.scaleSampark.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_MESSAGE")
public class MessageDetails implements Serializable {

	@Id
	@GeneratedValue
	private Long messageUuid;
	@Column(name = "participant_uuid", nullable = false)
	private Long participantUuid;

	@Column(name = "message", nullable = false)
	private String message;
	
	@Column(name = "sentTime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP default getdate()")
 	@Temporal(TemporalType.TIMESTAMP)
    private Date sentTime;
	
	@ManyToOne
    @JoinColumn
	private MessageType messageType;

	public Long getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(Long messageUuid) {
		this.messageUuid = messageUuid;
	}

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

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
}