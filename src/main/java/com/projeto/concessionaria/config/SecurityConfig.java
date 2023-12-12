package com.projeto.concessionaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                //csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeHttpRequests(authz ->
                        authz.antMatchers("/**/admin/**").hasRole("ADMIN")
                                .antMatchers("/**/**").hasRole("USER")
                                .anyRequest()
                                .authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails admin = User.withUsername("Igor")
                .password(passwordEncoder.encode("testando"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails user = User.withUsername("Jonas")
                .password(passwordEncoder.encode("12345"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

}
