package com.finbyte.solarisgateway.util.properties;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "com.finbyte.solarisgateway.retry")
@ConstructorBinding
@AllArgsConstructor
@NoArgsConstructor
public class RetryProperties {

  private Integer retries = 2;

  private List<String> httpMethods;
  private List<Integer> httpStatuses;

  // backoff policy
  private Long firstBackoff = 50L;
  private Long maxBackoff = 500L;
  private Integer factor = 2;

  public HttpMethod[] getHttpMethods() {

    if (CollectionUtils.isEmpty(httpMethods)) {
      return HttpMethod.values();
    }

    return httpMethods.stream().map(HttpMethod::valueOf).toArray(HttpMethod[]::new);
  }

  public HttpStatus[] getHttpHttpStatuses() {

    if (CollectionUtils.isEmpty(httpStatuses)) {
      return new HttpStatus[]{HttpStatus.UNAUTHORIZED};
    }

    return httpStatuses.stream().map(HttpStatus::resolve).toArray(HttpStatus[]::new);
  }
}
