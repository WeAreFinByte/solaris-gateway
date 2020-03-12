package com.finleap.solarisgateway.util;

/**
 * The class contains project constant and project properties key
 */
public final class SolarisGatewayConstant {

  private SolarisGatewayConstant() {
  }

  public interface RouteLocator {

    String DEFAULT_URI_KEY = "com.finleap.solarisgateway.uri";
  }

  public interface Auth {

    String JWT_PREFIX = "Bearer ";
    String THRESHOLD_KEY = "com.finleap.solarisgateway.auth.token.expire.threshold";
    String PROVIDER_KEY = "com.finleap.solarisgateway.auth.provider";
  }

  public interface UAA {

    String HTTP_METHOD_KEY = "com.finleap.solarisgateway.auth.uaa.httpmethod";
    String BASE_URI_KEY = "com.finleap.solarisgateway.auth.uaa.baseuri";
    String URI_KEY = "com.finleap.solarisgateway.auth.uaa.uri";
    String CLIENT_ID_KEY = "com.finleap.solarisgateway.auth.uaa.clientid";
    String CLIENT_SECRET_KEY = "com.finleap.solarisgateway.auth.uaa.clientsecret";
    String GRANT_TYPE_KEY = "com.finleap.solarisgateway.auth.uaa.granttype";
  }

  public interface Solaris {

    String HTTP_METHOD_KEY = "com.finleap.solarisgateway.auth.solaris.httpmethod";
    String BASE_URI_KEY = "com.finleap.solarisgateway.auth.solaris.baseuri";
    String URI_KEY = "com.finleap.solarisgateway.auth.solaris.uri";
    String CLIENT_ID_KEY = "com.finleap.solarisgateway.auth.solaris.clientid";
    String CLIENT_SECRET_KEY = "com.finleap.solarisgateway.auth.solaris.clientsecret";
    String GRANT_TYPE_KEY = "com.finleap.solarisgateway.auth.solaris.granttype";

  }

  public interface Retry {

    String RETRY_COUNT = "com.finleap.solarisgateway.retry.count";
  }

  public interface AuthProviderEnumValue {

    String SOLARIS = "solaris";
    String UAA = "uaa";
  }


}
