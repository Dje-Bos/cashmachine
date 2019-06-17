package org.sut.cashmachine.service.oauth;

import org.sut.cashmachine.exception.OAuth2AuthenticationProcessingException;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.rest.dto.GoogleOAuth2UserInfo;
import org.sut.cashmachine.rest.dto.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthType.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
