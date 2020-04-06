package com.scaleSampark.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scaleSampark.entity.MessageDetailsEntity;

@Repository
public interface MessageDetailsRepository extends PagingAndSortingRepository<MessageDetailsEntity, Long> {

	@Query("SELECT m FROM MessageDetailsEntity m ,ParticipantDetails p WHERE m.participantUuid = :participantId and m.sentTime > p.lastSeen ")
	Page<MessageDetailsEntity> findAllPendingMessage(Pageable paging, @Param("participantId") Long participantId);

}
