package com.finleap.solarisgateway.auth.client.impl;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.BASE_URI_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.CLIENT_ID_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.CLIENT_SECRET_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.GRANT_TYPE_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.HTTP_METHOD_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Solaris.URI_KEY;

import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import java.util.Base64;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Solaris Client implementation for UAA
 */

@Service
public class SolarisTokenClientService extends AbstractTokenClientService {

  @Getter
  private String baseUri;

  @Getter
  private String uri;

  @Getter
  private String httpMethod;

  @Getter
  private String client_id;

  @Getter
  private String client_secret;

  @Getter
  private String grant_type;

  private MultiValueMap<String, String> params;

  private final WebClient webClient;

  public SolarisTokenClientService(@Value("${" + HTTP_METHOD_KEY + "}") String httpMethod,
      @Value("${" + BASE_URI_KEY + "}") String baseUri,//
      @Value("${" + URI_KEY + "}") String uri,//
      @Value("${" + CLIENT_ID_KEY + "}") String client_id,//
      @Value("${" + CLIENT_SECRET_KEY + "}") String client_secret,//
      @Value("${" + GRANT_TYPE_KEY + "}") String grant_type) {

    super(httpMethod, baseUri, uri, client_id, client_secret, grant_type);
    this.httpMethod = httpMethod;
    this.baseUri = baseUri;
    this.uri = uri;
    this.client_id = client_id;
    this.client_secret = client_secret;
    this.grant_type = grant_type;

    this.webClient = getWebClient(baseUri);

    this.params = prepareParams();
  }

  protected MultiValueMap<String, String> prepareParams() {

    this.params = new LinkedMultiValueMap<>();

    params.add("grant_type", grant_type);

    return params;
  }

  protected WebClient getWebClient(String baseUri) {
    return WebClient.builder().baseUrl(baseUri)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Token getToken() {
    return webClient.method(HttpMethod.valueOf(httpMethod)).
        uri(u -> u.path(uri).replaceQueryParams(params).build())
        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
            .encodeToString((client_id + ":" + client_secret).getBytes()))
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .retrieve().
            bodyToMono(GenericToken.class).block();
  }
}
