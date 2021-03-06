package pl.skowrondariusz.TransportApplication.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import pl.skowrondariusz.TransportApplication.security.service.UserService;
import pl.skowrondariusz.TransportApplication.security.service.ConnectionSignUpImpl;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableSocial
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

    private boolean autoSignUp = false;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        try {
            this.autoSignUp = Boolean.parseBoolean(env.getProperty("social.auto-signup"));
        } catch (Exception e) {
            this.autoSignUp = false;
        }

        FacebookConnectionFactory facebookFactory = new FacebookConnectionFactory(Objects.requireNonNull(env.getProperty("facebook.app.id")), Objects.requireNonNull(env.getProperty("facebook.app.secret")));
        facebookFactory.setScope(env.getProperty("facebook.scope"));
        cfConfig.addConnectionFactory(facebookFactory);

        GoogleConnectionFactory googleFactory = new GoogleConnectionFactory(Objects.requireNonNull(env.getProperty("google.client.id")), Objects.requireNonNull(env.getProperty("google.client.secret")));
        googleFactory.setScope(env.getProperty("google.scope"));
        cfConfig.addConnectionFactory(googleFactory);

        LinkedInConnectionFactory linkedinFactory = new LinkedInConnectionFactory(Objects.requireNonNull(env.getProperty("linkedin.consumer.key")), Objects.requireNonNull(env.getProperty("linkedin.consumer.secret")));
        linkedinFactory.setScope(env.getProperty("linkedin.scope"));
        cfConfig.addConnectionFactory(linkedinFactory);

        TwitterConnectionFactory twitterFactory = new TwitterConnectionFactory(Objects.requireNonNull(env.getProperty("twitter.consumer.key")), Objects.requireNonNull(env.getProperty("twitter.consumer.secret")));
        cfConfig.addConnectionFactory(twitterFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());

        if (autoSignUp) {
            ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(userService);
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        } else {
            usersConnectionRepository.setConnectionSignUp(null);
        }
        return usersConnectionRepository;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,  ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

}
