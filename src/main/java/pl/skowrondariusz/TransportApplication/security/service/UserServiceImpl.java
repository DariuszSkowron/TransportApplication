package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.Role;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.security.utils.EncryptedPasswordUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),
//                user.getPassword(),
//                user.isEnabled(),
//                true,
//                true,
//                true,
//                mapRolesToAuthorities(user.getRoles()));

//        List<String> roleNames = roleNames.addAll(user.getRoles());

        List<String> roleNames =  user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }


        SocialUserDetailsImpl userDetails = new SocialUserDetailsImpl(user,  roleNames);
        return userDetails;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    public User save(UserRegistrationForm registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        String encrytedPassword = EncryptedPasswordUtils.encryptPassword(registration.getPassword());
        user.setPassword("{bcrypt}" +encrytedPassword);
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

    public User registerNewUserAccount(UserRegistrationForm registration) {
        User user = new User();
        user.setUserName(registration.getUserName());
        user.setEmail(registration.getEmail());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        String encrytedPassword = EncryptedPasswordUtils.encryptPassword(registration.getPassword());
        user.setPassword("{bcrypt}" + encrytedPassword);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    public User createAppUser(Connection<?> connection){
        ConnectionKey key = connection.getKey();
        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
        UserProfile userProfile = connection.fetchUserProfile();
        String email = userProfile.getEmail();
        User user = this.findByEmail(email);
        if (user != null) {
            return user;
        }
        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()
                + "_" + userProfile.getLastName().trim().toLowerCase();
        String userName = this.findAvailableUserName(userName_prefix);
        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = EncryptedPasswordUtils.encryptPassword(randomPassword);
        user = new User();
        user.setEnabled(false);
        user.setPassword("{bcrypt}" + encrytedPassword);
        user.setUserName(userName);
        user.setEmail(email);
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }


    public String findAvailableUserName(String userName_prefix){
        User user= this.userRepository.findByUserName(userName_prefix);
        if (user == null){
            return userName_prefix;
        }
        int i = 0;
        while (true){
            String userName = userName_prefix + "_" + i++;
            user = this.userRepository.findByUserName(userName);
            if (user == null) {
                return userName;
            }
        }
    }

    public User findByUserId(Long userId){
        return userRepository.findByUserId(userId);
    }

}


