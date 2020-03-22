package com.finbyte.solarisgateway.auth.client.impl;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Ticket Client implementation for Solaris Mock Server
 *
 */
@Service
public class GeneralTokenClientService extends AbstractTokenClientService {

  public GeneralTokenClientService(@Value("${" + HTTP_METHOD_KEY + "}") String httpMethod, @Value("${" + BASE_URI_KEY + "}") String baseUri,//
      @Value("${" + URI_KEY + "}") String uri,//
      @Value("${" + CLIENT_ID_KEY + "}") String client_id,//
      @Value("${" + CLIENT_SECRET_KEY + "}") String client_secret,//
      @Value("${" + GRANT_TYPE_KEY + "}") String grant_type) {

    super(httpMethod, baseUri, uri, client_id, client_secret, grant_type);
  }
}
