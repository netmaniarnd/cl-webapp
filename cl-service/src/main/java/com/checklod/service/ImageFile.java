package com.checklod.service;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageFile {

	private String imageType;
	private InputStream uploadedFile;
	
	public ImageFile(String imageType, InputStream uploadedFile) {
		this.setImageType(imageType);
		this.setUploadedFile(uploadedFile);
	}
}
