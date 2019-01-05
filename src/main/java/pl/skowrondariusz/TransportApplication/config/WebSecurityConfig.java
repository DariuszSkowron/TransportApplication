package pl.skowrondariusz.TransportApplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//@PropertySource("application-oauth2.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//
//                .antMatchers("/images/**", "/", "/index").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        http.formLogin().defaultSuccessUrl("/indexLog", true);
//    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user =
                User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

//    @Configuration
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/images/**", "/", "/index", "/oauth_login").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/indexLog", true)
//                    .permitAll()
//                    .and()
//                    .oauth2Login()
//                    .loginPage("/oauth_login")
//                    .authorizationEndpoint()
//                    .baseUri("/oauth2/authorize-client")
//                    .authorizationRequestRepository(authorizationRequestRepository())
//                    .and()
//                    .tokenEndpoint()
//                    .accessTokenResponseClient(accessTokenResponseClient())
//                    .and()
//                    .failureUrl("/loginFailure")
//                    .defaultSuccessUrl("/indexLog", true);
//        }
            http.authorizeRequests()
                    .antMatchers("/oauth_login", "/loginFailure", "/")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .oauth2Login()
                    .loginPage("/oauth_login")
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(authorizationRequestRepository())
                    .and()
                    .tokenEndpoint()
                    .accessTokenResponseClient(accessTokenResponseClient())
                    .and()
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure");
        }


    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest>
    accessTokenResponseClient() {

        return new NimbusAuthorizationCodeTokenResponseClient();
    }
    }


