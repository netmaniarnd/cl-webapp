package com.checklod.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Trip")
public class Trip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "invoiceNo is mandatory")
    @Column(name="invoiceNo")
    private String invoiceNo;
    
    @NotNull(message = "goingStatus is mandatory")
    @Column(name="goingStatus")
    private String goingStatus;

    @NotNull(message = "lowerLimit is mandatory")
    @Column(name="lowerLimit")
    private float lowerLimit;
    
    @NotNull(message = "upperLimit is mandatory")
    @Column(name="upperLimit")
    private float upperLimit;
    
    @Column(name="invoiceImageUrl")
    private String invoiceImageUrl;
    
    @Column(name="pdfReportUrl")
    private String pdfReportUrl;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @OneToMany(
    		mappedBy = "trip", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude private Set<TripSegment> tripSegments;
    
    @OneToMany(
    		mappedBy = "trip", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude private Set<TripMedia> tripMedias;
}
