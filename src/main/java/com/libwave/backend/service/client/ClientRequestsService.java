package com.libwave.backend.service.client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.MultimapBuilder;
import com.libwave.backend.api.model.client.request.ClientRequest;

@Service
public class ClientRequestsService {	

	private Cache<String, ClientRequest> requests = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofMinutes(1))
			.removalListener(new RemovalListener<String, ClientRequest>() {
				@Override
				public void onRemoval(RemovalNotification<String, ClientRequest> notification) {
					
				}
			}).build();

	public List<ClientRequest> getByDesktopUuid(String desktopUuid) {
		
		List<ClientRequest> requests = new ArrayList<>();
		
		for (ClientRequest)
		
		return requests;
		
	}

	public void add(ClientRequest request) {

	}

}
