package com.scaleSampark.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.scaleSampark.entity.MessageDetailsEntity;

@Repository
public interface MessageDetailsRepository extends PagingAndSortingRepository<MessageDetailsEntity, Long> {

}
