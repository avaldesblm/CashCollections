package com.blm.webportal.pins.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private InfoAditionalToken infoAditionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("frontendapp").secret(passwordEncoder.encode("12345"))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refreshToken")
			.accessTokenValiditySeconds(3600)
			.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain token = new TokenEnhancerChain(); 
		token.setTokenEnhancers(Arrays.asList(infoAditionalToken, AccessTokenConverter1()));
		endpoints.authenticationManager(authManager)
			.tokenStore(tokenStore1())
			.accessTokenConverter(AccessTokenConverter1())
			.tokenEnhancer(token);
	}
	
	@Bean
	public JwtTokenStore tokenStore1() {
		return new JwtTokenStore(AccessTokenConverter1());
	}

	@Bean
	public JwtAccessTokenConverter AccessTokenConverter1() {
		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey("holi_crayoli");
		return token;
	}
	
}
