package com.finleap.solarisgateway.auth.client.impl;

import com.finleap.solarisgateway.auth.client.TokenClientService;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractTokenClientService implements TokenClientService {

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

  public AbstractTokenClientService(String httpMethod, String baseUri, String uri, String client_id, String client_secret, String grant_type,
      WebClient.Builder webClientBuilder) {

    this.httpMethod = httpMethod;
    this.baseUri = baseUri;
    this.uri = uri;
    this.client_id = client_id;
    this.client_secret = client_secret;
    this.grant_type = grant_type;

    this.webClient = getWebClient(webClientBuilder);

    this.params = new LinkedMultiValueMap<>();

    params.add("client_id",client_id);
    params.add("client_secret",client_secret);
    params.add("grant_type",grant_type);
  }

  protected WebClient getWebClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder.baseUrl(baseUri).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Token getToken() {
    return webClient.method(HttpMethod.valueOf(httpMethod)).
        uri(u -> u.path(uri).replaceQueryParams(params).build())
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .retrieve().
            bodyToMono(GenericToken.class).block();
  }
}