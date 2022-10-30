package sia.tacocloud.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Helps to configure a user store for authentication purposes
 */
public interface UserDetailsService {

    /**
     * Uses username to look up a UserDetails object
     * @throws UsernameNotFoundException if no user can be found for the given username
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
