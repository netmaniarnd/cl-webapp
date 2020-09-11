package com.checklod.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Logger")
public class Logger {
    
    @Id
    private String id;
    
    @NotNull(message = "alias is mandatory")
    @Column(name="alias")
    private String alias;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
    		mappedBy = "logger", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude 
    private Set<TemperatureLog> temperatureLogs;

    @OneToMany(
    		mappedBy = "logger", 
    		cascade = CascadeType.ALL,
            orphanRemoval = true
	)
    @ToString.Exclude 
    private Set<TripSegment> tripSegments;
}
