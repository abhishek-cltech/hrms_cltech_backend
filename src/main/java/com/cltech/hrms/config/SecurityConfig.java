package com.cltech.hrms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	public static final String[] PUBLIC_URL= {
            "/authentication/**",
            "/employee/**",
            "/department/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-ui/**",
            "/swagger-resource/**",
            "/webjars/**",
           };
	
	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
    	 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers(PUBLIC_URL).permitAll()
         //.antMatchers(HttpMethod.GET).permitAll()
         .anyRequest()
         .authenticated()
         .and()
         //.formLogin()
         //.and()
         .httpBasic()
         .and()
         .cors()
         .and()
         .exceptionHandling()
         .and()
         .csrf()
         .disable();
    	 
    	 return http.build();
    }
    
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    protected InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
