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

import lombok.Data;

@Data
@Entity
@Table(name = "Invoice")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="optionNo")
    private String optionNo;
    
    @NotNull(message = "loggerId is mandatory")
    @Column(name="loggerId")
    private String loggerId;

    @Column(name="departure")
    private String departure;

    @Column(name="arrival")
    private String arrival;

    @Column(name="tripIdAssigned")
    private String tripIdAssigned;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
