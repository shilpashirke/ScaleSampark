package com.scaleSampark.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scaleSampark.entity.MessageDetailsEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageDetailsEntity, Long>{
	
}
