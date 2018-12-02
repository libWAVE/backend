package com.libwave.backend.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FileService implements InitializingBean, DisposableBean {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${files.folder}")
	private String folder;

	private Set<FileRecord> files = new HashSet<>();

	@SuppressWarnings("serial")
	public class FileRecord implements Serializable {

		private File file;
		private String uuid;
		private Date date = new Date();
		private Date expirationDate = DateUtils.addHours(new Date(), 1);
		private String originalFilename;

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public Date getExpirationDate() {
			return expirationDate;
		}

		public Date getDate() {
			return date;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public String getOriginalFilename() {
			return originalFilename;
		}

		public void setOriginalFilename(String originalFilename) {
			this.originalFilename = originalFilename;
		}

		@Override
		public String toString() {
			return "FileRecord [file=" + file + ", uuid=" + uuid + ", date=" + date + ", expirationDate="
					+ expirationDate + ", originalFilename=" + originalFilename + "]";
		}

	}

	public FileRecord download(String uuid) {

		if (UuidService.isUuidValid(uuid)) {

			File file = new File(folder, uuid);
			if (file.exists()) {

				log.debug("Download file: " + file);

				for (FileRecord fr : files) {
					if (fr.getUuid().equals(uuid)) {
						return fr;
					}
				}

			}
		}

		return null;

	}

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_HOUR)
	public void deleteFiles() {

		final Date now = new Date();

		for (final FileRecord file : files) {

			if (file.getExpirationDate().before(now)) {

				file.getFile().delete();

				files.remove(file);

			}

		}

	}

	/**
	 * Upload a file
	 */
	public FileRecord upload(File file, String originalFilename) {

		FileRecord fileRecord = null;

		// Make a copy
		try {

			String uuid = UUID.randomUUID().toString();

			File newFile = new File(folder, uuid);

			FileUtils.copyFileToDirectory(file, newFile);

			fileRecord = new FileRecord();
			fileRecord.setFile(newFile);
			fileRecord.setOriginalFilename(originalFilename);
			fileRecord.setUuid(uuid);

			this.files.add(fileRecord);

			log.debug("Uploaded: " + fileRecord);

		} catch (IOException e) {
			log.error("Error saving file: ", e);
		}

		return fileRecord;

	}

	private void cleanup() {
		log.debug("Clean up files");
		try {
			FileUtils.cleanDirectory(new File(folder));
		} catch (IOException e) {
			log.error("Error cleaning up folder: " + folder);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		if (false == new File(folder).exists()) {
			FileUtils.forceMkdir(new File(folder));
		}

		cleanup();
	}

	@Override
	public void destroy() throws Exception {
		cleanup();
	}

}
