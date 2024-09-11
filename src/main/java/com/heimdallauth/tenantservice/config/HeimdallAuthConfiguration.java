package com.heimdallauth.tenantservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "heimdallauth")
@NoArgsConstructor
@Getter
@Setter
public class HeimdallAuthConfiguration {
    private String kratosEndpointUrl;
    private String bifrostEndpointUrl;
}
