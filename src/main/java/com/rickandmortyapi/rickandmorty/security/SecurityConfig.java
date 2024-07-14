package com.rickandmortyapi.rickandmorty.security;

import com.rickandmortyapi.rickandmorty.exception.ExceptionHandlingFilter;
import com.rickandmortyapi.rickandmorty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
    //Bu sinifin amaci adi ustunde security ile ilgili bilgileri vermek. Hangi endpoint hangi yetkilere sahip olacak gibi.
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.filterChain");
        http.addFilterBefore(new ExceptionHandlingFilter(), UsernamePasswordAuthenticationFilter.class);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                            x.requestMatchers("/user/**").permitAll()
                        )
                .authorizeHttpRequests(x ->
                            x.requestMatchers("/api/character/**").authenticated()
                                    .requestMatchers("/api/location/**").hasRole("ADMIN")
                        )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //AuthenticationProvider asil authentication islemlerinin gerceklestigi yer.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.out.println("SecurityConfig.authenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    //kullanicinin kimlik bilgileri authentication manager ile authenticationprovider'a iletilir.
    //AuthenticationProvider, kullanıcının sağladığı kimlik bilgilerini (UsernamePasswordAuthenticationToken gibi) doğrular
    //

    //Kullanicinin sifrelerinin validasyonunun yapildigi yer. Provider ile haberlesir.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        System.out.println("SecurityConfig.authenticationManager");
        return configuration.getAuthenticationManager();
    }
}
