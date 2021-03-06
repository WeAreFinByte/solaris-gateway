package com.finbyte.solarisgateway.secret;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_ID_KEY;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.SecretKey;
import org.springframework.core.env.ConfigurableEnvironment;

public class DockerSecretsClientIdProcessor extends AbstractDockerSecretsProcessor {

  public DockerSecretsClientIdProcessor() {
    super(CLIENT_ID_KEY);
  }

  @Override
  protected String getFileName(ConfigurableEnvironment environment) {
    return environment.getProperty(SecretKey.CLIENT_ID_FILE_NAME_KEY);
  }
}