package com.iz.sandbox.feign;

import com.iz.sandbox.api.EventsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "events",  url = "${clients.events}")
public interface EventsApiClient extends EventsApi {
}
