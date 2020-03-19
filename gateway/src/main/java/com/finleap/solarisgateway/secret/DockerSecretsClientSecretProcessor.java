package com.finleap.solarisgateway.secret;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth.CLIENT_SECRET_KEY;

import com.finleap.solarisgateway.util.SolarisGatewayConstant.SecretKey;
import org.springframework.core.env.ConfigurableEnvironment;

public class DockerSecretsClientSecretProcessor extends AbstractDockerSecretsProcessor {

  public DockerSecretsClientSecretProcessor() {
    super(CLIENT_SECRET_KEY);
  }

  @Override
  protected String getFileName(ConfigurableEnvironment environment) {
    return environment.getProperty(SecretKey.CLIENT_SECRET_FILE_NAME_KEY);
  }
}