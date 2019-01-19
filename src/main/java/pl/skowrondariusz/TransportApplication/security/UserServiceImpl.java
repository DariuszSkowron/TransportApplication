package pl.skowrondariusz.TransportApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            try {
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new UsernameNotFoundException("Invalid username or password.");
                }
                return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        user.isEnabled(),
                        true,
                        true,
                        true,
                        mapRolesToAuthorities(user.getRoles()));
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationForm registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }




    public User createAppUser(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
        UserProfile userProfile = connection.fetchUserProfile();
        String email = userProfile.getEmail();
        User appUser = this.findByEmail(email);
        if (appUser != null) {
            return appUser;
        }
        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()
                + "_" + userProfile.getLastName().trim().toLowerCase();

        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String password = passwordEncoder.encode(randomPassword);
        appUser = new User();
        appUser.setEnabled(true);
        appUser.setEmail(email);
        appUser.setFirstName(userProfile.getFirstName());
        appUser.setLastName(userProfile.getLastName());
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(appUser);
    }

}
