package com.scaleSampark.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_PARTICIPANT")
public class ParticipantDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long participant_uuid;
	@Column(name = "nick_name", nullable = false,unique=true)
	private String nickName;
	@Column(name = "email", nullable = false,unique=true)
	private String email;
	@Column(name = "last_seen", insertable = false, updatable = true, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSeen;

	@Override
	public String toString() {
		return "ParticipantDetails [id=" + participant_uuid + ", nickName=" + nickName + ", email=" + email + "]";
	}

	public Long getParticipant_uuid() {
		return participant_uuid;
	}

	public void setParticipant_uuid(Long participant_uuid) {
		this.participant_uuid = participant_uuid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

}
