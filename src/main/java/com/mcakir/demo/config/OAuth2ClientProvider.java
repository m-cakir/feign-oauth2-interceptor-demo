package com.mcakir.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.String.format;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2ClientProvider {

    public static final Authentication ANONYMOUS_USER_AUTHENTICATION =
            new AnonymousAuthenticationToken("key", "anonymous", createAuthorityList("ROLE_ANONYMOUS"));

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public String getAuthenticationToken(final String clientRegistrationId) {

        final OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId)
                .principal(ANONYMOUS_USER_AUTHENTICATION)
                .build();

        OAuth2AuthorizedClient client = authorizedClientManager.authorize(request);

        if (Objects.isNull(client) || Objects.isNull(client.getAccessToken())) {
            throw new RuntimeException("error on receiving token");
        }

        log.info("getAuthenticationToken ::: clientRegistrationId={}, accessToken={}", clientRegistrationId, client.getAccessToken().getTokenValue());

        return format("Bearer %s", client.getAccessToken().getTokenValue());
    }
}
