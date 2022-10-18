package com.iz.sandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
public class KeyCloakJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public static final String ROLES_FIELD = "roles";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String RESOURCE_ACCESS_FIELD = "resource_access";

    private final String clientName;
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    public KeyCloakJwtAuthenticationConverter(String clientName) {
        this.clientName = clientName;
        jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
       final Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(source);
        /**
         * Mapping default keycloak role claims for client to make them authorities
         */
       authorities.addAll( Optional.ofNullable((Map<String, Object>)source.getClaim(RESOURCE_ACCESS_FIELD))
               .map(claim -> (Map<String, Object>)claim.get(clientName))
               .map(roles ->  ((Collection<String>) roles.get(ROLES_FIELD))).orElse(Collections.emptyList())
               .stream()
               .map(x -> new SimpleGrantedAuthority(ROLE_PREFIX + x))
               .collect(Collectors.toList()));
       return  authorities;
    }
}
