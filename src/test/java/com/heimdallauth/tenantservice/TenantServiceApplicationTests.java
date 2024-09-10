package com.heimdallauth.tenantservice;

import com.heimdallauth.tenantservice.config.TestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestContainerConfig.class)
@SpringBootTest
class TenantServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
