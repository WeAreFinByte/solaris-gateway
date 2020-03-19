package com.finbyte.solarisgateway.util.properties;

import com.finbyte.solarisgateway.util.OauthProvider;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
@ConfigurationProperties(prefix = "com.finbyte.solarisgateway.auth")
@ConstructorBinding
@AllArgsConstructor
public class AuthProperties {

  @NotBlank
  private String provider;

  public OauthProvider getOauthProvider() {
    return OauthProvider.getOauthProvider(provider);
  }

}
