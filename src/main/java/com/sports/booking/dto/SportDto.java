package com.sports.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportDto {

    private String name;

    private String cost;

    private Integer seatsAvailable;

    private Date start_date;

    private Date end_date;


}
