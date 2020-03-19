package com.finbyte.solarisgateway.util;

/**
 * The class contains project constant and project properties key
 */
public final class SolarisGatewayConstant {

  public final static String PROPERTIES_ROOT_PACKAGE = "com.finbyte.solarisgateway.util.properties";

  private SolarisGatewayConstant() {
  }

  public interface RouteLocator {

    String DEFAULT_URI_KEY = "com.finbyte.solarisgateway.uri";
    String DEFAULT_PREFIX_KEY = "com.finbyte.solarisgateway.prefix";
  }

  public interface Auth {

    String JWT_PREFIX = "Bearer ";
    String THRESHOLD_KEY = "com.finbyte.solarisgateway.auth.token.expire.threshold";
    String PROVIDER_KEY = "com.finbyte.solarisgateway.auth.provider";
    String CLIENT_ID_KEY = "com.finbyte.solarisgateway.auth.clientid";
    String CLIENT_SECRET_KEY = "com.finbyte.solarisgateway.auth.clientsecret";
  }

  public interface UAA {

    String HTTP_METHOD_KEY = "com.finbyte.solarisgateway.auth.uaa.httpmethod";
    String BASE_URI_KEY = "com.finbyte.solarisgateway.auth.uaa.baseuri";
    String URI_KEY = "com.finbyte.solarisgateway.auth.uaa.uri";
    String GRANT_TYPE_KEY = "com.finbyte.solarisgateway.auth.uaa.granttype";
  }

  public interface Solaris {

    String HTTP_METHOD_KEY = "com.finbyte.solarisgateway.auth.solaris.httpmethod";
    String BASE_URI_KEY = "com.finbyte.solarisgateway.auth.solaris.baseuri";
    String URI_KEY = "com.finbyte.solarisgateway.auth.solaris.uri";
    String GRANT_TYPE_KEY = "com.finbyte.solarisgateway.auth.solaris.granttype";

  }

  public interface SecretKey {

    String BIND_PATH = "com.finbyte.solarisgateway.docker-secret.bind-path";
    String CLIENT_ID_FILE_NAME_KEY = "com.finbyte.solarisgateway.docker-secret.client-id";
    String CLIENT_SECRET_FILE_NAME_KEY = "com.finbyte.solarisgateway.docker-secret.client-secret";

  }

  public interface Retry {

    String RETRY_COUNT = "com.finbyte.solarisgateway.retry.count";
  }

  public interface AuthProviderEnumValue {

    String SOLARIS = "solaris";
    String UAA = "uaa";
  }
}