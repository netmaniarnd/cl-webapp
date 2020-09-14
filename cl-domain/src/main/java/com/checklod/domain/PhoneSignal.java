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

import lombok.Data;

@Data
@Entity
@Table(name = "PhoneSignal")
public class PhoneSignal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "phoneId is mandatory")
    @Column(name="phoneId")
    private String phoneId;
    
    @NotNull(message = "lteRssi is mandatory")
    @Column(name="lteRssi")
    private int lteRssi;

    @Column(name="battery")
    private double battery;

    @Column(name="location")
    private String location;
    
    @Column(name="measuredAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime measuredAt;
   
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
