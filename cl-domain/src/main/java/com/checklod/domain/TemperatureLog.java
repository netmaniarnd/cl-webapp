package com.checklod.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@IdClass(TripSegmentId.class)
@Table(name = "TemperatureLog")
public class TemperatureLog {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
	@EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride( name = "tripId", column = @Column(name = "tripId")),
		  @AttributeOverride( name = "seq", column = @Column(name = "seq", insertable=false, updatable=false))
		})
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
    private TripSegmentId tripSegmentId;

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

    @Column(name="ip_addr")
    private String ipAddr;

    @ToString.Exclude 
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tripId", nullable = false, insertable=false, updatable=false)
    public Trip getTrip() {
    	return trip;
    }

    @ToString.Exclude 
    private Phone phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phone_no", nullable = false, insertable=false, updatable=false)
    public Phone getPhone() {
    	return phone;
    }
    
    @ToString.Exclude 
    private Logger logger;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loggerId", nullable = false, insertable=false, updatable=false)
    public Logger getLogger() {
    	return logger;
    }

    @Id
    public long getTripId() {
       return tripSegmentId.getTripId();
    }

    @Id
    public void setTripId(long tripId) {
       tripSegmentId.setTripId(tripId);
    }

    @Id
    public int getSeq() {
       return tripSegmentId.getSeq();
    }

    @Id
    public void setSeq(int seq) {
       tripSegmentId.setSeq(seq);
    }
}
