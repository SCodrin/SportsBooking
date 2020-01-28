package com.sports.booking.database.repositories;

import com.sports.booking.database.models.Sport;
import com.sports.booking.dto.SportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query(value = " select s from Sport s "
            + " where :start_date >= s.start_date "
            + " and  :end_date <= s.end_date ")
    List<Sport> findSportBetweenDates(@Param("start_date") Date start_date, @Param("end_date") Date end_date);

    @Query(value = " select s from Sport s "
            + " where s.name = :sport_name")
    Sport findSportsByName(@Param("sport_name") String sport_name);
}
