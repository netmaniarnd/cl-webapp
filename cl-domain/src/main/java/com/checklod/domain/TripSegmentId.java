package com.checklod.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class TripSegmentId implements Serializable {

    private long tripId;
    
    private int seq;
    
 // default constructor
 // JPA expects a default constructor in my entity class ToDo
    protected TripSegmentId() {}
    
    public TripSegmentId(long tripId, int seq) {
        this.tripId = tripId;
        this.seq = seq;
    }
}
