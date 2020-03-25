package com.scaleSampark.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_MESSAGETYPE")
public class MessageTypeEntity implements Serializable{
	
	@Id
	@GeneratedValue
	private Long messageTypeUuid;
	@Column(name="message_type",nullable=false)
	private String messageType;
	public Long getMessageTypeUuid() {
		return messageTypeUuid;
	}
	public void setMessageTypeUuid(Long messageTypeUuid) {
		this.messageTypeUuid = messageTypeUuid;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	
}
