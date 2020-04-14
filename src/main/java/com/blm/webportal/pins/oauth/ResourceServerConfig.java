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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		.antMatchers(HttpMethod.GET, "/api/ceves/getAll").permitAll()
		.antMatchers(HttpMethod.GET, "/api/ceves/get/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/ceves/edit/{id}").permitAll()//hasRole("CEVE")
		
		.antMatchers(HttpMethod.GET, "/api/binnacle/getAll").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/binnacle/get/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/binnacle/create").hasAnyRole("ADMIN","CEVE")
		
		//.antMatchers(HttpMethod.GET, "/users/search/**").denyAll()
		.antMatchers(HttpMethod.GET, "/users/search/findByMail").permitAll()
		
		.antMatchers(HttpMethod.GET, "/oauth/token").permitAll()
		
		.anyRequest().authenticated()
		//.and().cors().configurationSource(configurationSource())
		;
		/*
		CorsConfigurationSource source = corsConfigurationSource();
		http.addFilterBefore(new CorsFilter(source), ChannelProcessingFilter.class);
		*/
		
	}
	
	/*
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("OPTIONS");
        //more config
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }
	/*
	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
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
	*/

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
