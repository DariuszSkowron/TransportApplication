//package pl.skowrondariusz.TransportApplication.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security_2.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
////import org.springframework.security_2.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
////import org.springframework.security_2.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
////import org.springframework.security_2.oauth2.client.web.AuthorizationRequestRepository;
////import org.springframework.security_2.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
////import org.springframework.security_2.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//import org.springframework.social.security.SpringSocialConfigurer;
//import pl.skowrondariusz.TransportApplication.security_2.AppRole;
////import pl.skowrondariusz.TransportApplication.service.MyUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
////@PropertySource("application-oauth2.properties")
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//////    @Override
//////    protected void configure(HttpSecurity http) throws Exception {
//////        http
//////                .authorizeRequests()
//////
//////                .antMatchers("/images/**", "/", "/index").permitAll()
//////                .anyRequest().authenticated()
//////                .and()
//////                .formLogin()
//////                .loginPage("/login")
//////                .permitAll()
//////                .and()
//////                .logout()
//////                .permitAll();
//////        http.formLogin().defaultSuccessUrl("/indexLog", true);
//////    }
////
//////    @Bean
//////    @Override
//////    public UserDetailsService userDetailsService(){
//////        UserDetails user =
//////                User.withDefaultPasswordEncoder()
//////                .username("user")
//////                .password("password")
//////                .roles("USER")
//////                .build();
//////
//////        return new InMemoryUserDetailsManager(user);
//////    }
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("john")
////                .password(passwordEncoder().encode("123"))
////                .roles("USER");
////    }
////
//////    @Configuration
//////    public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////            http.authorizeRequests()
////                    .antMatchers("/images/**", "/", "/index", "/oauth_login", "/h2/**").permitAll()
////                    .anyRequest().authenticated()
////                    .and()
////                    .formLogin()
////                    .loginPage("/login")
//////                    .defaultSuccessUrl("/indexLog", true)
////                    .permitAll()
////                    .and()
////                    .logout()
////                    .invalidateHttpSession(true)
////                    .clearAuthentication(true)
////                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                    .logoutSuccessUrl("/login?logout")
////                    .permitAll()
////                    .and()
////                    .oauth2Login()
////                    .loginPage("/oauth_login")
////                    .authorizationEndpoint()
////                    .baseUri("/oauth2/authorization")
////                    .authorizationRequestRepository(authorizationRequestRepository())
////                    .and()
////                    .tokenEndpoint()
////                    .accessTokenResponseClient(accessTokenResponseClient())
////                    .and()
////                    .failureUrl("/loginFailure")
//////                    .defaultSuccessUrl("/indexLog", true)
////                    .and()
////                    .logout()
////                    .invalidateHttpSession(true)
////                    .clearAuthentication(true)
////                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                    .logoutSuccessUrl("/login?logout")
////                    .permitAll();
////        }
//////            http.authorizeRequests()
//////                    .antMatchers("/oauth_login", "/loginFailure", "/")
//////                    .permitAll()
//////                    .anyRequest()
//////                    .authenticated()
//////                    .and()
//////                    .oauth2Login()
//////                    .loginPage("/oauth_login")
//////                    .authorizationEndpoint()
//////                    .baseUri("/oauth2/authorization")
//////                    .authorizationRequestRepository(authorizationRequestRepository())
//////                    .and()
//////                    .tokenEndpoint()
//////                    .accessTokenResponseClient(accessTokenResponseClient())
//////                    .and()
//////                    .defaultSuccessUrl("/loginSuccess")
//////                    .failureUrl("/loginFailure");
//////        }
////
////
////    @Bean
////    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
////        return new HttpSessionOAuth2AuthorizationRequestRepository();
////    }
////
////    @Bean
////    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest>
////    accessTokenResponseClient() {
////
////        return new NimbusAuthorizationCodeTokenResponseClient();
////    }
////
//////    @Autowired
//////    private MyUserDetailsService userDetailsService;
//////
//////    @Override
//////    protected void configure(AuthenticationManagerBuilder auth)
//////            throws Exception {
//////        auth.userDetailsService(userDetailsService);
//////    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/", "/signup", "/login", "/logout", "/register", "/confirm").permitAll();
//        http.authorizeRequests().antMatchers("/userInfo").access("hasRole('" + AppRole.ROLE_USER + "')");
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('" + AppRole.ROLE_ADMIN + "')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login")
//                .defaultSuccessUrl("/userInfo")
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password");
//        http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
//        http.apply(new SpringSocialConfigurer()).signupUrl("/signup");
//    }
//
//    @Override
//    public UserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
//    }
//
//
