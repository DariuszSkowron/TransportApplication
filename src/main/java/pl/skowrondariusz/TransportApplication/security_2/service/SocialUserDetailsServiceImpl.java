package pl.skowrondariusz.TransportApplication.security_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;

    @Override
    public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {
        System.out.println("SocialUserDetailsServiceImpl.loadUserByUserId=" + userName);
        UserDetails userDetails = ((UserDetailsServiceImpl) userDetailServiceImpl).loadUserByUsername(userName);
        return (SocialUserDetailsImpl) userDetails;
    }
}
