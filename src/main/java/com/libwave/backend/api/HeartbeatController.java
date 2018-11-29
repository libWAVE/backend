package com.libwave.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libwave.backend.api.model.BooleanWrapper;
import com.libwave.backend.api.model.HeartbeatRequest;
import com.libwave.backend.api.model.HeartbeatResponse;
import com.libwave.backend.service.HeartbeatService;

@RestController
public class HeartbeatController {

	@Autowired
	private HeartbeatService heartbeatService;

	@RequestMapping(path = "/api/heartbeat")
	public HeartbeatResponse heartbeat(@RequestBody HeartbeatRequest req) {

		// Record a heartbeat
		heartbeatService.add(req.getUuid());
		
		// Process commands
		// TODO

		return new HeartbeatResponse();
	}

	@RequestMapping(path = "/api/alive/{uuid}")
	public BooleanWrapper alive(@PathVariable String uuid) {
		return new BooleanWrapper(heartbeatService.isAlive(uuid));
	}

}
