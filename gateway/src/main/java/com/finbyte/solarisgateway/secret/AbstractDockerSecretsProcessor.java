package com.finbyte.solarisgateway.secret;


import com.finbyte.solarisgateway.util.SolarisGatewayConstant.SecretKey;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

@Log4j2
public abstract class AbstractDockerSecretsProcessor implements EnvironmentPostProcessor, Ordered {

  private final String propertyName;

  protected AbstractDockerSecretsProcessor(String propertyName) {
    this.propertyName = propertyName;
  }

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    final String secretPath = environment.getProperty(SecretKey.BIND_PATH);

    if (!StringUtils.isEmpty(secretPath)) {
      final String fileName = getFileName(environment);

      if (!StringUtils.isEmpty(fileName)) {
        final Resource resource = new FileSystemResource(Path.of(secretPath, fileName));

        if (resource.exists()) {
          try {
            if (log.isDebugEnabled()) {
              log.debug("Using {}, {} from injected Docker secret file", fileName, propertyName);
            }

            final String secretValue = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
            environment.getSystemProperties().put(propertyName, secretValue);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else {
          log.warn("Docker secret is not loaded because secret file {} cannot be found ", fileName);
        }
      } else {
        log.warn("Docker secret is not loaded because secret file name is not configured. Application properties will be used if it's configured");
      }

    } else {
      log.warn("Docker secret is not loaded because docker-secret.bind-path property is empty ");
    }
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }

  protected abstract String getFileName(ConfigurableEnvironment environment);
}