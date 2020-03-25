package com.finbyte.solarisgateway.auth.client;

import com.finbyte.solarisgateway.auth.client.dto.GenericToken;
import reactor.core.publisher.Mono;

/**
 * Client Service to retrieve
 */
public interface TokenClientService {

  /**
   * Method returns Token from auth provider
   *
   * @return New Auth Token from provider
   */
  Mono<GenericToken> getToken();

}
