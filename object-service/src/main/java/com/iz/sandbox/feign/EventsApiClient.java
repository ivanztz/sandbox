package com.iz.sandbox.feign;

import com.iz.sandbox.object.event.api.EventsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "events")
public interface EventsApiClient extends EventsApi {
}
