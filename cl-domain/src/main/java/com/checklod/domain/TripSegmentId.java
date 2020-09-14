package com.checklod.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class TripSegmentId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "tripId")
	private long tripId;
    
	@Column(name = "seq")
    private int seq;
    
 // default constructor
 // JPA expects a default constructor in my entity class ToDo
    protected TripSegmentId() {}
    
    public TripSegmentId(long tripId, int seq) {
        this.tripId = tripId;
        this.seq = seq;
    }
}
