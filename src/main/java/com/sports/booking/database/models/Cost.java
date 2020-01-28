package com.sports.booking.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cost_id")
    private Long cost_id;

    @Column(name = "location_id")
    private Long location_id;

    @Column(name = "sport_id")
    private Long sport_id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "spots")
    private Integer spots;
}
