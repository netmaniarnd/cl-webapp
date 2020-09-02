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
@Table(name = "LoggerSignal")
public class LoggerSignal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "loggerId is mandatory")
    @Column(name="loggerId")
    private String loggerId;
    
    @NotNull(message = "bleRssi is mandatory")
    @Column(name="bleRssi")
    private int bleRssi;

    @Column(name="battery")
    private float battery;
    
    @Column(name="measuredAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime measuredAt;
   
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
