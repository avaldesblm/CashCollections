package com.blm.webportal.pins.oauth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/oauth/token").permitAll()
		//.antMatchers(HttpMethod.GET, "/ceves/get/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/ceves/edit/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		//.antMatchers(HttpMethod.POST, "/binnacle/create").permitAll()
		.antMatchers(HttpMethod.GET, "/users/search/findByUsername").permitAll()
		//.antMatchers(HttpMethod.GET, "/binnacle/**").hasRole("AUDIT")
		//.antMatchers(HttpMethod.GET, "/ceves/getAll").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/binnacle/**").hasRole("AUDIT")
		.antMatchers(HttpMethod.GET, "/ceves/getAll").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/users/search/findByMail").permitAll()
		.antMatchers(HttpMethod.GET, "/users/**").permitAll()
		.antMatchers(HttpMethod.GET, "/ceves/get/{id}").hasRole("CEVE")
		.anyRequest().authenticated()
		.and().cors().configurationSource(configurationSource());
	}
	
	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedOrigins(Arrays.asList("*"));
		cors.setAllowedMethods(Arrays.asList("POST","GET","PUT","OPTIONS"));
		cors.setAllowCredentials(true);
		cors.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(configurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(AccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter AccessTokenConverter() {
		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey("holi_crayoli");
		return token;
	}
	
}
