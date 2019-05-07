package pl.skowrondariusz.TransportApplication.security.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.social.security.SocialUserDetails;

import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.service.SocialUserDetailsImpl;

import java.util.List;

public class SecurityUtil {

    public static void logInUser(User user, List<String> roleNames) {
        SocialUserDetails userDetails = new SocialUserDetailsImpl(user, roleNames);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
