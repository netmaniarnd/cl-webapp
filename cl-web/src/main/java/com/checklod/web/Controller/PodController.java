package com.checklod.web.Controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.checklod.service.ImageFile;
import com.checklod.service.PodDTO;
import com.checklod.service.PodService;
import com.checklod.web.ResultResponse;
import com.checklod.web.StorageException;
import com.checklod.service.InvalidTripException;

@Controller
public class PodController {
	
	private static Logger logger = LoggerFactory.getLogger(PodController.class);
	
	@Autowired
	private PodService podService;
	
	/*
	 * 웹에서 안드로이드 앱인 것처럼 테스트하기 위한 페이지
	 */
	@GetMapping("/pod")
    public String index(Model model) {
		PodDTO podDTO = new PodDTO();
		String phoneNo = "01071068770";
		String loggerId = "FF:FF:FF:FF:FF:21";
		
		podDTO.setPhoneNo(phoneNo );
		podDTO.setLoggerId(loggerId );
		
        model.addAttribute("data", podDTO);
		return "pod-base";
	}
	
    @PostMapping("/addpod")
    public ResponseEntity<ResultResponse> addPod(
    		@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
    		@RequestParam("phoneNo") String phoneNo,
    		@RequestParam("loggerId") String loggerId,
    		@RequestParam("fileTypes") String fileTypes) {
		logger.info("addPod phoneNo {}", phoneNo);
		logger.info("addPod loggerId {}", loggerId);
		logger.info("addPod fileTypes {}", fileTypes);
		
		String[] types = fileTypes.split(",");
		
		PodDTO podDTO = new PodDTO();
		// construct DTO
    	try {
    		int index = 0;
			for(MultipartFile uploadedFile : uploadingFiles) {
				if (uploadedFile.isEmpty()) {
		            throw new StorageException("Failed to store empty file");
		        }
				//
				logger.debug("OriginalFilename = {}", uploadedFile.getOriginalFilename());
				InputStream is = uploadedFile.getInputStream();

				podDTO.addFile(new ImageFile(types[index++], is));
			}
			podDTO.setPhoneNo(phoneNo);
			podDTO.setLoggerId(loggerId);
			logger.info("podDTO {}", podDTO);

			podService.register(podDTO);
		} catch (InvalidTripException e) {
			//e.printStackTrace();
			logger.error("addPod {}", e.getMessage());
	    	ResultResponse responseObject = new ResultResponse();
	    	responseObject.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
		} catch (IOException | StorageException e) {
			//e.printStackTrace();
			logger.error("addPod {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

    	/*
    	 * Content-Type: application/json
		 * {"message":"ok"}
    	 */
    	ResultResponse responseObject = new ResultResponse();
    	responseObject.setMessage("ok");
		return ResponseEntity.ok(responseObject);
    }
}
