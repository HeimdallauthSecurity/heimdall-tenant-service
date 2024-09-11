package com.heimdallauth.tenantservice;

import com.heimdallauth.tenantservice.config.HeimdallAuthConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.xml.namespace.QName;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {HeimdallAuthConfiguration.class})
public class TenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantServiceApplication.class, args);
    }

}
