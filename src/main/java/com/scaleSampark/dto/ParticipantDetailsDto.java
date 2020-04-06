package com.scaleSampark.dto;

public class ParticipantDetailsDto {
	private Long participant_uuid;
	private String nickName;
	private String email;
	private String lastSeen;

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
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
}
