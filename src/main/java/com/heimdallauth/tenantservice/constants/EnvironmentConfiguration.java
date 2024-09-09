package com.heimdallauth.tenantservice.constants;

public enum EnvironmentConfiguration {
    DEV(500),
    PROD(1000000);
    public final int USER_LIMIT;
    EnvironmentConfiguration(int userLimit) {
        this.USER_LIMIT = userLimit;
    }
}
