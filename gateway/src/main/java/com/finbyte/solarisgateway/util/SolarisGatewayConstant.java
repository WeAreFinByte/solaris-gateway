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
    String DEFAULT_TARGET_PREFIX_KEY = "com.finbyte.solarisgateway.targetprefix";
  }

  public interface EnvironmentProfileName {

    String DEV = "dev";
    String PROD = "prod";
    String NOT_PROD = "!" + PROD;
  }

  public static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

  public interface GatewayLogging {

    String ENABLED_GLOBAL_LOGGING_FILTER_KEY = "com.finbyte.solarisgateway.logging.global";
    String ENABLED_ELAPSED_TIME_LOGGING_FILTER_KEY = "com.finbyte.solarisgateway.logging.elapsedtime";
  }

  public interface Auth {

    String JWT_PREFIX = "Bearer ";
    String THRESHOLD_KEY = "com.finbyte.solarisgateway.auth.token.expire.threshold";
    String PROVIDER_KEY = "com.finbyte.solarisgateway.auth.provider";
    String CLIENT_ID_KEY = "com.finbyte.solarisgateway.auth.clientid";
    String CLIENT_SECRET_KEY = "com.finbyte.solarisgateway.auth.clientsecret";
    String HTTP_METHOD_KEY = "com.finbyte.solarisgateway.auth.httpmethod";
    String BASE_URI_KEY = "com.finbyte.solarisgateway.auth.baseuri";
    String URI_KEY = "com.finbyte.solarisgateway.auth.uri";
    String GRANT_TYPE_KEY = "com.finbyte.solarisgateway.auth.granttype";
  }

  public interface SecretKey {

    String BIND_PATH = "com.finbyte.solarisgateway.docker-secret.bind-path";
    String CLIENT_ID_FILE_NAME_KEY = "com.finbyte.solarisgateway.docker-secret.client-id";
    String CLIENT_SECRET_FILE_NAME_KEY = "com.finbyte.solarisgateway.docker-secret.client-secret";

  }

  public interface AuthProviderEnumValue {

    String SOLARIS = "solaris";
    String MOCK = "mock";
  }
}
