package com.checklod.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Phone")
public class Phone {
    
    @Id
    private String id;
    
    @NotNull(message = "driverId is mandatory")
    @Column(name="driverId")
    private long driverId;

    @Column(name="driverPhone")
    private String driverPhone;
    
    @OneToMany(
    		mappedBy = "phone", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude 
    private Set<TripSegment> tripSegments;

}
