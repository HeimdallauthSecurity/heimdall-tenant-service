package com.heimdallauth.tenantservice;

import com.heimdallauth.tenantservice.config.TestContainerConfig;
import org.springframework.boot.SpringApplication;

public class TestTenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(TenantServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
