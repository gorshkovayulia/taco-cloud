package sia.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.data.User;

public interface UserInterface extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
