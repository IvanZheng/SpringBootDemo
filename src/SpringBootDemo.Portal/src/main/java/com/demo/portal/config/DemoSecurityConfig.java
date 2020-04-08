package com.demo.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    Environment environment;

    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(environment.getProperty("postLogoutRedirectUri"));
        return successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM zero.teamcore.cn"));

        http.authorizeRequests()
                // allow anonymous access to the root page
                .antMatchers("/users/**", "/logout").permitAll()

                // all other requests
                .anyRequest().authenticated()

                // RP-initiated logout
                .and().logout().logoutSuccessHandler(oidcLogoutSuccessHandler())
                // enable OAuth2/OIDC
                .and().oauth2Login();
    }
}
