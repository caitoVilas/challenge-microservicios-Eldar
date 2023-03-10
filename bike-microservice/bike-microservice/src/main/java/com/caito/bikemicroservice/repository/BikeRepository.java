package com.caito.bikemicroservice.repository;

import com.caito.bikemicroservice.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findByUserId(Long id);
}
