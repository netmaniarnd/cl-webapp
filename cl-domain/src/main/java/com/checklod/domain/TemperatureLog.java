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
@Table(name = "TemperatureLog")
public class TemperatureLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "tripId is mandatory")
    @Column(name="tripId")
    private long tripId;
    
    @NotNull(message = "seq is mandatory")
    @Column(name="seq")
    private int seq;

    @NotNull(message = "loggerId is mandatory")
    @Column(name="loggerId")
    private String loggerId;

    @Column(name="temperature")
    private float temperature;
    
    @Column(name="measuredAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime measuredAt;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @NotNull(message = "sequence is mandatory")
    @Column(name="sequence")
    private int sequence;

    @Column(name="RTC")
    private String rtc;
    
    @NotNull(message = "int_temp is mandatory")
    @Column(name="int_temp")
    private float intTemp;
    
    @Column(name="int_hum")
    private float intHum;
    
    @Column(name="ext_hum")
    private float extHum;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="ip_addr")
    private String ipAddr;

}
