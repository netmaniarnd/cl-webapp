package com.checklod.service;

public class InvalidTripException extends RuntimeException {

	public InvalidTripException(String message) {
        super(message);
    }
}
