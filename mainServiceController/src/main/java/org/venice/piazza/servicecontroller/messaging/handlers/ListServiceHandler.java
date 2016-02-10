package org.venice.piazza.servicecontroller.messaging.handlers;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mlynum
 * @version 1.0
 */
import model.job.PiazzaJobType;
import model.job.metadata.ResourceMetadata;
import model.job.type.DescribeServiceMetadataJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.venice.piazza.servicecontroller.data.mongodb.accessors.MongoAccessor;
import org.venice.piazza.servicecontroller.util.CoreLogger;
import org.venice.piazza.servicecontroller.util.CoreServiceProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListServiceHandler implements PiazzaJobHandler { 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListServiceHandler.class);
	
	private MongoAccessor accessor;
	private CoreLogger coreLogger;
	private CoreServiceProperties coreServiceProperties;	
	private RestTemplate template;
	
	public ListServiceHandler(MongoAccessor accessor, CoreServiceProperties coreServiceProperties, CoreLogger coreLogger) {
		this.accessor = accessor;
		this.coreServiceProperties = coreServiceProperties;
		this.template = new RestTemplate();
		this.coreLogger = coreLogger;
	
	}
	//TODO needs to be implemented
	public ResponseEntity<List<String>> handle (PiazzaJobType jobRequest ) {
		
		LOGGER.debug("Listing services");
		
        ArrayList<String> retVal = new ArrayList<String>();
        ResponseEntity<String> handleResourceReturn = handle();
        retVal.add(handleResourceReturn.getBody());
        
		return new ResponseEntity<List<String>>(retVal,handleResourceReturn.getStatusCode());
		
	}
	
	public ResponseEntity<String> handle () {
		ResponseEntity<String> responseEntity = null;
		
		try {
	
			List<ResourceMetadata> rmList = accessor.list();
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(rmList);
			responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception ex) {
			
			LOGGER.error(ex.getMessage());
			responseEntity = new ResponseEntity<String>("Could not retrieve list of service metadata " , HttpStatus.NOT_FOUND);
			
		}
	
		return responseEntity;
		
	}

}