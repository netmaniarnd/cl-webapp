package com.checklod.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripMediaRepository extends CrudRepository<TripMedia, Long> {}
