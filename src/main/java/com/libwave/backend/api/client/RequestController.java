package com.libwave.backend.api.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.backend.api.model.BooleanWrapper;
import com.libwave.backend.api.model.client.request.ClientRequest;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@RestController
public class RequestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientRequestsService clientRequestsService;

	@RequestMapping(path = "/api/client/requests/add")
	public BooleanWrapper getClientRequests(@RequestBody ClientRequest request) {

		if (UuidService.isUuidValid(request.getClientUuid()) && UuidService.isUuidValid(request.getDesktopUuid())) {

			log.debug("Add client [" + request.getClientUuid() + "] requests for desktop [" + request.getDesktopUuid()
					+ "]");

			clientRequestsService.add(request);

		}

		return new BooleanWrapper(true);

	}

}
