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

import lombok.ToString;

@ToString
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

    public TripSegment getTripSegment() {
    	return tripSegment;
    }
    
    @ToString.Exclude 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loggerId", nullable = true, insertable=false, updatable=false)
    private Logger logger;

    public Logger getLogger() {
    	return logger;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getExtHum() {
		return extHum;
	}

	public void setExtHum(float extHum) {
		this.extHum = extHum;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public LocalDateTime getMeasuredAt() {
		return measuredAt;
	}

	public void setMeasuredAt(LocalDateTime measuredAt) {
		this.measuredAt = measuredAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getRtc() {
		return rtc;
	}

	public void setRtc(String rtc) {
		this.rtc = rtc;
	}

	public float getIntTemp() {
		return intTemp;
	}

	public void setIntTemp(float intTemp) {
		this.intTemp = intTemp;
	}

	public float getIntHum() {
		return intHum;
	}

	public void setIntHum(float intHum) {
		this.intHum = intHum;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setTripSegment(TripSegment tripSegment) {
		this.tripSegment = tripSegment;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
