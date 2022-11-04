package sia.tacocloud.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface UserDetailsService {

    /**
     * Uses username to look up a UserDetails object
     * @throws UsernameNotFoundException if no user can be found for the given username
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
