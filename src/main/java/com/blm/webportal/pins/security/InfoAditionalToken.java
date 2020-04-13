package com.blm.webportal.pins.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import com.blm.webportal.pins.models.entity.User;
import com.blm.webportal.pins.models.entity.service.IUsuarioService;

@Component
public class InfoAditionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		User u = userService.findByUsername(authentication.getName());
		info.put("nombre", u.getName());
		info.put("apellido", u.getLastName());
		info.put("correo", u.getMail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
	
}
