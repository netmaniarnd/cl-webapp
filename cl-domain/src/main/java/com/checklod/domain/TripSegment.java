package com.checklod.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TripSegment")
public class TripSegment {
    
	@EmbeddedId
    private TripSegmentId tripSegmentId;
    
    @Column(name="phoneNo")
    private String phoneNo;
    
    @Column(name="driverId")
    private long driverId;
    
    @Column(name="driverName")
    private String driverName;
    
    @Column(name="vehicleNo")
    private String vehicleNo;
    
    @Column(name="transMode")
    private String transMode;
    
    @NotNull(message = "checkin is mandatory")
    @CreationTimestamp
    private LocalDateTime checkin;
    
    @UpdateTimestamp
    private LocalDateTime checkout;
    
    @OneToMany(
    		mappedBy = "tripSegment", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude 
    private Set<TemperatureLog> temperatureLogs;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tripId", nullable = false, insertable=false, updatable=false)
    @ToString.Exclude 
    private Trip trip;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loggerId", nullable = false, insertable=false, updatable=false)
    @ToString.Exclude 
    private Logger logger;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phoneNo", nullable = false, insertable=false, updatable=false)
    @ToString.Exclude 
    private Phone phone;
}
