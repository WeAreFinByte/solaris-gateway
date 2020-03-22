package com.finbyte.solaris.mockserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient("login-client1")
				.secret(passwordEncoder.encode("secret"))
				.authorities("ROLE_A","ROLE_B","ROLE_TRUSTED_CLIENT")
				.scopes("all")
				.authorizedGrantTypes("client_credentials")
				.and()
				.withClient("login-client")
				.secret(passwordEncoder.encode("secret"))
				.authorities("ROLE_C")
				.scopes("all")
				.authorizedGrantTypes("client_credentials");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.checkTokenAccess("hasAuthority('ROLE_C')");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager);
	}

}
