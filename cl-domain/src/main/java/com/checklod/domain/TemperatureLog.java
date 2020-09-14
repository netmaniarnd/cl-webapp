package com.checklod.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TemperatureLog")
public class TemperatureLog {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="temperature", nullable = true)
    private float temperature;
    
    @Column(name="measuredAt", nullable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime measuredAt;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @NotNull(message = "sequence is mandatory")
    @Column(name="sequence")
    private int sequence;

    @NotNull(message = "RTC is mandatory")
    @Column(name="RTC")
    private String rtc;
    
    @NotNull(message = "int_temp is mandatory")
    @Column(name="int_temp")
    private float intTemp;
    
    @NotNull(message = "int_hum is mandatory")
    @Column(name="int_hum")
    private float intHum;
    
    @NotNull(message = "ext_hum is mandatory")
    @Column(name="ext_hum")
    private float extHum;

    @NotNull(message = "ip_addr is mandatory")
    @Column(name="ip_addr")
    private String ipAddr;

    @ToString.Exclude 
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumns( {
        @JoinColumn(name="tripId", referencedColumnName="tripId"),
        @JoinColumn(name="seq", referencedColumnName="seq")
    } )
    private TripSegment tripSegment;
    
    @ToString.Exclude 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loggerId", nullable = true, insertable=false, updatable=false)
    private Logger logger;
}
