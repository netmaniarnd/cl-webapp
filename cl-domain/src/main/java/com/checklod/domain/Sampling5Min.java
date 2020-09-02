package com.checklod.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "activelog300")
public class Sampling5Min {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "tripId is mandatory")
    @Column(name="tripId")
    private long tripId;
    
    @NotNull(message = "interval is mandatory")
    @Column(name="interval")
    private int interval;
    
    @Column(name="measuredAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime measuredAt;

    @NotNull(message = "int_temp is mandatory")
    @Column(name="int_temp")
    private float intTemp;

    @Column(name="chipset_temp")
    private float chipsetTemp;
   
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
