package com.heimdallauth.tenantservice.clients;

import com.heimdallauth.tenantservice.dto.bifrost.BifrostSendEmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="bifrost-client", url="${heimdallauth.bifrost-endpoint-url}")
public interface BifrostClient {
    @RequestMapping("/v1/email/send")
    void sendEmail(@RequestBody BifrostSendEmailRequestDTO emailRequest);
}
