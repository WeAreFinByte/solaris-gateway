package com.finbyte.solarisgateway.auth;

import com.finbyte.solarisgateway.auth.client.dto.GenericToken;
import reactor.core.publisher.Mono;

public interface TokenService {

  String getToken();

  String getTokenWithJWTPrefix();

  Mono<GenericToken> refreshToken();
}
