package com.libwave.backend.api.client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libwave.backend.api.model.StringWrapper;
import com.libwave.backend.api.model.client.request.DownloadClientRequest;
import com.libwave.backend.service.FileService;
import com.libwave.backend.service.FileService.FileRecord;
import com.libwave.backend.service.UuidService;
import com.libwave.backend.service.client.ClientRequestsService;

@Controller
public class DownloadController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ClientRequestsService clientRequestsService;

	@RequestMapping(path = "/api/client/download/request")
	public StringWrapper download(@RequestBody DownloadClientRequest request) {

		if (UuidService.isUuidValid(request.getClientUuid()) && UuidService.isUuidValid(request.getDesktopUuid())) {

			log.debug("Add download client [" + request.getClientUuid() + "] request for desktop ["
					+ request.getDesktopUuid() + "]");

			clientRequestsService.add(request);

		}
 
		return new StringWrapper("");

	}
	
	@RequestMapping(path = "/api/client/download/{uuid}")
	public void download(@PathVariable String uuid, HttpServletResponse resp) {

		FileRecord file = fileService.download(uuid);

		if (file != null) {

			resp.setHeader("Content-disposition", "attachment; filename=" + file.getOriginalFilename());
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentLength((int) file.getFile().length());

			try (InputStream is = new BufferedInputStream(new FileInputStream(file.getFile()))) {
				IOUtils.copy(is, resp.getOutputStream());
			} catch (Exception e) {
				log.error("Error: ", e);
			}

		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
