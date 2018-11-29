package com.libwave.backend.api.desktop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.backend.api.model.client.request.ClientRequest;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@RestController
public class RequestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientRequestsService clientRequestsService;

	@RequestMapping(path = "/api/desktop/requests/get/{uuid}")
	public List<ClientRequest> getClientRequests(@PathVariable String uuid) {

		List<ClientRequest> resp = new ArrayList<>();

		if (UuidService.isUuidValid(uuid)) {

			log.debug("Get client requests for desktop: " + uuid);

			resp = clientRequestsService.getByDesktopUuid(uuid);

		}

		return resp;

	}

}
