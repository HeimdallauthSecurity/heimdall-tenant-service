package com.heimdallauth.tenantservice.clients;

import com.heimdallauth.tenantservice.dto.kratos.KratosAccountInformation;
import com.heimdallauth.tenantservice.dto.kratos.KratosUserAccountCreationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="kratos-client", url="${heimdallauth.kratos-endpoint-url}")
public interface KratosClient {
    @PostMapping("/users")
    KratosAccountInformation createAdministratorUserAccount(@RequestBody KratosUserAccountCreationDTO accountInformation);
}
