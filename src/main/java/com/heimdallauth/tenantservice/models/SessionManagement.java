package com.heimdallauth.tenantservice.models;

public record SessionManagement(
        int sessionTimeout,
        int sessionTimeoutRememberMe,
        int sessionTimeoutRememberMeMax,
        boolean sessionTimeoutRememberMeExtend,
        boolean sessionTimeoutRememberMeRenew,
        boolean sessionTimeoutRememberMeRenewExtend
) {
}
