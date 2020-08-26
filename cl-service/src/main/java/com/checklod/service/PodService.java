package com.checklod.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public interface PodService {

	public void register(@Valid PodDTO podInfo);
}
