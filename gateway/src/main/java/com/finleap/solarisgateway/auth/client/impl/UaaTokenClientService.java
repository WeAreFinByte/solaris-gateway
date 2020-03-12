package com.finleap.solarisgateway.auth.client.impl;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.UAA.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient.Builder;

/**
 * Ticket Client implementation for UAA
 *
 * UAA and resource-server is mock project to test and development purpose. You should not use for production
 */
@Service
public class UaaTokenClientService extends AbstractTokenClientService {

  public UaaTokenClientService(@Value("${" + HTTP_METHOD_KEY + "}") String httpMethod, @Value("${" + BASE_URI_KEY + "}") String baseUri,//
      @Value("${" + URI_KEY + "}") String uri,//
      @Value("${" + CLIENT_ID_KEY + "}") String client_id,//
      @Value("${" + CLIENT_SECRET_KEY + "}") String client_secret,//
      @Value("${" + GRANT_TYPE_KEY + "}") String grant_type,//
      Builder webClientBuilder) {

    super(httpMethod, baseUri, uri, client_id, client_secret, grant_type, webClientBuilder);
  }
}
