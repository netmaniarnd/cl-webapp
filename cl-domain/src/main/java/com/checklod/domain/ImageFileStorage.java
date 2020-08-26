package com.checklod.domain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ImageFileStorage implements FileStorage {

	private static Logger logger = LoggerFactory.getLogger(ImageFileStorage.class);

	@Value("${upload.path}")
	private String uploadPath;
	
	@Override
	public URL create(MediaFile media) throws IOException {
		logger.info("create MediaFile {}", media.toString());
		//
		String uploadingDir = uploadPath;
		String filePath = UUID.randomUUID().toString();
		//
		Files.copy(media.getFileStream(), Paths.get(uploadingDir + filePath),
                StandardCopyOption.REPLACE_EXISTING);
		File myFile=new File(uploadingDir + filePath);
		URL myUrl = myFile.toURI().toURL();
		return myUrl;
	}

}
