package com.finbyte.solarisgateway.auth.client.impl;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_ID_KEY;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_SECRET_KEY;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.UAA.BASE_URI_KEY;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.UAA.GRANT_TYPE_KEY;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.UAA.HTTP_METHOD_KEY;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.UAA.URI_KEY;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Ticket Client implementation for UAA Oauth Token Retrieval
 *
 * UAA and resource-server is mock project to test and development purpose. You should not use for production
 */
@Service
public class UaaTokenClientService extends AbstractTokenClientService {

  public UaaTokenClientService(@Value("${" + HTTP_METHOD_KEY + "}") String httpMethod, @Value("${" + BASE_URI_KEY + "}") String baseUri,//
      @Value("${" + URI_KEY + "}") String uri,//
      @Value("${" + CLIENT_ID_KEY + "}") String client_id,//
      @Value("${" + CLIENT_SECRET_KEY + "}") String client_secret,//
      @Value("${" + GRANT_TYPE_KEY + "}") String grant_type) {

    super(httpMethod, baseUri, uri, client_id, client_secret, grant_type);
  }
}
