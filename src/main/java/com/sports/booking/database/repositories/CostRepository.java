package com.sports.booking.database.repositories;

import com.sports.booking.database.models.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

    @Query(value = " select c from Cost c where c.sport_id = :sport_id ")
    Optional<Cost> getCostBySport_id(@Param("sport_id") Long sport_id);
}
