package com.libwave.backend.service.client;

import java.time.Duration;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.libwave.api.model.client.ClientRequest;

@Service
public class ClientRequestsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	// Desktop UUID -> ClientRequest
	private Multimap<String, ClientRequest> desktopMappedClientRequest = ArrayListMultimap.create();

	// Request UUID -> ClientRequest
	private Cache<String, ClientRequest> requests = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofSeconds(60))
			.removalListener(new RemovalListener<String, ClientRequest>() {
				@Override
				public void onRemoval(RemovalNotification<String, ClientRequest> notification) {
					ClientRequest expiredRequest = notification.getValue();
					desktopMappedClientRequest.get(expiredRequest.getDesktopUuid()).remove(expiredRequest);
					log.debug("Expired request: " + expiredRequest.getUuid());
				}
			}).build();

	public void add(ClientRequest request) {
		desktopMappedClientRequest.put(request.getDesktopUuid(), request);
		requests.put(request.getUuid(), request);
		log.debug("Added request: " + request.getUuid());
	}

	public ClientRequest getByDesktopUuid(String desktopUuid) {

		if (desktopMappedClientRequest.containsKey(desktopUuid)) {

			Collection<ClientRequest> desktopRequests = desktopMappedClientRequest.get(desktopUuid);

			if (false == desktopRequests.isEmpty()) {

				ClientRequest request = desktopRequests.iterator().next();

				desktopRequests.remove(request);
				requests.invalidate(request.getUuid());
				
				log.debug("Fetched request: " + request);

				return request;
			}

		}

		return null;
	}

}
