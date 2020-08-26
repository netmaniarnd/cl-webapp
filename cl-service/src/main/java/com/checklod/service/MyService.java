package com.checklod.service;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.checklod.domain.FileStorage;
import com.checklod.domain.MediaFile;
import com.checklod.domain.Trip;
import com.checklod.domain.TripMedia;
import com.checklod.domain.TripMediaRepository;
import com.checklod.domain.TripRepository;
import com.checklod.domain.TripSegment;

@Service
public class MyService implements PodService {

	private static final String STATUS_SIGNED = "4";

	private static Logger logger = LoggerFactory.getLogger(MyService.class);
	
	@Autowired
	private FileStorage fileRepositiry;

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
    private TripMediaRepository tripMediaRepository;

	@Value("${upload.path}")
	private String uploadPath;
	
	@Transactional(isolation=Isolation.DEFAULT)
	@Override
	public void register(@Valid PodDTO podImage) {
		logger.info("PodDTO {}", podImage.toString());
		//
		Trip myTrip = findGoingTrip(podImage.getLoggerId());
		
		podImage.getFiles().forEach(image -> {
			URL url = buileMediaFile(image);
			TripMedia tripMedia = buildTripMedia(image, url, myTrip);
			tripMediaRepository.save(tripMedia);
		});
		//
		myTrip.setGoingStatus(STATUS_SIGNED);
		tripRepository.save(myTrip);
		//
		logger.info("transaction OK");
	}

	private Trip findGoingTrip(String loggerId) {
		Optional<TripSegment> resultTripSegment = tripRepository.findGoingLatestByLogger(loggerId);
		//
		if(resultTripSegment == null || ! resultTripSegment.isPresent()) throw new InvalidTripException("Segment not found");
		Long tripId = resultTripSegment.get().getTripSegmentId().getTripId();
		Optional<Trip> trip = tripRepository.findById(tripId);
		Trip aTrip = trip.get();
		return aTrip;
	}

	private TripMedia buildTripMedia(ImageFile item, URL url, Trip trip) {
		TripMedia tripMedia = new TripMedia();
		tripMedia.setTrip(trip);
		tripMedia.setImgType(((ImageFile)item).getImageType());
		tripMedia.setImgUrl(url.toString());
		return tripMedia;
	}

	private URL buileMediaFile(ImageFile item) {
		//String rootPath = uploadPath + "/uploadingDir/";
		MediaFile media = new MediaFile();
		logger.info("ImageFile {}", item.toString());
		media.setImageType(item.getImageType());
		media.setFileStream(item.getUploadedFile());
		//
		URL url = null;
		try {
			url = fileRepositiry.create(media);
			logger.info("file url {}", url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

}
