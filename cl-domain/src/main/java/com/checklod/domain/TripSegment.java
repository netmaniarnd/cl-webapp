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

import lombok.ToString;

@ToString
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

	public TripSegmentId getTripSegmentId() {
		return tripSegmentId;
	}

	public void setTripSegmentId(TripSegmentId tripSegmentId) {
		this.tripSegmentId = tripSegmentId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getTransMode() {
		return transMode;
	}

	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}

	public LocalDateTime getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDateTime checkin) {
		this.checkin = checkin;
	}

	public LocalDateTime getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDateTime checkout) {
		this.checkout = checkout;
	}

	public Set<TemperatureLog> getTemperatureLogs() {
		return temperatureLogs;
	}

	public void setTemperatureLogs(Set<TemperatureLog> temperatureLogs) {
		this.temperatureLogs = temperatureLogs;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
}
