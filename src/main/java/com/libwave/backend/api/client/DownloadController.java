package com.libwave.backend.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(path = "/api/client/download/{uuid}")
	public void download(@PathVariable String uuid) {
		
	}

}
