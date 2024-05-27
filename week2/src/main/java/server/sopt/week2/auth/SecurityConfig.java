package server.sopt.week2.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import server.sopt.week2.auth.filter.CustomAccessDeniedHandler;
import server.sopt.week2.auth.filter.CustomJwtAuthenticationEntryPoint;
import server.sopt.week2.auth.filter.JwtAuthenticationFilter;
import server.sopt.week2.auth.service.LogoutService;
//import server.sopt.week2.auth.service.LogoutService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final LogoutService authService;
    private static final String[] AUTH_WHITE_LIST = {
            "/api/v1/member",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "api/v1/token/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .requestCache(RequestCacheConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception ->
                {
                    exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                });
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AUTH_WHITE_LIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logoutConfig -> {
                    logoutConfig
                            .logoutUrl("/auth/logout")
                            .addLogoutHandler(authService)
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                });
        return http.build();
    }
}


