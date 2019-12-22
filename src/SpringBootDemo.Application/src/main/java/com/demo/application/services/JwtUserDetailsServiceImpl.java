package com.demo.application.services;

import com.demo.domain.models.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    static Map<String, UserDetails> users = new HashMap<String, UserDetails>() {{
        put("ivan", new UserDetailsImpl("ivan", "$2y$12$R1vw.4up2eU78M0U4G1JH.hYzhZIEV6fps6O1QcQG8fvU5QMmpdw.", true, true, true, true));
        put("viki", new UserDetailsImpl("viki", "$2y$12$R1vw.4up2eU78M0U4G1JH.hYzhZIEV6fps6O1QcQG8fvU5QMmpdw.", true, true, true, true));
        put("yuki", new UserDetailsImpl("yuki", "$2y$12$R1vw.4up2eU78M0U4G1JH.hYzhZIEV6fps6O1QcQG8fvU5QMmpdw.", true, true, true, true));
    }};

    public UserDetails loadUserByUsername(String username) {

        return users.get(username);
    }
}
