package com.checklod.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
