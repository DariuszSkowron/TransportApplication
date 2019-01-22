//package pl.skowrondariusz.TransportApplication.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.social.security.SpringSocialConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private UserDetailsService userDetailsService;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(
//                        "/auth/**", "/registration**" , "/forgot-password**", "/reset-password**", "/email/email-template", "/registrationConfirm**", "/resendToken**",
//                        "/js/**",
//                        "/css/**",
//                        "/img/**",
//                        "/webjars/**").permitAll()
//                .antMatchers("/admin", "/h2/**").hasRole("ADMIN").anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
////                .and()
////                .logout().deleteCookies("JSESSIONID")
////                .and()
////                .rememberMe()
////                .key("unique-and-secret")
////                .rememberMeCookieName("remember-me-cookie-name")
////                .tokenValiditySeconds(24 * 60 * 60)
////        .userDetailsService(userService);
//        http.exceptionHandling().accessDeniedPage("/403");
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//
//        return auth;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user")
////                .password("password").
////                .roles("ADMIN")
////                .and()
////                .withUser("manager")
////                .password("password")
////                .credentialsExpired(true)
////                .accountExpired(true)
////                .accountLocked(true)
////                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
////                .roles("MANAGER");
////    }
//
////        @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////            auth.inMemoryAuthentication()
////                    .withUser("john")
////                    .password(passwordEncoder().encode("123"))
////                    .roles("ADMIN");
////        }
//
////    @Override
////    public UserDetailsService userDetailsService() {
////        return userDetailsService;
////    }
//
//}