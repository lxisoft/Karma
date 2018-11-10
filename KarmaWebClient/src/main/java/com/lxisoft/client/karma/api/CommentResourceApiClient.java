package com.lxisoft.client.karma.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.client.karma.ClientConfiguration;

@FeignClient(name="${karma.name:karma}", url="${karma.url:localhost:8081/}", configuration = ClientConfiguration.class)
public interface CommentResourceApiClient extends CommentResourceApi {
}