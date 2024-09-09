package com.heimdallauth.tenantservice.constants;

public enum EnvironmentConfiguration {
    DEV(500),
    PROD(1000000);
    public final int userLimit;

    EnvironmentConfiguration(int userLimit) {
        this.userLimit = userLimit;
    }
}
