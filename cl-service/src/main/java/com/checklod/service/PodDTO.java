package com.checklod.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PodDTO {

	private String phoneNo;
	private String loggerId;
	private List<ImageFile> files = new ArrayList<ImageFile>();

	public void addFile(ImageFile file) {
		this.files.add(file);
	}
}
