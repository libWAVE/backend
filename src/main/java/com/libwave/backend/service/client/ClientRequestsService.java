package com.libwave.backend.service.client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.libwave.backend.api.model.client.request.ClientRequest;

@Service
public class ClientRequestsService {

	// Desktop UUID -> ClientRequest
	private Multimap<String, ClientRequest> desktopMappedClientRequest = ArrayListMultimap.create();

	// Request UUID -> ClientRequest
	private Cache<String, ClientRequest> requests = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofMinutes(1))
			.removalListener(new RemovalListener<String, ClientRequest>() {
				@Override
				public void onRemoval(RemovalNotification<String, ClientRequest> notification) {
					ClientRequest expiredRequest = notification.getValue();
					desktopMappedClientRequest.get(expiredRequest.getDesktopUuid()).remove(expiredRequest);
				}
			}).build();

	public void add(ClientRequest request) {
		desktopMappedClientRequest.put(request.getDesktopUuid(), request);
		requests.put(request.getUuid(), request);
	}

	public List<ClientRequest> getByDesktopUuid(String desktopUuid) {
		return new ArrayList<>(desktopMappedClientRequest.get(desktopUuid));
	}

}
