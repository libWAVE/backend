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
import org.springframework.web.bind.annotation.RequestMapping;

import com.libwave.api.Urls;
import com.libwave.backend.service.FileService;
import com.libwave.backend.service.FileService.FileRecord;

@Controller
public class DownloadController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;

	@RequestMapping(path = Urls.CLIENT_SUBMIT_DOWNLOAD_REQUEST_POLL)
	public void download(@PathVariable String requestUuid, HttpServletResponse resp) {

		FileRecord file = fileService.download(requestUuid);

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
