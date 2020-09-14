package com.checklod.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "Driver")
public class Driver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "name is mandatory")
    @Column(name="name")
    private String name;
    
    @NotNull(message = "vehicleNo is mandatory")
    @Column(name="vehicleNo")
    private String vehicleNo;
    
    @Column(name="location")
    private String location;

}
