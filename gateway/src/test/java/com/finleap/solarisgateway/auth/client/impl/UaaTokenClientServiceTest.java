package com.finleap.solarisgateway.auth.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

public class UaaTokenClientServiceTest {

  public static MockWebServer mockBackEnd;

  private ObjectMapper MAPPER = new ObjectMapper();

  private UaaTokenClientService uaaTokenClientService;

  private String baseUri;

  private String uri = "/path";

  private String httpMethod = HttpMethod.GET.toString();

  private String clientId = "clientId";

  private String clientSecret = "clientSecret";

  private String grantType = "login-client";

  @BeforeAll
  public static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  public static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeEach
  void initialize() {
    baseUri = String.format("http://localhost:%s",
        mockBackEnd.getPort());

    uaaTokenClientService = new UaaTokenClientService(httpMethod, baseUri, uri, clientId, clientSecret, grantType);
  }

  @Test
  public void testUaaTokenParameters() {

    assertEquals(httpMethod, uaaTokenClientService.getHttpMethod());
    assertEquals(baseUri, uaaTokenClientService.getBaseUri());
    assertEquals(uri, uaaTokenClientService.getUri());
    assertEquals(clientId, uaaTokenClientService.getClient_id());
    assertEquals(clientSecret, uaaTokenClientService.getClient_secret());
    assertEquals(grantType, uaaTokenClientService.getGrant_type());

  }

  @Test
  public void getTokenShouldReturnTokenWhenHttpMethodGet() throws Exception {

    // Given
    final GenericToken token = new GenericToken("theAccessToken", "bearer", 1000L, "theScope", "theJTI");
    mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(token))
        .addHeader("Content-Type", "application/json"));

    //When
    final Token returnToken = uaaTokenClientService.getToken();

    assertNotNull(returnToken);
    assertTrue(returnToken instanceof GenericToken);

    //Then
    final RecordedRequest recordedRequest = mockBackEnd.takeRequest();
    assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
    assertEquals("/path?client_id=clientId&client_secret=clientSecret&grant_type=login-client", recordedRequest.getPath());

  }

  @Test
  public void getTokenShouldReturnTokenWhenHttpMethodPost() throws Exception {

    // Given
    uaaTokenClientService = new UaaTokenClientService(HttpMethod.POST.toString(), baseUri, uri, clientId, clientSecret, grantType);

    final GenericToken token = new GenericToken("theAccessToken", "bearer", 1000L, "theScope", "theJTI");

    mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(token))
        .addHeader("Content-Type", "application/json"));

    //When
    final Token returnToken = uaaTokenClientService.getToken();

    assertNotNull(returnToken);
    assertTrue(returnToken instanceof GenericToken);

    //Then
    final RecordedRequest recordedRequest = mockBackEnd.takeRequest();
    assertEquals(HttpMethod.POST.toString(), recordedRequest.getMethod());
    assertEquals("/path?client_id=clientId&client_secret=clientSecret&grant_type=login-client", recordedRequest.getPath());

  }

  @Test
  public void getTokenShouldReturnTokenWithCorrectValue() throws Exception {

    // Given
    final String accessToken = "testToken";
    final String tokenType = "tokenType";
    final Long expiresIn = 100L;
    final String scope = "scope";
    final String jti = "jti";

    final GenericToken token = new GenericToken(accessToken, tokenType, expiresIn, scope, jti);

    mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(token))
        .addHeader("Content-Type", "application/json"));

    //When
    final Token returnToken = uaaTokenClientService.getToken();

    assertNotNull(returnToken);
    assertTrue(returnToken instanceof GenericToken);

    final GenericToken returnTokenAsGeneric = (GenericToken) returnToken;
    assertEquals(accessToken, returnTokenAsGeneric.getAccessToken());
    assertEquals(tokenType, returnTokenAsGeneric.getTokenType());
    assertEquals(expiresIn, returnTokenAsGeneric.getExpiresIn());
    assertEquals(scope, returnTokenAsGeneric.getScope());
    assertEquals(jti, returnTokenAsGeneric.getJti());

    //Then
    final RecordedRequest recordedRequest = mockBackEnd.takeRequest();
    assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
    assertEquals("/path?client_id=clientId&client_secret=clientSecret&grant_type=login-client", recordedRequest.getPath());

  }

}