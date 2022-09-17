/*
 * //package com.cltech.hrms.config; // //import
 * org.springframework.context.annotation.Bean; //import
 * org.springframework.context.annotation.Configuration; //import
 * org.springframework.security.authentication.AuthenticationManager; //import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; //import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; //import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * //import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; //import
 * org.springframework.security.core.userdetails.UserDetailsService; //import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; //import
 * org.springframework.security.web.SecurityFilterChain; // //import
 * com.cltech.hrms.security.user.UserDetailsServiceImpl; // //@Configuration
 * ////@EnableWebSecurity ////@EnableGlobalMethodSecurity(prePostEnabled = true)
 * //public class WebSecurityConfig { // @Bean // protected UserDetailsService
 * userDetailsService() { // return new UserDetailsServiceImpl(); // } //
 * // @Bean // protected BCryptPasswordEncoder passwordEncoder() { // return new
 * BCryptPasswordEncoder(); // } // // @Bean // protected AuthenticationManager
 * authenticationManager(AuthenticationConfiguration
 * authenticationConfiguration) throws Exception { // return
 * authenticationConfiguration.getAuthenticationManager(); // } // // @Bean //
 * protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
 * { // // http.csrf().disable().cors().disable() // .authorizeRequests() //
 * //.antMatchers("/login").permitAll() //
 * .antMatchers("/cltech/authentication/registration").permitAll() //
 * .antMatchers("/cltech/**" ).permitAll() //
 * //.antMatchers("/cltech/employee/**").permitAll() //
 * .anyRequest().authenticated() // .and().formLogin() //
 * .loginPage("/cltech/authentication/login") // .and() //
 * .logout().permitAll(); // return http.build(); // } // // //}
 * 
 * package com.cltech.hrms.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc; import
 * org.springframework.web.servlet.view.InternalResourceViewResolver;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableWebMvc
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true) public class
 * WebSecurityConfig extends WebSecurityConfigurerAdapter { public static final
 * String[] PUBLIC_URL= { "/authentication/**", "/employee/**", "/v3/api-docs",
 * "/v2/api-docs", "/swagger-resource/**", "/swagger-ui/**", "/webjars/**", };
 * 
 * @Qualifier("userDetailsServiceImpl")
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * 
 * @Bean protected BCryptPasswordEncoder bCryptPasswordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { //
 * http.cors().and() // .authorizeRequests() //
 * .antMatchers("/cltech/employee/**", "/cltech/authentication/**").permitAll()
 * // .anyRequest().authenticated() // .and() // .formLogin() ////
 * .loginPage("/login") // .permitAll() //// .and() //// .logout() ////
 * .permitAll() // .and() // .httpBasic(); //
 * http.cors().disable().csrf().disable(); http .csrf().disable()
 * .authorizeRequests() //.antMatchers(HttpMethod.POST,
 * "/employee/**").permitAll()
 * //.antMatchers(HttpMethod.POST,"/authentication/**").permitAll() //
 * .antMatchers("/authentication/**").permitAll() //
 * .antMatchers("/employee/**").permitAll() .antMatchers(PUBLIC_URL).permitAll()
 * // .antMatchers("/v3/api-docs").permitAll()
 * .antMatchers(HttpMethod.GET).permitAll() .anyRequest() .authenticated()
 * //.and() //.formLogin() .and() .httpBasic() .and() .cors() .and()
 * .exceptionHandling() .and() .csrf() .disable();
 * 
 * 
 * 
 * }
 * 
 * @Bean protected AuthenticationManager customAuthenticationManager() throws
 * Exception { return authenticationManager(); }
 * 
 * @Override public void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(userDetailsService).passwordEncoder(
 * bCryptPasswordEncoder()); }
 * 
 * // @Bean // protected InternalResourceViewResolver defaultViewResolver() { //
 * return new InternalResourceViewResolver(); // } }
 */