package pl.skowrondariusz.TransportApplication.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import pl.skowrondariusz.TransportApplication.security.model.Role;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.security.utils.EncryptedPasswordUtils;

import java.util.Arrays;

@Component
@ConditionalOnProperty(name = "transportapp.database.recreate", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

        private UserRepository userRepository;

        public DatabaseInitializer(UserRepository userRepository){
            this.userRepository = userRepository;
        }

        @Override
        public void run(String... args){
            User defaultUser = new User("admin", "admin", "admin", "admin1", "admin", Arrays.asList(new Role("ROLE_USER")));
            defaultUser.setEnabled(true);
            String encrytedPassword = EncryptedPasswordUtils.encryptPassword(defaultUser.getPassword());
            defaultUser.setPassword("{bcrypt}" + encrytedPassword);
            this.userRepository.save(defaultUser);


            System.out.println("BASIC DATABASE INITIALIZED");
        }
    }
