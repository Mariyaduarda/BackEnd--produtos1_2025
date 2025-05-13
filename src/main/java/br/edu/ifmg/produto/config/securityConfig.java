package br.edu.ifmg.produto.config;

import
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Bean
public SecurityFilterChain securityFilterChain{
    http.authorizedRequests(
            AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry
                autorize.requesMatchers("/**").permitAll()
.anyRequest()aurheticated()
        .formLogin();
)
}
