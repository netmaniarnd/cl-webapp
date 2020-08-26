package com.checklod.domain;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Repository;

@Repository
public interface FileStorage {
	public URL create(MediaFile media) throws IOException;
}
