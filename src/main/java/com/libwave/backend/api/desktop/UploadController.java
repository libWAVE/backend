package com.libwave.backend.api.desktop;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.libwave.backend.service.FileService;
import com.libwave.backend.service.UuidService;

@Controller
public class UploadController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;

	@RequestMapping(path = "/api/desktop/upload")
	public void upload(String uuid, MultipartFile file, HttpServletResponse resp) throws Exception {

		String fileUuid = null;

		if (file != null && UuidService.isUuidValid(uuid)) {

			// Copy to temp folder
			File temp = new File(SystemUtils.getJavaIoTmpDir(), UUID.randomUUID().toString());

			file.transferTo(temp);

			fileService.upload(temp, file.getOriginalFilename());

			FileUtils.deleteDirectory(temp);

		}

		resp.getWriter().println(fileUuid);

	}
}
