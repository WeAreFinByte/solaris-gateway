package com.finleap.solarisgateway.security;

import com.finleap.solarisgateway.security.dto.Role;
import com.finleap.solarisgateway.util.properties.SolarisSecurityProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
    return http
        .csrf().disable()
        .authorizeExchange()
        .pathMatchers("/actuator/**").hasRole(Role.MANAGER.toString())
        .pathMatchers("/").permitAll()
        .anyExchange().authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin().disable()
        .build();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsService(SolarisSecurityProperties properties) {

    Collection<UserDetails> userDetails = new ArrayList<>();
    Optional.ofNullable(properties.getUsers()).orElseThrow().forEach(solarisUser ->
    {

      final UserDetails user = User
          .withUsername(solarisUser.getName())
          .password(passwordEncoder().encode(solarisUser.getPassword()))
          .disabled(!solarisUser.isEnabled())
          .roles(solarisUser.getRoles().stream().map(role -> Role.valueOf(role).toString()).toArray(String[]::new))
          .build();

      userDetails.add(user);

    });

    return new MapReactiveUserDetailsService(userDetails);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}