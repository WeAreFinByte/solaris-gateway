package com.finbyte.solarisgateway.util.properties;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "com.finbyte.solarisgateway.security")
public class SolarisSecurityProperties {

  @NotNull
  private List<SolarisUser> users;

  @Getter
  @Setter
  public static class SolarisUser {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private List<String> roles;

    boolean enabled;
  }
}
