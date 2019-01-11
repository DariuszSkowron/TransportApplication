//package pl.skowrondariusz.TransportApplication.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security_old.core.GrantedAuthority;
//import org.springframework.security_old.core.authority.SimpleGrantedAuthority;
//import pl.skowrondariusz.TransportApplication.model.Privilege;
//import pl.skowrondariusz.TransportApplication.model.Role;
//import pl.skowrondariusz.TransportApplication.model.User;
//import org.springframework.security_old.core.userdetails.UserDetails;
//import org.springframework.security_old.core.userdetails.UserDetailsService;
//import org.springframework.security_old.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pl.skowrondariusz.TransportApplication.repository.UserRepository;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service("userDetailsService")
//@Transactional
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//
//    @Autowired
//    private HttpServletRequest request;
//
//    public MyUserDetailsService() {
//        super();
//    }
//
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        try {
//            User user = userRepository.findByEmail(email);
//            if (user == null) {
//                throw new UsernameNotFoundException(
//                        "No user found with username: " + email);
//            }
//
//            return new org.springframework.security_old.core.userdetails.User(
//                    user.getEmail(),
//                    user.getPassword().toLowerCase(),
//                    user.isEnabled(),
//                    accountNonExpired,
//                    credentialsNonExpired,
//                    accountNonLocked,
//                    getAuthorities(user.getRole()));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//        private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
//            return getGrantedAuthorities(getPrivileges(roles));
//        }
//
//        private final List<String> getPrivileges(final Collection<Role> roles) {
//            final List<String> privileges = new ArrayList<String>();
//            final List<Privilege> collection = new ArrayList<Privilege>();
//            for (final Role role : roles) {
//                collection.addAll(role.getPrivileges());
//            }
//            for (final Privilege item : collection) {
//                privileges.add(item.getName());
//            }
//
//            return privileges;
//        }
//
//        private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
//            final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            for (final String privilege : privileges) {
//                authorities.add(new SimpleGrantedAuthority(privilege));
//            }
//            return authorities;
//        }
//    }