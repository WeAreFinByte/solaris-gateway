package com.finleap.solarisgateway.util;

public final class SolarisGatewayConstant
{
    private SolarisGatewayConstant() {
    }

    public interface Gateway
    {
        String SECURITY_HEADER_KEY = "Authorization";
        String DEFAULT_URI_KEY = "com.finleap.solarisgateway.uri";
    }
}
