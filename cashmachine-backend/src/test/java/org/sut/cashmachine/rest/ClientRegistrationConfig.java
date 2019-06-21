package org.sut.cashmachine.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class ClientRegistrationConfig {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(ClientRegistration.withRegistrationId("google").authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).clientId("clientId").clientSecret("926D96C90030DD58429D2751AC1BDBBC").redirectUriTemplate("http://callback.com").authorizationUri("http://auth.com").tokenUri("http://token.uri").build());
    }
}
