package com.finbyte.solarisgateway.auth;

public interface AuthTokenService {

  String getToken();

  String getTokenWithJWTPrefix();
}
