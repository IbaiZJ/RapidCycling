package com.rapidcycling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/",
            "/home",
            "/api/**",
            "/api/v1/auth/**",
    };

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated())
                // .formLogin((form) -> form
                //         .loginPage("/login")
                //         .loginProcessingUrl("/login")
                //         .successHandler(successHandler)
                //         // .defaultSuccessUrl("/", true) // redirect
                //         // .failureUrl("/login?error=true")
                //         .permitAll())
                // .rememberMe(remember -> remember
                //         .key(rememberMeKey)
                //         .rememberMeParameter("remember-me")
                //         .userDetailsService(userDetailsService)
                //         .tokenValiditySeconds(duration))
                // .sessionManagement(session -> session
                //         .maximumSessions(1)
                //         .maxSessionsPreventsLogin(false)
                //         .expiredUrl("/login?expired"))
                // .logout((logout) -> logout
                //         .logoutUrl("/logout")
                //         .deleteCookies("JSESSIONID", "remember-me")
                //         .invalidateHttpSession(true)
                //         .logoutSuccessUrl("/login?logout=true")
                //         .permitAll())
                ;
        return http.build();
    }

}
