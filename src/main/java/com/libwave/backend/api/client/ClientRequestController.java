package com.libwave.backend.api.client;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.api.Urls;
import com.libwave.api.model.client.ClientRequest;
import com.libwave.api.model.common.BooleanWrapper;
import com.libwave.api.model.common.StringWrapper;
import com.libwave.backend.service.HeartbeatService;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@RestController
public class ClientRequestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientRequestsService clientRequestsService;

	@Autowired
	private HeartbeatService heartbeatService;

	@RequestMapping(path = Urls.CLIENT_CHECK_DESKTOP_STATUS)
	public BooleanWrapper isDesktopAlive(@PathVariable String desktopUuid) {
		return new BooleanWrapper(heartbeatService.isAlive(desktopUuid));
	}

	@RequestMapping(path = Urls.CLIENT_SUBMIT_REQUEST)
	public StringWrapper search(@RequestBody ClientRequest request) {

		request.setUuid(UUID.randomUUID().toString());

		if (UuidService.isUuidValid(request.getClientUuid()) && UuidService.isUuidValid(request.getDesktopUuid())) {

			log.debug("Add client request [" + request + "] for desktop [" + request.getDesktopUuid() + "]");

			clientRequestsService.add(request);

		}

		return new StringWrapper(request.getUuid());

	}

}
