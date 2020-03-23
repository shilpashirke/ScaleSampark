package com.scaleSampark.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scaleSampark.entity.ParticipantDetails;
@Repository
public interface RegistrationRepository extends CrudRepository<ParticipantDetails, Long>{

}
