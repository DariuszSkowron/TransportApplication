//package pl.skowrondariusz.TransportApplication.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionKey;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        try {
//            User user = userRepository.findByEmail(email);
//            if (user == null) {
//                throw new UsernameNotFoundException("Invalid username or password.");
//            }
//            return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                    user.getPassword(),
//                    user.isEnabled(),
//                    true,
//                    true,
//                    true,
//                    mapRolesToAuthorities(user.getRoles()));
//        }catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public User findByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//
//    public User save(UserRegistrationForm registration){
//        User user = new User();
//        user.setFirstName(registration.getFirstName());
//        user.setLastName(registration.getLastName());
//        user.setEmail(registration.getEmail());
//        user.setPassword(passwordEncoder.encode(registration.getPassword()));
//        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public void updatePassword(String password, Long userId) {
//        userRepository.updatePassword(password, userId);
//    }
//
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void saveRegisteredUser(User user) {
//        userRepository.save(user);
//    }
//
//}



