package com.libwave.backend.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

@Service
public class HeartbeatService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Cache<String, Boolean> pings = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofMinutes(1L))
			.removalListener(new RemovalListener<String, Boolean>() {

				@Override
				public void onRemoval(RemovalNotification<String, Boolean> notification) {
					log.debug("Logged off: " + notification.getKey());
				}
			}).build();

	public void add(String uuid) {
		if (UuidService.isUuidValid(uuid)) {
			pings.put(uuid, true);
			log.debug("Pinged: " + uuid);
		}
	}

	public boolean isAlive(String uuid) {
		return UuidService.isUuidValid(uuid) && pings.getIfPresent(uuid) != null;
	}

}
