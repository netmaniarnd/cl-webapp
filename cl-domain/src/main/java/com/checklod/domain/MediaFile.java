package com.checklod.domain;

import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaFile {

	private String imageType;
	private InputStream fileStream;
}
