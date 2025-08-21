package com.springboot.day14proj2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.day14proj2.service.JwtAuthFilter;
import com.springboot.day14proj2.service.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtService jwtService;

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
                UserDetails admin = User.withUsername("admin").password("{noop}admin123")
                                .roles("ADMIN").build();
                UserDetails warden = User.withUsername("warden").password("{noop}warden123")
                                .roles("WARDEN").build();
                UserDetails user = User.withUsername("user").password("{noop}user123")
                                .roles("USER").build();
                return new InMemoryUserDetailsManager(admin, user, warden);
        }

        @Bean
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        @Bean
        public JwtAuthFilter jwtAuthFilter(UserDetailsService userDetailsService) {
                return new JwtAuthFilter(jwtService, userDetailsService);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter)
                        throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/auth/**").permitAll()
                                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/api/warden/**").hasRole("WARDEN")
                                                .requestMatchers("/api/student/**")
                                                .hasAnyRole("ADMIN", "USER", "WARDEN")
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .httpBasic();
                return http.build();
        }

}
