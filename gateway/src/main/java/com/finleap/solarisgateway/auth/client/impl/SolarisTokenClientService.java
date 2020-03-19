package com.finleap.solarisgateway.auth.client.impl;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.BASE_URI_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_ID_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_SECRET_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.GRANT_TYPE_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.HTTP_METHOD_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.URI_KEY;

import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * Solaris Client implementation for Oauth Token Retrieval
 */

@Service
public class SolarisTokenClientService extends AbstractTokenClientService {

  public SolarisTokenClientService(@Value("${" + HTTP_METHOD_KEY + "}") String httpMethod,
      @Value("${" + BASE_URI_KEY + "}") String baseUri,//
      @Value("${" + URI_KEY + "}") String uri,//
      @Value("${" + CLIENT_ID_KEY + "}") String client_id,//
      @Value("${" + CLIENT_SECRET_KEY + "}") String client_secret,//
      @Value("${" + GRANT_TYPE_KEY + "}") String grant_type) {

    super(httpMethod, baseUri, uri, client_id, client_secret, grant_type);
  }


  @Override
  public Token getToken() {
    return webClient.method(HttpMethod.valueOf(getHttpMethod())).
        uri(u -> u.path(getUri()).replaceQueryParams(params).build())
        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
            .encodeToString((getClient_id() + ":" + getClient_secret()).getBytes()))
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .retrieve().
            bodyToMono(GenericToken.class).block();
  }
}
