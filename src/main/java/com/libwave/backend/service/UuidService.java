package com.libwave.backend.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class UuidService {

	public static boolean isUuidValid(String uuid) {
		if (StringUtils.isNotEmpty(uuid) && uuid.length() == 36) {
			try {
				UUID.fromString(uuid);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}

}
