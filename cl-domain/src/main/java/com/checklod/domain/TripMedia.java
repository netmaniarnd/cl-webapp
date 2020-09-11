package com.checklod.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TripMedia")
public class TripMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull(message = "imgType is mandatory")
    @Column(name="ImgType", nullable = false, updatable = false)
    private String imgType;
    
    @NotNull(message = "imgUrl is mandatory")
    @Column(name="ImgUrl", nullable = false, updatable = false)
    private String imgUrl;
    
    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tripId", nullable = false)
    @ToString.Exclude
    private Trip trip;

}
