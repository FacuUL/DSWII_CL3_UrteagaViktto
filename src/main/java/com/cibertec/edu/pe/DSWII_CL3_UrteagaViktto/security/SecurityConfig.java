package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.security;

import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.service.DetalleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;



@AllArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private DetalleUsuarioService detalleUsuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/api/v1/auth/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()

                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new FiltroJWTAutorizacion(),
                        UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detalleUsuarioService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws  Exception{
        return configuration.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("supervisor").password("{noop}supervisor").roles("SUPERVISOR")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Autowired
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/filespdf/**")).hasRole("SUPERVISOR")
                .dispatcherTypeMatchers(HttpMethod.valueOf("/filesdoc/**")).hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

}
