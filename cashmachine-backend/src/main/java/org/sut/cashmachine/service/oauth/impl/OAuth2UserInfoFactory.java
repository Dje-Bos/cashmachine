package org.sut.cashmachine.service.oauth.impl;

import org.sut.cashmachine.exception.OAuth2AuthenticationProcessingException;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.rest.dto.GoogleOAuth2UserInfoDTO;
import org.sut.cashmachine.rest.dto.OAuth2UserInfoDTO;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfoDTO getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthType.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfoDTO(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
