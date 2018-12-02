package com.libwave.backend.api.desktop;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.api.Urls;
import com.libwave.api.model.client.ClientRequest;
import com.libwave.api.model.desktop.GetClientRequestsResponse;
import com.libwave.backend.service.HeartbeatService;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@RestController
public class DesktopRequestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientRequestsService clientRequestsService;

	@Autowired
	private HeartbeatService heartbeatService;

	@RequestMapping(path = Urls.DESKTOP_GET_CLIENT_REQUESTS)
	public GetClientRequestsResponse getClientRequests(@PathVariable String desktopUuid, HttpServletResponse response) {

		GetClientRequestsResponse resp = null;

		if (UuidService.isUuidValid(desktopUuid)) {

			heartbeatService.add(desktopUuid);

			log.debug("Get client requests for desktop: " + desktopUuid);

			ClientRequest clientRequest = clientRequestsService.getByDesktopUuid(desktopUuid);

			if (clientRequest != null) {

				resp = new GetClientRequestsResponse();
				resp.setRequest(clientRequest);

			}

		}

		log.debug("Got desktop request: " + resp);

		if (resp == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

		return resp;

	}

}
