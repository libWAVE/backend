package com.libwave.backend.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.backend.api.model.BooleanWrapper;
import com.libwave.backend.api.model.StringWrapper;
import com.libwave.backend.api.model.client.request.DownloadClientRequest;
import com.libwave.backend.api.model.client.request.SearchClientRequest;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@RestController
public class RequestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientRequestsService clientRequestsService;

	@RequestMapping(path = "/api/client/requests/search/add")
	public BooleanWrapper search(@RequestBody SearchClientRequest request) {

		if (UuidService.isUuidValid(request.getClientUuid()) && UuidService.isUuidValid(request.getDesktopUuid())) {

			log.debug("Add search client [" + request.getClientUuid() + "] request for desktop ["
					+ request.getDesktopUuid() + "]");

			clientRequestsService.add(request);

		}

		return new BooleanWrapper(true);

	}

	@RequestMapping(path = "/api/client/requests/download/add")
	public StringWrapper download(@RequestBody DownloadClientRequest request) {

		if (UuidService.isUuidValid(request.getClientUuid()) && UuidService.isUuidValid(request.getDesktopUuid())) {

			log.debug("Add download client [" + request.getClientUuid() + "] request for desktop ["
					+ request.getDesktopUuid() + "]");

			clientRequestsService.add(request);

		}

		return new StringWrapper("");

	}
	
	

}
