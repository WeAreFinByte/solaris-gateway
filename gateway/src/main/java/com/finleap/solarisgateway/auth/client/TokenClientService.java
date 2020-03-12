package com.finleap.solarisgateway.auth.client;

import com.finleap.solarisgateway.auth.client.dto.Token;

/**
 *  Client Service to retrieve
 */
public interface TokenClientService {

  /**
   * Method returns Token from auth provider
   *
   * @return New Auth Token from provider
   */
  Token getToken();

}
