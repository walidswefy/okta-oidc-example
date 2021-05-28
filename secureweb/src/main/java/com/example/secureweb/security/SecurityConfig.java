package com.example.secureweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        System.out.println(clientRegistrationRepository.findByRegistrationId("x"));
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:8080");
        return successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // all other requests
                .anyRequest().authenticated()
                // enable OAuth2/OIDC
                .and().oauth2Login()
                // After we logout, redirect to root page, by default Spring will send you to /login?logout
                .and().logout().logoutSuccessHandler(oidcLogoutSuccessHandler())
                .invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID");
    }
}
