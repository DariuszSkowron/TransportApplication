package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

    private UserServiceImpl userServiceImpl;

    public SocialUserDetailsServiceImpl(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {
        System.out.println("SocialUserDetailsServiceImpl.loadUserByUserId=" + userName);
        UserDetails userDetails = ((UserServiceImpl) userServiceImpl).loadUserByUsername(userName);
        return (SocialUserDetailsImpl) userDetails;
    }
}
