package com.finleap.solarisgateway.auth;

public interface AuthTokenService {

  String getToken();

  String getTokenWithJWTPrefix();
}
