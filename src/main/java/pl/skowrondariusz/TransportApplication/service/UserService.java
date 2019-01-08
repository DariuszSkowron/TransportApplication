package pl.skowrondariusz.TransportApplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import pl.skowrondariusz.TransportApplication.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.dto.UserDto;
import pl.skowrondariusz.TransportApplication.repository.RoleRepository;
import pl.skowrondariusz.TransportApplication.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.validation.EmailExistsException;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

         @Autowired
        private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;


        @Override
        public User registerNewUserAccount(final UserDto accountDto)
                throws EmailExistsException {

            if (emailExist(accountDto.getEmail())) {
                throw new EmailExistsException(
                        "There is an account with that email address:"  + accountDto.getEmail());
            }
            final User user = new User();
            user.setFirstName(accountDto.getFirstName());
            user.setLastName(accountDto.getLastName());
            user.setPassword(accountDto.getPassword());
            user.setEmail(accountDto.getEmail());
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            return repository.save(user);
        }
        private boolean emailExist(String email) {
            User user = repository.findByEmail(email);
            if (user != null) {
                return true;
            }
            return false;
        }
    }
