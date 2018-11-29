package com.libwave.backend.service;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class CommandService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Cache<String, List<String>> commands = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofMinutes(5))
			.build();
	
/*
	public void add(String uuid, String command) {
		if (UuidService.isUuidValid(uuid)) {
			pings.put(uuid, true);
			log.debug("Pinged: " + uuid);
		}
	}

*/
}
